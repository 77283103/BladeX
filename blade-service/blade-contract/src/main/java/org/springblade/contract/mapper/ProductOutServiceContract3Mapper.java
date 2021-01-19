package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ProductOutServiceContract3Entity;

/**
 * 生产项目外包服务合同子表3 Mapper 接口
 *
 * @author Wang Pengfei
 * @date : 2021-01-19 10:24:10
 */
public interface ProductOutServiceContract3Mapper extends BaseMapper<ProductOutServiceContract3Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param productOutServiceContract3
	 * @return
	 */
	IPage<ProductOutServiceContract3Entity> pageList(IPage<ProductOutServiceContract3Entity> page, ProductOutServiceContract3Entity productOutServiceContract3);

}
