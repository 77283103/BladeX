package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.*;
import org.springblade.contract.mapper.*;
import org.springblade.contract.service.IContractFormInfoService;
import org.springblade.contract.vo.*;
import org.springblade.contract.wrapper.*;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.resource.feign.IFileClient;
import org.springblade.resource.vo.FileVO;
import org.springblade.system.entity.TemplateFieldEntity;
import org.springblade.system.feign.ISysClient;
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
			v.setSealInfoEntity(sealUsingInfoEntity);
			//将归档信息存入合同分页  获取归档日期，用印申请人，用印申请单位，用印公司,归档月份
			ContractArchiveEntity archiveEntity=contractArchiveMapper.selectArchiveById(v.getId());
			v.setArchiveEntity(archiveEntity);
			//将签订信息存入合同分页 获取邮寄日期
			ContractSigningEntity signingEntity=signingMapper.selectSigningById(v.getId());
			v.setSigningEntity(signingEntity);
			//将为归档信息存入合同分页  获取未归档原因，用印时间，经办人，经办部门
			ContractArchiveNotEntity archiveNotEntity=contractArchiveNotMapper.selectArchiveNotById(v.getId());
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
		List<ContractAccordingEntity> contractAccordingList = contractAccordingMapper.selectByIdAccording(id);
		contractFormInfoResponseVO.setAccording(contractAccordingList);
		//查询相对方
		List<ContractCounterpartEntity> contractCounterpartList = contractCounterpartMapper.selectByIds(id);
		contractFormInfoResponseVO.setCounterpart(contractCounterpartList);
		//查询保证金
		List<ContractBondEntity> contractBondList = contractBondMapper.selectByIds(id);
		contractFormInfoResponseVO.setContractBond(contractBondList);
		//查询履约计划清单
		List<ContractPerformanceEntity> contractPerformanceList = contractPerformanceMapper.selectByIds(id);
		contractFormInfoResponseVO.setPerformanceList(contractPerformanceList);
		//查询履约计划收付款
		List<ContractPerformanceColPayEntity> contractPerformanceColPayList = contractPerformanceColPayMapper.selectByIds(id);
		contractFormInfoResponseVO.setPerformanceColPayList(contractPerformanceColPayList);

		//查询合同评估并保存到合同vo
		ContractAssessmentEntity contractAssessmentEntity= contractAssessmentMapper.selectByAssessmentId(id);
		contractFormInfoResponseVO.setAssessmentEntity(contractAssessmentEntity);
		//查询合同归档并保存到合同vo
		ContractArchiveEntity contractArchiveEntity=contractArchiveMapper.selectByArchiveId(id);
		contractFormInfoResponseVO.setArchiveEntity(contractArchiveEntity);
		//查询合同用印信息保存到合同vo
		if (Func.isEmpty(contractFormInfoResponseVO.getSealInfoEntity())) {
			ContractSealUsingInfoEntity sealUsingInfoEntity = sealUsingInfoMapper.selectUsingById(id);
			if (Func.isNotEmpty(sealUsingInfoEntity)) {
				ContractSealUsingInfoResponseVO sealUsingInfoResponseVO = ContractSealUsingInfoWrapper.build().entityPV(sealUsingInfoEntity);
				contractFormInfoResponseVO.setSealInfoEntity(sealUsingInfoResponseVO);
			}
		}
		//查询合同签订信息保存到合同vo
		ContractSigningEntity signingEntity=signingMapper.selectBySigningId(id);
		contractFormInfoResponseVO.setSigningEntity(signingEntity);
		ContractSigningResponseVO signingResponseVO= ContractSigningWrapper.build().entityVO(signingEntity);
		ContractAssessmentResponseVO assessmentResponseVO= ContractAssessmentWrapper.build().entityVO(contractAssessmentEntity);
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
		//评估相关附件
		if (!Func.isEmpty(assessmentResponseVO)){
			if (Func.isNoneBlank(assessmentResponseVO.getAttachedFiles())) {
				R<List<FileVO>> result = fileClient.getByIds(assessmentResponseVO.getAttachedFiles());
				if (result.isSuccess()) {
					contractFormInfoResponseVO.setAssessmentAttachedVOList(result.getData());
				}
			}
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
	public ContractFormInfoEntity templateDraft(TemplateRequestVO template) {
		//把Json对象转成对象
		List<TemplateFieldEntity> templateFieldList = JSON.parseArray(template.getJson(), TemplateFieldEntity.class);
		JSONObject j = new JSONObject();
		for(TemplateFieldEntity templateField : templateFieldList){
			if("editList".equals(templateField.getComponentType())||"relationList".equals(templateField.getComponentType())){
				if("ContractCounterpart".equals(templateField.getRelationCode())){
					JSONObject obj= JSON.parseObject(templateField.getTableData().toString());
					String counterpart=obj.getString("contractBond");
					JSONArray arry= (JSONArray) obj.get("contractBond");
					ContractCounterpartEntity contractCounterpart=JSONObject.toJavaObject((JSONObject) arry.get(0), ContractCounterpartEntity.class);
					System.out.println(obj.getString("contractBond"));
				}
			}else{
				j.put(templateField.getFieldName(), templateField.getFieldValue());
			}
		}
		ContractFormInfoEntity contractFormInfoEntity=JSONObject.toJavaObject(j, ContractFormInfoEntity.class);

		return contractFormInfoEntity;
	}


}
