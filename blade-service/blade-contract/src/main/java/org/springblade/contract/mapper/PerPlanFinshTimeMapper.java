package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.PerPlanFinshTimeEntity;
import org.springblade.contract.vo.PerPlanFinshTimeRequestVO;

/**
 * 履约计划完成时间 Mapper 接口
 *
 * @author chenzy
 * @date : 2021-04-20 16:34:52
 */
public interface PerPlanFinshTimeMapper extends BaseMapper<PerPlanFinshTimeEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param perPlanFinshTime
	 * @return
	 */
	IPage<PerPlanFinshTimeEntity> pageList(IPage<PerPlanFinshTimeEntity> page, PerPlanFinshTimeRequestVO perPlanFinshTime);

}
