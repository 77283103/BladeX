package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.ContractRawMaterialsRequestVO;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.ContractRawMaterialsEntity;
import org.springblade.contract.mapper.ContractRawMaterialsMapper;
import org.springblade.contract.service.IContractRawMaterialsService;
import org.springframework.stereotype.Service;

/**
 * 原物料1v多 服务实现类
 *
 * @author szw
 * @date : 2020-11-22 16:42:03
 */
@Service
public class ContractRawMaterialsServiceImpl extends BaseServiceImpl<ContractRawMaterialsMapper, ContractRawMaterialsEntity> implements IContractRawMaterialsService {

	@Override
	public IPage<ContractRawMaterialsEntity> pageList(IPage<ContractRawMaterialsEntity> page, ContractRawMaterialsRequestVO contractRawMaterials) {
		return baseMapper.pageList(page, contractRawMaterials);
	}
}
