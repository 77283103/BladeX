package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springblade.contract.dto.ContractAssessmentDTO;
import org.springblade.contract.entity.ContractCounterpartEntity;
import org.springblade.contract.entity.ContractFormInfoEntity;
import org.springblade.contract.mapper.ContractFormInfoMapper;
import org.springblade.contract.vo.ContractAssessmentRequestVO;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.ContractAssessmentEntity;
import org.springblade.contract.mapper.ContractAssessmentMapper;
import org.springblade.contract.service.IContractAssessmentService;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 合同评估表 服务实现类
 *
 * @author liyj
 * @date : 2020-09-24 10:41:34
 */
@AllArgsConstructor
@Service
public class ContractAssessmentServiceImpl extends BaseServiceImpl<ContractAssessmentMapper, ContractAssessmentEntity> implements IContractAssessmentService {

	private ContractAssessmentMapper contractAssessmentMapper;
	private ContractFormInfoMapper formInfoMapper;

	@Override
	public IPage<ContractAssessmentEntity> pageList(IPage<ContractAssessmentEntity> page, ContractAssessmentEntity assessment) {
		return baseMapper.pageList(page, assessment);
	}

	/**
	 * 保存合同评估对那个数据id
	 * @param vo 提取合同id和评估id
	 */
	@Override
	public void saveAssessment(ContractAssessmentRequestVO vo) {
		formInfoMapper.saveAssessment(vo.getContractId(),vo.getId());
	}
}
