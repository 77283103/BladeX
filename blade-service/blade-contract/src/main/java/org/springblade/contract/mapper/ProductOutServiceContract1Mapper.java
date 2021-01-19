package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ProductOutServiceContract1Entity;

/**
 * 生产项目外包服务合同子表1 Mapper 接口
 *
 * @author Wang Pengfei
 * @date : 2021-01-19 10:22:12
 */
public interface ProductOutServiceContract1Mapper extends BaseMapper<ProductOutServiceContract1Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param productOutServiceContract1
	 * @return
	 */
	IPage<ProductOutServiceContract1Entity> pageList(IPage<ProductOutServiceContract1Entity> page, ProductOutServiceContract1Entity productOutServiceContract1);

}
