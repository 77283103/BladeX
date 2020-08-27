package org.springblade.flow.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseService;
import org.springblade.flow.core.entity.BorrowFlowEntity;

/**
 * 传阅 服务类
 *
 * @author Liu Meng
 * @date 2020-8-26
 */
public interface BorrowFlowService extends BaseService<BorrowFlowEntity> {

	/**
	 * 流程办结列表
	 *
	 * @param page      分页工具
	 * @param borrowFlowEntity 传阅流程类
	 * @return
	 */
	IPage<BorrowFlowEntity> selectBorrowPage(IPage<BorrowFlowEntity> page, BorrowFlowEntity borrowFlowEntity);
}
