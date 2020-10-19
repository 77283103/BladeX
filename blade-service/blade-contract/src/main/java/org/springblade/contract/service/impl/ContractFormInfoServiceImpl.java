package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.*;
import org.springblade.contract.mapper.*;
import org.springblade.contract.service.IContractFormInfoService;
import org.springblade.contract.service.IContractSigningService;
import org.springblade.contract.vo.ContractFormInfoRequestVO;
import org.springblade.contract.vo.ContractFormInfoResponseVO;
import org.springblade.contract.vo.ContractSigningResponseVO;
import org.springblade.contract.wrapper.ContractFormInfoWrapper;
import org.springblade.contract.wrapper.ContractSigningWrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.resource.feign.IFileClient;
import org.springblade.resource.vo.FileVO;
import org.springblade.system.feign.ISysClient;
import org.springblade.system.user.entity.User;
import org.springblade.system.user.feign.IUserClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

	private ContractAccordingMapper contractAccordingMapper;

	private ContractPerformanceMapper contractPerformanceMapper;

	private ContractAssessmentMapper contractAssessmentMapper;

	private ContractArchiveMapper contractArchiveMapper;

	private ContractSealUsingInfoMapper sealUsingInfoMapper;

	private ContractSigningMapper signingMapper;

	@Override
	public IPage<ContractFormInfoResponseVO> pageList(IPage<ContractFormInfoEntity> page, ContractFormInfoEntity contractFormInfo) {
		page = baseMapper.pageList(page, contractFormInfo);
		IPage<ContractFormInfoResponseVO> pages=ContractFormInfoWrapper.build().pageVO(page);
		List<ContractFormInfoResponseVO> records = pages.getRecords();
		List<ContractFormInfoResponseVO> recordList = new ArrayList<>();
		/*为每个对象，设置创建者名字和组织名字*/
		for(ContractFormInfoResponseVO v : records){
			v.setUserRealName(userClient.userInfoById(v.getCreateUser()).getData().getRealName());
			v.setUserDepartName(sysClient.getDept(v.getCreateDept()).getData().getDeptName());
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
		contractFormInfoMapper.saveCounterpart(vo.getId(),vo.getCounterpart());
	}

	@Override
	public void saveAccording(ContractFormInfoRequestVO vo) {
		contractFormInfoMapper.saveAccording(vo.getId(),vo.getAccording());
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
		ContractFormInfoResponseVO contractFormInfoResponseVO = ContractFormInfoWrapper.build().entityVO(contractFormInfo);
		//查询相对方保存
		List<ContractCounterpartEntity> contractCounterpartList = contractCounterpartMapper.selectByIds(id);
		contractFormInfoResponseVO.setCounterpartList(contractCounterpartList);
		//查询依据保存
		List<ContractAccordingEntity> contractAccordingList = contractAccordingMapper.selectByIds(id);
		contractFormInfoResponseVO.setAccordingList(contractAccordingList);
		//查询履约计划保存
		List<ContractPerformanceEntity> contractPerformanceList = contractPerformanceMapper.selectByContractId(id);
		contractFormInfoResponseVO.setPerformanceList(contractPerformanceList);
		//查询合同评估并保存到合同vo
		ContractAssessmentEntity contractAssessmentEntity= contractAssessmentMapper.selectById(id);
		contractFormInfoResponseVO.setAssessmentEntity(contractAssessmentEntity);
		//查询合同归档并保存到合同vo
		ContractArchiveEntity contractArchiveEntity=contractArchiveMapper.selectById(id);
		contractFormInfoResponseVO.setArchiveEntity(contractArchiveEntity);
		//查询合同用印信息保存到合同vo
		ContractSealUsingInfoEntity sealUsingInfoEntity=sealUsingInfoMapper.selectById(id);
		contractFormInfoResponseVO.setSealInfoEntity(sealUsingInfoEntity);
		//查询合同签订信息保存到合同vo
		ContractSigningEntity signingEntity=signingMapper.selectById(id);
		contractFormInfoResponseVO.setSigningEntity(signingEntity);
//		ContractSigningResponseVO signingResponseVO= ContractSigningWrapper.build().entityVO(signingEntity);
		//评估相关附件
		if (Func.isNoneBlank(contractAssessmentEntity.getAttachedFiles())){
			R<List<FileVO>> result = fileClient.getByIds(contractAssessmentEntity.getAttachedFiles());
			if (result.isSuccess()){
				contractFormInfoResponseVO.setAssessmentAttachedVOList(result.getData());
			}
		}
		//@Func.isNoneBlank判断是否全为非空字符串
		//文本扫描件
		if (Func.isNoneBlank(signingEntity.getTextFiles())){
			R<List<FileVO>> result = fileClient.getByIds(signingEntity.getTextFiles());
			if (result.isSuccess()){
				contractFormInfoResponseVO.setSigningTextFileVOList(result.getData());
			}
		}
		//附件扫描件
		if (Func.isNoneBlank(signingEntity.getAttachedFiles())){
			R<List<FileVO>> result = fileClient.getByIds(signingEntity.getAttachedFiles());
			if (result.isSuccess()){
				contractFormInfoResponseVO.setSigningAttachedFileVOList(result.getData());
			}
		}
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

}
