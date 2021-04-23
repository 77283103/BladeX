package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.PerPlanFinshContentEntity;
import org.springblade.contract.vo.PerPlanFinshContentRequestVO;

/**
 * 履约计划完成内容 Mapper 接口
 *
 * @author chenzy
 * @date : 2021-04-20 16:28:58
 */
public interface PerPlanFinshContentMapper extends BaseMapper<PerPlanFinshContentEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param perPlanFinshContent
	 * @return
	 */
	IPage<PerPlanFinshContentEntity> pageList(IPage<PerPlanFinshContentEntity> page, PerPlanFinshContentRequestVO perPlanFinshContent);

}
