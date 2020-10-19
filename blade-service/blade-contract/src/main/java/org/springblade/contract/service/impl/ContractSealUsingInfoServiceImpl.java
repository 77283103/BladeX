package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.mapper.ContractFormInfoMapper;
import org.springblade.contract.service.IContractFormInfoService;
import org.springblade.contract.vo.ContractSealUsingInfoRequestVO;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.ContractSealUsingInfoEntity;
import org.springblade.contract.mapper.ContractSealUsingInfoMapper;
import org.springblade.contract.service.IContractSealUsingInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用印名称 服务实现类
 *
 * @author szw
 * @date : 2020-09-24 01:27:14
 */
@Service
public class ContractSealUsingInfoServiceImpl extends BaseServiceImpl<ContractSealUsingInfoMapper, ContractSealUsingInfoEntity> implements IContractSealUsingInfoService {

	@Resource
	private IContractFormInfoService contractFormInfoService;
	@Resource
	private ContractFormInfoMapper formInfoMapper;

	@Override
	public IPage<ContractSealUsingInfoEntity> pageList(IPage<ContractSealUsingInfoEntity> page, ContractSealUsingInfoEntity sealInfo) {
		return baseMapper.pageList(page, sealInfo);
	}

	/**
	 * 重写新增方法 同时修改合同状态
	 * @param contractStatus
	 * @param entity
	 * @return
	 */
	@Override
	public boolean save(String contractStatus, ContractSealUsingInfoEntity entity) {
		contractFormInfoService.updateExportStatus(contractStatus,entity.getRefContractId());
		if(baseMapper.insert(entity) == 1) {
			return true;
		}
		return false;
	}

	/**
	 * 保存用印合同信息关联id数据方法
	 * @param vo 从vo里面获取归档id 关联合同id
	 */
	@Override
	public void saveSeal(ContractSealUsingInfoRequestVO vo) {
		formInfoMapper.saveSeal(vo.getRefContractId(),vo.getId());
	}
}
