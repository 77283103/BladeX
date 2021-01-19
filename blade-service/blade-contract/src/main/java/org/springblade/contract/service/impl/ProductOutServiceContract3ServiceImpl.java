package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ProductOutServiceContract3Entity;
import org.springblade.contract.mapper.ProductOutServiceContract3Mapper;
import org.springblade.contract.service.IProductOutServiceContract3Service;
import org.springblade.contract.vo.ProductOutServiceContract3ResponseVO;
import org.springblade.contract.wrapper.ProductOutServiceContract3Wrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.mp.support.ITableRef;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 生产项目外包服务合同子表3 服务实现类
 *
 * @author Wang Pengfei
 * @date : 2021-01-19 10:24:11
 */
@Service
public class ProductOutServiceContract3ServiceImpl extends BaseServiceImpl<ProductOutServiceContract3Mapper, ProductOutServiceContract3Entity> implements IProductOutServiceContract3Service, ITableRef<ProductOutServiceContract3Entity> {
	/**
	 * 合同关联字段
	 */
	private static final String REF_COLUMN_NAME = "pro_out_ser_con3_id";
	@Override
	public IPage<ProductOutServiceContract3Entity> pageList(IPage<ProductOutServiceContract3Entity> page, ProductOutServiceContract3Entity productOutServiceContract3) {
		return baseMapper.pageList(page, productOutServiceContract3);
	}

	@Override
	public void saveBatchByRefId(Long refId, List<ProductOutServiceContract3ResponseVO> responseVOList) {
		this.saveBatchByRefId(refId,REF_COLUMN_NAME, ProductOutServiceContract3Wrapper.build().PVEntityList(responseVOList),this);
	}

	@Override
	public List<ProductOutServiceContract3ResponseVO> selectRefList(Long refId) {
		List<ProductOutServiceContract3Entity> productOutServiceContract3EntityList = this.selectRefList(refId, REF_COLUMN_NAME, this);
		return ProductOutServiceContract3Wrapper.build().entityPVList(productOutServiceContract3EntityList);
	}

	@Override
	public void setRefId(Long refId, ProductOutServiceContract3Entity entity) {
		entity.setId(null);
		entity.setProOutSerCon3Id(refId);
	}
}
