package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.ContractRawMaterialsRequestVO;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.ContractRawMaterialsEntity;

/**
 * 原物料1v多 服务类
 *
 * @author szw
 * @date : 2020-11-22 16:42:03
 */
public interface IContractRawMaterialsService extends BaseService<ContractRawMaterialsEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param contractRawMaterials
	 * @return
	 */
	IPage<ContractRawMaterialsEntity> pageList(IPage<ContractRawMaterialsEntity> page, ContractRawMaterialsRequestVO contractRawMaterials);
}
