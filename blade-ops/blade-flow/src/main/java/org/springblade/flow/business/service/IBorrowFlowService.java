package org.springblade.flow.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseService;
import org.springblade.flow.core.entity.BorrowFlowEntity;

/**
 * 借阅 服务类
 *
 * @author Liu Meng
 */
public interface IBorrowFlowService extends BaseService<BorrowFlowEntity> {

	/**
	 * 流程办结列表
	 *
	 * @param page      分页工具
	 * @param borrowFlowEntity 流程类
	 * @return
	 */
	IPage<BorrowFlowEntity> selectBorrowPage(IPage<BorrowFlowEntity> page, BorrowFlowEntity borrowFlowEntity);
}
