	package org.springblade.contract.service.impl;

	import com.alibaba.fastjson.JSON;
	import com.alibaba.fastjson.JSONArray;
	import com.alibaba.fastjson.JSONObject;
	import com.baomidou.mybatisplus.core.metadata.IPage;
	import lombok.AllArgsConstructor;
	import org.springblade.contract.entity.*;
	import org.springblade.contract.mapper.*;
	import org.springblade.contract.service.IContractFormInfoService;
	import org.springblade.contract.vo.*;
	import org.springblade.contract.wrapper.*;
	import org.springblade.core.mp.base.BaseServiceImpl;
	import org.springblade.core.secure.BladeUser;
	import org.springblade.core.secure.utils.AuthUtil;
	import org.springblade.core.tool.api.R;
	import org.springblade.core.tool.utils.Func;
	import org.springblade.resource.feign.IFileClient;
	import org.springblade.resource.vo.FileVO;
	import org.springblade.system.cache.SysCache;
	import org.springblade.system.entity.TemplateFieldEntity;
	import org.springblade.system.feign.ISysClient;
	import org.springblade.system.user.cache.UserCache;
	import org.springblade.system.user.entity.User;
	import org.springblade.system.user.feign.IUserClient;
	import org.springblade.system.vo.TemplateRequestVO;
	import org.springframework.stereotype.Service;

	import java.util.ArrayList;
	import java.util.Arrays;
	import java.util.Date;
	import java.util.List;

	/**
	 *  服务实现类
	 *
	 * @author 史智伟
	 * @date : 2020-09-23 18:04:38
	 */
	@Service
	@AllArgsConstructor
	public class ContractFormInfoServiceImpl extends BaseServiceImpl<ContractFormInfoMapper, ContractFormInfoEntity> implements IContractFormInfoService {
		private IFileClient fileClient;

		private ISysClient sysClient;

		private IUserClient userClient;

		private ContractFormInfoMapper contractFormInfoMapper;

		private ContractCounterpartMapper contractCounterpartMapper;

		private ContractBondMapper contractBondMapper;

		private ContractAccordingMapper contractAccordingMapper;

		private ContractPerformanceMapper contractPerformanceMapper;

		private ContractPerformanceColPayMapper contractPerformanceColPayMapper;

		private ContractAssessmentMapper contractAssessmentMapper;

		private ContractArchiveMapper contractArchiveMapper;

		private ContractArchiveNotMapper contractArchiveNotMapper;

		private ContractSealUsingInfoMapper sealUsingInfoMapper;

		private ContractSigningMapper signingMapper;

		private ContractArchiveNotMapper archiveNotMapper;

		private ContractRelieveMapper relieveMapper;


		@Override
		public IPage<ContractFormInfoResponseVO> pageList(IPage<ContractFormInfoEntity> page, ContractFormInfoRequestVO contractFormInfo) {
			String[] code = contractFormInfo.getContractStatus().split(",");
			contractFormInfo.setCode(Arrays.asList(code));
			page = baseMapper.pageList(page, contractFormInfo);
			IPage<ContractFormInfoResponseVO> pages=ContractFormInfoWrapper.build().entityPVPage(page);
			List<ContractFormInfoResponseVO> records = pages.getRecords();
			List<ContractFormInfoResponseVO> recordList = new ArrayList<>();

			for(ContractFormInfoResponseVO v : records){
				/*为每个对象，设置创建者名字和组织名字*/
				v.setUserRealName(userClient.userInfoById(v.getCreateUser()).getData().getRealName());
				v.setUserDepartName(sysClient.getDept(v.getCreateDept()).getData().getDeptName());
				//将相对方存入合同分页显示 获取相对方名称
				List<ContractCounterpartEntity> counterpartEntityList=contractCounterpartMapper.selectByIds(v.getId());
				v.setCounterpartEntityList(counterpartEntityList);
				//将用印信息存入合同分页 获取用印日期
				ContractSealUsingInfoEntity sealUsingInfoEntity=sealUsingInfoMapper.selectUsingById(v.getId());
				if (Func.isNotEmpty(sealUsingInfoEntity)) {
					v.setSignTime(sealUsingInfoEntity.getSignTime());
					v.setSealInfoEntity(sealUsingInfoEntity);
				}
				//将归档信息存入合同分页  获取归档日期，用印申请人，用印申请单位，用印公司,归档月份
				ContractArchiveEntity archiveEntity=contractArchiveMapper.selectArchiveById(v.getId());
				if(Func.isNotEmpty(archiveEntity)) {
					v.setArchiveMonth(archiveEntity.getArchiveMonth());
					v.setPrintApplicant(archiveEntity.getPrintApplicant());
					v.setPrintCompany(archiveEntity.getPrintCompany());
					v.setContractPrintInitDept(archiveEntity.getContractPrintInitDept());
					v.setArchiveEntity(archiveEntity);
				}
				//将签订信息存入合同分页 获取邮寄日期
				ContractSigningEntity signingEntity=signingMapper.selectSigningById(v.getId());
				if(Func.isNotEmpty(signingEntity)) {
					v.setSignDate(signingEntity.getSignDate());
					v.setContractStartingTime(signingEntity.getContractStartTime());
					v.setContractEndTime(signingEntity.getContractEndTime());
					v.setSigningEntity(signingEntity);
				}
				//将未归档信息存入合同分页  获取未归档原因
				List<ContractArchiveNotEntity> archiveNotEntity=contractArchiveNotMapper.selectArchiveNotById(v.getId());
				if(archiveNotEntity.size()>0) {
					v.setArchiveNotEntity(archiveNotEntity);
				}
				recordList.add(v);
			}
			pages.setRecords(recordList);
			return pages;
		}

		/**
		 * 修改合同状态
		 * @param contractStatus,id
		 * @param id
		 * @return
		 */
		@Override
		public boolean updateExportStatus(String contractStatus,Long id) {
			return contractFormInfoMapper.updateExportStatus(contractStatus,id);
		}

		@Override
		public IPage<ContractFormInfoEntity> pageListSealInfo(IPage<ContractFormInfoRequestVO> page, ContractFormInfoRequestVO contractFormInfoRequestVO) {
			return baseMapper.pageListSealInfo(page, contractFormInfoRequestVO);
		}


		@Override
		public void saveCounterpart(ContractFormInfoRequestVO vo) {
			contractFormInfoMapper.deleteCounterpart(vo.getId());
			contractFormInfoMapper.saveCounterpart(vo.getId(),vo.getCounterpart());
		}

		@Override
		public void saveAccording(ContractFormInfoRequestVO vo) {
			//contractFormInfoMapper.saveAccording(vo.getId(),vo.getAccording());
		}

		/**
		 * 保存合同关联用印
		 * @param vo
		 */
		@Override
		public void saveSeal(ContractFormInfoRequestVO vo) {
			contractFormInfoMapper.saveSeal(vo.getId(),vo.getSeal());
		}

		/**
		 * 保存合同关联评估
		 * @param vo 提取合同id和评估id
		 */
		@Override
		public void saveAssessment(ContractFormInfoRequestVO vo) {
			contractFormInfoMapper.saveAssessment(vo.getId(),vo.getAssessment());
		}

		/**
		 *
		 * 保存合同关联归档
		 * @param vo 获取对应的合同id和归档id
		 */
		@Override
		public void saveArchive(ContractFormInfoRequestVO vo) {
			contractFormInfoMapper.saveArchive(vo.getId(),vo.getArchive());
		}

		/**
		 *保存合同关联签订
		 * @param vo
		 */
		@Override
		public void saveSigning(ContractFormInfoRequestVO vo) {
			contractFormInfoMapper.saveSigning(vo.getId(),vo.getSigning());
		}

		/**
		 * 根据合同id查询关联数据返回到合同vo
		 * @param id 合同id
		 * @return
		 */
		@Override
		public ContractFormInfoResponseVO getById(Long id) {
			ContractFormInfoEntity contractFormInfo=baseMapper.selectById(id);
			ContractFormInfoResponseVO contractFormInfoResponseVO = ContractFormInfoWrapper.build().entityPV(contractFormInfo);
			if(Func.isNoneBlank(contractFormInfoResponseVO.getSealName())){
				String[] sealNameList = contractFormInfoResponseVO.getSealName().split(",");
				contractFormInfoResponseVO.setSealNameList(sealNameList);
			}
			//查询依据
			List<ContractAccordingEntity> contractAccordingList = contractAccordingMapper.selectByIds(id);
			contractFormInfoResponseVO.setAccordingEntityList(contractAccordingList);
			//查询相对方
			List<ContractCounterpartEntity> contractCounterpartList = contractCounterpartMapper.selectByIds(id);
			contractFormInfoResponseVO.setCounterpartEntityList(contractCounterpartList);
			//查询保证金
			List<ContractBondEntity> contractBondList = contractBondMapper.selectByIds(id);
			contractFormInfoResponseVO.setContractBond(contractBondList);
			//查询履约计划清单
			List<ContractPerformanceEntity> contractPerformanceList = contractPerformanceMapper.selectByIds(id);
			contractFormInfoResponseVO.setPerformanceList(contractPerformanceList);
			//查询履约计划收付款
			List<ContractPerformanceColPayEntity> contractPerformanceColPayList = contractPerformanceColPayMapper.selectByIds(id);
			contractFormInfoResponseVO.setPerformanceColPayList(contractPerformanceColPayList);
			//查询解除信息
			ContractRelieveEntity relieveEntity=relieveMapper.selectRelieveById(id);
			if (Func.isNotEmpty(relieveEntity)) {
				ContractRelieveResponseVO relieveResponseVO = ContractRelieveWrapper.build().entityPV(relieveEntity);
				if (Func.isNotEmpty(relieveEntity.getSigningBasis())) {
					relieveResponseVO.setAccordingEntity(contractAccordingMapper.selectById(relieveEntity.getSigningBasis()));
				}
				if (Func.isNotBlank(relieveEntity.getTermAgreement())){
					//判断解除协议是否为空，不为空将查出来返回道VO
					R<List<FileVO>> result = fileClient.getByIds(relieveResponseVO.getTermAgreement());
					if (result.isSuccess()) {
						relieveResponseVO.setTermAgreementFileVOList(result.getData());
					}
				}
				contractFormInfo.setRelieveEntity(relieveResponseVO);
			}
			//查询合同评估并保存到合同vo
			ContractAssessmentEntity contractAssessmentEntity= contractAssessmentMapper.selectByAssessmentId(id);
			if (Func.isNotEmpty(contractAssessmentEntity)) {
				ContractAssessmentResponseVO assessmentResponseVO = ContractAssessmentWrapper.build().entityPV(contractAssessmentEntity);
				//评估相关附件
				if (!Func.isEmpty(assessmentResponseVO)){
					if (Func.isNoneBlank(assessmentResponseVO.getAttachedFiles())) {
						R<List<FileVO>> result = fileClient.getByIds(assessmentResponseVO.getAttachedFiles());
						if (result.isSuccess()) {
							assessmentResponseVO.setAssessmentAttachedVOList(result.getData());
						}
					}
				}
				contractFormInfoResponseVO.setAssessmentEntity(assessmentResponseVO);
			}
			//查询合同未归档原因集合并保存到合同vo
			List<ContractArchiveNotEntity> archiveNotEntity=archiveNotMapper.selectArchiveNotById(id);
			contractFormInfoResponseVO.setArchiveNotEntity(archiveNotEntity);
			//查询合同归档并保存到合同vo
			ContractArchiveEntity contractArchiveEntity = contractArchiveMapper.selectArchiveById(id);
			if(Func.isNotEmpty(contractArchiveEntity)) {
				ContractArchiveResponseVO archiveResponseVO = ContractArchiveWrapper.build().entityPV(contractArchiveEntity);
				BladeUser user = AuthUtil.getUser();
				Long userId = Long.valueOf(user.getUserId());
				Long deptId = Long.valueOf(AuthUtil.getDeptId());
				Date now = new Date();
				archiveResponseVO.setCreateUserName(UserCache.getUser(userId).getRealName());
				archiveResponseVO.setCreateDeptName(SysCache.getDeptName(deptId));
				archiveResponseVO.setCreateSystemTime(now);
				contractFormInfoResponseVO.setArchiveEntity(archiveResponseVO);
			}
			//查询合同用印信息保存到合同vo
			if (Func.isEmpty(contractFormInfoResponseVO.getSealInfoEntity())) {
				ContractSealUsingInfoEntity sealUsingInfoEntity = sealUsingInfoMapper.selectUsingById(id);
				if (Func.isNotEmpty(sealUsingInfoEntity)) {
					ContractSealUsingInfoResponseVO sealUsingInfoResponseVO = ContractSealUsingInfoWrapper.build().entityPV(sealUsingInfoEntity);
					contractFormInfoResponseVO.setSealInfoEntity(sealUsingInfoResponseVO);
				}
			}
			//查询合同签订信息保存到合同vo
			ContractSigningEntity signingEntity=signingMapper.selectSigningById(id);
			contractFormInfoResponseVO.setSigningEntity(signingEntity);
			ContractSigningResponseVO signingResponseVO= ContractSigningWrapper.build().entityPV(signingEntity);
			//查询合同文本
			if (Func.isNoneBlank(contractFormInfoResponseVO.getTextFile())){
				R<List<FileVO>> result = fileClient.getByIds(contractFormInfoResponseVO.getTextFile());
				if (result.isSuccess()){
					contractFormInfoResponseVO.setTestFileVOList(result.getData());
				}
			}
			//查询合同附件
			if (Func.isNoneBlank(contractFormInfoResponseVO.getAttachedFiles())){
				R<List<FileVO>> result = fileClient.getByIds(contractFormInfoResponseVO.getAttachedFiles());
				if (result.isSuccess()){
					contractFormInfoResponseVO.setAttachedFileVOList(result.getData());
				}
			}
			/* 查询创建者 */
			if (Func.isNoneBlank(contractFormInfoResponseVO.getCreateUser().toString())){
				/*User user = UserCache.getUser(entity.getCreateUser());*/
				User user = userClient.userInfoById(contractFormInfoResponseVO.getCreateUser()).getData();
				contractFormInfoResponseVO.setUserRealName(user.getRealName());
			}
			/* 查询创建者组织 */
			if (Func.isNoneBlank(contractFormInfoResponseVO.getCreateDept().toString())){
				String dept = sysClient.getDeptName(contractFormInfoResponseVO.getCreateDept()).getData();
				contractFormInfoResponseVO.setUserDepartName(dept);
			}

			//签订文本扫描件
			if (!Func.isEmpty(signingResponseVO)){
				if (Func.isNoneBlank(signingResponseVO.getTextFiles())) {
					R<List<FileVO>> result = fileClient.getByIds(signingResponseVO.getTextFiles());
					if (result.isSuccess()) {
						contractFormInfoResponseVO.setSigningTextFileVOList(result.getData());
					}
				}
			}
			//签订附件扫描件
			if (!Func.isEmpty(signingResponseVO)){
				if (Func.isNoneBlank(signingResponseVO.getAttachedFiles())) {
					R<List<FileVO>> result = fileClient.getByIds(signingResponseVO.getAttachedFiles());
					if (result.isSuccess()) {
						contractFormInfoResponseVO.setSigningAttachedFileVOList(result.getData());
					}
				}
			}
			return contractFormInfoResponseVO;
		}

		/**
		 * 统计合同下载次数
		 * @param id 合同id
		 * @param fileExportCount 下载次数
		 * @param fileExportCategory
		 * @return 返回统计状态
		 */
		@Override
		public boolean textExportCount(Long id, Integer fileExportCount, String  fileExportCategory) {
			return contractFormInfoMapper.textExportCount(id, fileExportCount,fileExportCategory);
		}

		/**
		 *范本起草保存
		 * @param template 合同模板
		 */
		@Override
		public ContractFormInfoEntity templateDraft(ContractFormInfoEntity contractFormInfo,TemplateRequestVO template) {
			//把Json对象转成对象
			List<TemplateFieldEntity> templateFieldList = JSON.parseArray(template.getJson(), TemplateFieldEntity.class);
			for(TemplateFieldEntity templateField : templateFieldList){
				if("editList".equals(templateField.getComponentType())||"relationList".equals(templateField.getComponentType())){
					if("ContractAccording".equals(templateField.getRelationCode())){
						List<ContractAccordingEntity> accordingList = JSON.parseArray(templateField.getTableData().toString(), ContractAccordingEntity.class);
						/*保存依据信息*/
						if(accordingList.size()>0){
							ContractAccordingEntity contractAccording=accordingList.get(0);
							contractAccording.setContractId(contractFormInfo.getId());
							//contractAccordingMapper.insert(contractAccording);
						}
					}
					if("ContractCounterpart".equals(templateField.getRelationCode())){
						JSONObject obj= JSON.parseObject(templateField.getTableData().toString());
						JSONArray counterpartObject= (JSONArray) obj.get("counterpart");
						List<ContractCounterpartEntity> contractCounterpart=JSON.parseArray(counterpartObject.toString(), ContractCounterpartEntity.class);
						if(contractCounterpart.size()>0){
							contractFormInfoMapper.deleteCounterpart(contractFormInfo.getId());
							contractFormInfoMapper.saveCounterpart(contractFormInfo.getId(),contractCounterpart);
						}
						JSONArray contractBondArry= (JSONArray) obj.get("contractBond");
						List<ContractBondEntity> contractBond=JSON.parseArray(contractBondArry.toString(), ContractBondEntity.class);
						if(contractCounterpart.size()>0){
							contractBondMapper.deleteBond(contractFormInfo.getId());
							List<Long> list=new ArrayList<>();
							for(ContractBondEntity contractBondEntity:contractBond){
								if (Func.isEmpty(contractFormInfo.getId())){
									contractBondMapper.insert(contractBondEntity);
								}else{
									contractBondMapper.updateById(contractBondEntity);
								}
								list.add(contractBondEntity.getId());
							}
							contractBondMapper.saveBond(list,contractFormInfo.getId());
						}
					}
					if("ContractPerformance".equals(templateField.getRelationCode())){
						List<ContractPerformanceEntity> performanceList = JSON.parseArray(templateField.getTableData().toString(), ContractPerformanceEntity.class);
						/*保存履约信息*/
						if(performanceList.size()>0){
							//contractPerformanceMapper.d
							performanceList.forEach(performance->{
								performance.setContractId(contractFormInfo.getId());
								contractPerformanceMapper.insert(performance);
							});
						}
					}
					if("ContractPerformanceColPay".equals(templateField.getRelationCode())){
						List<ContractPerformanceColPayEntity> performanceColPayList = JSON.parseArray(templateField.getTableData().toString(), ContractPerformanceColPayEntity.class);
						/*保存履约计划收付款*/
						if(performanceColPayList.size()>0){
							performanceColPayList.forEach(performanceColPay->{
								performanceColPay.setContractId(contractFormInfo.getId());
								contractPerformanceColPayMapper.insert(performanceColPay);
							});
						}
					}
				}
			}
			return contractFormInfo;
		}


	}
