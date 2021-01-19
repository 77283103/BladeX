package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ProductOutServiceContract2Entity;

/**
 * 生产项目外包服务合同子表2 Mapper 接口
 *
 * @author Wang Pengfei
 * @date : 2021-01-19 10:23:06
 */
public interface ProductOutServiceContract2Mapper extends BaseMapper<ProductOutServiceContract2Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param productOutServiceContract2
	 * @return
	 */
	IPage<ProductOutServiceContract2Entity> pageList(IPage<ProductOutServiceContract2Entity> page, ProductOutServiceContract2Entity productOutServiceContract2);

}
