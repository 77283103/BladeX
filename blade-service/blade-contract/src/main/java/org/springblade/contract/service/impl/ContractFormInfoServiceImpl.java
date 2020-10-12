package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.*;
import org.springblade.contract.mapper.*;
import org.springblade.contract.service.IContractFormInfoService;
import org.springblade.contract.vo.ContractFormInfoRequestVO;
import org.springblade.contract.vo.ContractFormInfoResponseVO;
import org.springblade.contract.wrapper.ContractFormInfoWrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.resource.feign.IFileClient;
import org.springblade.resource.vo.FileVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *  服务实现类
 *
 * @author 史智伟
 * @date : 2020-09-23 18:04:38
 */
@Service
public class ContractFormInfoServiceImpl extends BaseServiceImpl<ContractFormInfoMapper, ContractFormInfoEntity> implements IContractFormInfoService {
	@Resource
	private IFileClient fileClient;
	@Resource
	private ContractFormInfoMapper contractFormInfoMapper;

	@Resource
	private ContractCounterpartMapper contractCounterpartMapper;

	@Resource
	private ContractAccordingMapper contractAccordingMapper;

	@Resource
	private ContractPerformanceMapper contractPerformanceMapper;

	@Resource
	private ContractAssessmentMapper contractAssessmentMapper;

	@Override
	public IPage<ContractFormInfoEntity> pageList(IPage<ContractFormInfoEntity> page, ContractFormInfoEntity contractFormInfo) {
		return baseMapper.pageList(page, contractFormInfo);
	}

	@Override
	public boolean updateExportStatus(String contractStatus,Long id) {
		return contractFormInfoMapper.updateExportStatus(contractStatus,id);
	}

	@Override
	public IPage<ContractFormInfoEntity> pageListSealInfo(IPage<ContractFormInfoRequestVO> page, ContractFormInfoRequestVO contractFormInfoRequestVO) {
		return baseMapper.pageListSealInfo(page, contractFormInfoRequestVO);
	}

	/**
	 * 合同评估后修改合同状态
	 * @param contractStatus 合同状态
	 * @param id 合同id
	 * @return
	 */
	@Override
	public boolean updateAssessmentStatus(String contractStatus, Long id) {
		return contractFormInfoMapper.updateAssessmentStatus(contractStatus,id);
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
	 * 保存合同评估对那个数据id
	 * @param vo 提取合同id和评估id
	 */
	@Override
	public void saveAssessment(ContractFormInfoRequestVO vo) {
		contractFormInfoMapper.saveAssessment(vo.getId(),vo.getAssessment());
	}


	@Override
	public ContractFormInfoResponseVO getById(Long id) {
		ContractFormInfoEntity contractFormInfo=baseMapper.selectById(id);
		ContractFormInfoResponseVO contractFormInfoResponseVO = ContractFormInfoWrapper.build().entityVO(contractFormInfo);
		//查询相对方保存
		List<ContractCounterpartEntity> contractCounterpartList = contractCounterpartMapper.selectByIds(id);
		contractFormInfo.setCounterpartList(contractCounterpartList);
		//查询依据保存
		List<ContractAccordingEntity> contractAccordingList = contractAccordingMapper.selectByIds(id);
		contractFormInfo.setAccordingList(contractAccordingList);
		//查询履约计划保存
		List<ContractPerformanceEntity> contractPerformanceList = contractPerformanceMapper.selectByContractId(id);
		contractFormInfo.setPerformanceList(contractPerformanceList);
		//查询
		ContractAssessmentEntity contractAssessmentEntity= contractAssessmentMapper.selectById(id);
		contractFormInfo.setAssessmentEntity(contractAssessmentEntity);
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
		return contractFormInfoResponseVO;
	}

}
