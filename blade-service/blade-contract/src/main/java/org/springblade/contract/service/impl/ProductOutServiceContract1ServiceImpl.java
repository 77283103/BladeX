package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ProductOutServiceContract1Entity;
import org.springblade.contract.mapper.ProductOutServiceContract1Mapper;
import org.springblade.contract.service.IProductOutServiceContract1Service;
import org.springblade.contract.vo.ProductOutServiceContract1ResponseVO;
import org.springblade.contract.wrapper.ProductOutServiceContract1Wrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.mp.support.ITableRef;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 生产项目外包服务合同子表1 服务实现类
 *
 * @author Wang Pengfei
 * @date : 2021-01-19 10:22:12
 */
@Service
public class ProductOutServiceContract1ServiceImpl extends BaseServiceImpl<ProductOutServiceContract1Mapper, ProductOutServiceContract1Entity> implements IProductOutServiceContract1Service, ITableRef<ProductOutServiceContract1Entity> {
	/**
	 * 合同关联字段
	 */
	private static final String REF_COLUMN_NAME = "pro_out_ser_con_id";
	@Override
	public IPage<ProductOutServiceContract1Entity> pageList(IPage<ProductOutServiceContract1Entity> page, ProductOutServiceContract1Entity productOutServiceContract1) {
		return baseMapper.pageList(page, productOutServiceContract1);
	}

	@Override
	public void saveBatchByRefId(Long refId, List<ProductOutServiceContract1ResponseVO> responseVOList) {
		this.saveBatchByRefId(refId,REF_COLUMN_NAME, ProductOutServiceContract1Wrapper.build().PVEntityList(responseVOList),this);
	}

	@Override
	public List<ProductOutServiceContract1ResponseVO> selectRefList(Long refId) {
		List<ProductOutServiceContract1Entity> productOutServiceContract1EntityList = this.selectRefList(refId, REF_COLUMN_NAME, this);
		return ProductOutServiceContract1Wrapper.build().entityPVList(productOutServiceContract1EntityList);
	}

	@Override
	public void setRefId(Long refId, ProductOutServiceContract1Entity entity) {
		entity.setId(null);
		entity.setProOutSerConId(refId);
	}
}
