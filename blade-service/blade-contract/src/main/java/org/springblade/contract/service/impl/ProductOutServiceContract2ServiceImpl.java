package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ProductOutServiceContract2Entity;
import org.springblade.contract.mapper.ProductOutServiceContract2Mapper;
import org.springblade.contract.service.IProductOutServiceContract2Service;
import org.springblade.contract.vo.ProductOutServiceContract2ResponseVO;
import org.springblade.contract.wrapper.ProductOutServiceContract2Wrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.mp.support.ITableRef;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 生产项目外包服务合同子表2 服务实现类
 *
 * @author Wang Pengfei
 * @date : 2021-01-19 10:23:06
 */
@Service
public class ProductOutServiceContract2ServiceImpl extends BaseServiceImpl<ProductOutServiceContract2Mapper, ProductOutServiceContract2Entity> implements IProductOutServiceContract2Service, ITableRef<ProductOutServiceContract2Entity> {
	/**
	 * 合同关联字段
	 */
	private static final String REF_COLUMN_NAME = "pro_out_ser_con_id";
	@Override
	public IPage<ProductOutServiceContract2Entity> pageList(IPage<ProductOutServiceContract2Entity> page, ProductOutServiceContract2Entity productOutServiceContract2) {
		return baseMapper.pageList(page, productOutServiceContract2);
	}

	@Override
	public void saveBatchByRefId(Long refId, List<ProductOutServiceContract2ResponseVO> responseVOList) {
		this.saveBatchByRefId(refId,REF_COLUMN_NAME, ProductOutServiceContract2Wrapper.build().PVEntityList(responseVOList),this);
	}

	@Override
	public List<ProductOutServiceContract2ResponseVO> selectRefList(Long refId) {
		List<ProductOutServiceContract2Entity> productOutServiceContract2EntityList = this.selectRefList(refId, REF_COLUMN_NAME, this);
		return ProductOutServiceContract2Wrapper.build().entityPVList(productOutServiceContract2EntityList);
	}

	@Override
	public void setRefId(Long refId, ProductOutServiceContract2Entity entity) {
		entity.setId(null);
		entity.setProOutSerConId(refId);
	}
}
