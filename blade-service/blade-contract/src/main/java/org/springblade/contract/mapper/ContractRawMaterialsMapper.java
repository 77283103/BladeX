package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractRawMaterialsEntity;
import org.springblade.contract.vo.ContractRawMaterialsRequestVO;

/**
 * 原物料1v多 Mapper 接口
 *
 * @author szw
 * @date : 2020-11-22 16:42:02
 */
public interface ContractRawMaterialsMapper extends BaseMapper<ContractRawMaterialsEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param contractRawMaterials
	 * @return
	 */
	IPage<ContractRawMaterialsEntity> pageList(IPage<ContractRawMaterialsEntity> page, ContractRawMaterialsRequestVO contractRawMaterials);

}
