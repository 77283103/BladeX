package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ProductOutServiceContract1Entity;
import org.springblade.contract.vo.ProductOutServiceContract1ResponseVO;
import org.springblade.core.mp.base.BaseService;

import java.util.List;

/**
 * 生产项目外包服务合同子表1 服务类
 *
 * @author Wang Pengfei
 * @date : 2021-01-19 10:22:12
 */
public interface IProductOutServiceContract1Service extends BaseService<ProductOutServiceContract1Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param productOutServiceContract1
	 * @return
	 */
	IPage<ProductOutServiceContract1Entity> pageList(IPage<ProductOutServiceContract1Entity> page, ProductOutServiceContract1Entity productOutServiceContract1);
	void saveBatchByRefId(Long refId, List<ProductOutServiceContract1ResponseVO> responseVOList);

	List<ProductOutServiceContract1ResponseVO> selectRefList(Long refId);
}
