package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.PerPlanFinshTimeRequestVO;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.PerPlanFinshTimeEntity;

import java.util.List;

/**
 * 履约计划完成时间 服务类
 *
 * @author chenzy
 * @date : 2021-04-20 16:34:52
 */
public interface IPerPlanFinshTimeService extends BaseService<PerPlanFinshTimeEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param perPlanFinshTime
	 * @return
	 */
	IPage<PerPlanFinshTimeEntity> pageList(IPage<PerPlanFinshTimeEntity> page, PerPlanFinshTimeRequestVO perPlanFinshTime);

	/**
	 * 批量新增
	 * @param planFinshTimeRequestVOList
	 * @return
	 */
	Boolean addListByRequest(List<PerPlanFinshTimeRequestVO> planFinshTimeRequestVOList,Long contractId,Long serviceContentId);

}
