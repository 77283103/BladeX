package org.springblade.flow.business.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.flow.business.mapper.BorrowFlowMapper;
import org.springblade.flow.business.service.IBorrowFlowService;
import org.springblade.flow.core.entity.BorrowFlowEntity;

import org.springframework.stereotype.Service;

/**
 * 借阅 服务实现类
 *
 * @author Liu Meng
 */
@Service
public class BorrowFlowServiceImpl extends BaseServiceImpl<BorrowFlowMapper, BorrowFlowEntity> implements IBorrowFlowService {

	private BorrowFlowMapper borrowFlowMapper;
	/**
	 * @Description: 传阅信息查询列表
	 * @Param:
	 * @return:
	 * @Author: lm
	 * @Date: 2020/8/12 15:08
	 */
	@Override
	public IPage<BorrowFlowEntity> selectBorrowPage(IPage<BorrowFlowEntity> page, BorrowFlowEntity borrowFlowEntity) {
		return this.borrowFlowMapper.pageList(page,borrowFlowEntity);
	}



}
