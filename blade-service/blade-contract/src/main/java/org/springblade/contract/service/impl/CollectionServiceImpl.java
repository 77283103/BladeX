package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.CollectionEntity;
import org.springblade.contract.mapper.CollectionMapper;
import org.springblade.contract.service.ICollectionService;
import org.springblade.contract.vo.CollectionRequestVO;
import org.springblade.contract.vo.CollectionResponseVO;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xhbbo
 */
@Service
public class CollectionServiceImpl  extends BaseServiceImpl<CollectionMapper, CollectionEntity> implements ICollectionService {
	@Override
	public IPage<CollectionResponseVO> pageList(IPage<CollectionEntity> page, CollectionRequestVO contractBond) {
		return baseMapper.pageList(page, contractBond);
	}

	@Override
	public List<CollectionEntity> getByIdList(Long id) {
		return baseMapper.selectByIdList(id);
	}

	@Override
	public void deleteCounterpart(Long id) {
		baseMapper.deleteCounterpart(id);
	}

	@Override
	public void deleteContractId(Long id) {
		baseMapper.deleteContractId(id);
	}
}
