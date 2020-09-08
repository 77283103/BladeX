package org.springblade.flow.business.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;

import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.flow.business.mapper.BorrowFlowMapper;
import org.springblade.flow.business.service.BorrowFlowService;
import org.springblade.flow.core.entity.BorrowFlowEntity;

import org.springframework.stereotype.Service;

/**
 * 传阅 服务实现类
 *
 * @author Liu Meng
 * @date 2020-8-26
 */
@Service
@AllArgsConstructor
public class BorrowFlowServiceImpl extends BaseServiceImpl<BorrowFlowMapper, BorrowFlowEntity> implements BorrowFlowService {

	private BorrowFlowMapper borrowFlowMapper;

	@Override
	public IPage<BorrowFlowEntity> selectBorrowPage(IPage<BorrowFlowEntity> page, BorrowFlowEntity borrowFlowEntity) {
		return this.borrowFlowMapper.pageList(page,borrowFlowEntity);
	}



}
