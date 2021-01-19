package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ProductOutServiceContract3Entity;
import org.springblade.contract.vo.ProductOutServiceContract3ResponseVO;
import org.springblade.core.mp.base.BaseService;

import java.util.List;

/**
 * 生产项目外包服务合同子表3 服务类
 *
 * @author Wang Pengfei
 * @date : 2021-01-19 10:24:11
 */
public interface IProductOutServiceContract3Service extends BaseService<ProductOutServiceContract3Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param productOutServiceContract3
	 * @return
	 */
	IPage<ProductOutServiceContract3Entity> pageList(IPage<ProductOutServiceContract3Entity> page, ProductOutServiceContract3Entity productOutServiceContract3);

	void saveBatchByRefId(Long refId, List<ProductOutServiceContract3ResponseVO> responseVOList);

	List<ProductOutServiceContract3ResponseVO> selectRefList(Long refId);
}
