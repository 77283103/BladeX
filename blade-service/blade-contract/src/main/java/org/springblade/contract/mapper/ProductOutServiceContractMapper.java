package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ProductOutServiceContractEntity;

/**
 * 生产项目外包服务合同 Mapper 接口
 *
 * @author Wang Pengfei
 * @date : 2021-01-19 10:20:04
 */
public interface ProductOutServiceContractMapper extends BaseMapper<ProductOutServiceContractEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param productOutServiceContract
	 * @return
	 */
	IPage<ProductOutServiceContractEntity> pageList(IPage<ProductOutServiceContractEntity> page, ProductOutServiceContractEntity productOutServiceContract);

}
