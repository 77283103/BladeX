package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ProductOutServiceContract2Entity;
import org.springblade.contract.vo.ProductOutServiceContract2ResponseVO;
import org.springblade.core.mp.base.BaseService;

import java.util.List;

/**
 * 生产项目外包服务合同子表2 服务类
 *
 * @author Wang Pengfei
 * @date : 2021-01-19 10:23:06
 */
public interface IProductOutServiceContract2Service extends BaseService<ProductOutServiceContract2Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param productOutServiceContract2
	 * @return
	 */
	IPage<ProductOutServiceContract2Entity> pageList(IPage<ProductOutServiceContract2Entity> page, ProductOutServiceContract2Entity productOutServiceContract2);
	void saveBatchByRefId(Long refId, List<ProductOutServiceContract2ResponseVO> responseVOList);

	List<ProductOutServiceContract2ResponseVO> selectRefList(Long refId);
}
