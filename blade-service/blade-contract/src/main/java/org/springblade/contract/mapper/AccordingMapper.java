package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.AccordingEntity;

/**
 * 合同依据管理 Mapper 接口
 *
 * @author XHB
 * @date : 2020-09-23 18:40:18
 */
public interface AccordingMapper extends BaseMapper<AccordingEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param according
	 * @return
	 */
	IPage<AccordingEntity> pageList(IPage<AccordingEntity> page, AccordingEntity according);

}
