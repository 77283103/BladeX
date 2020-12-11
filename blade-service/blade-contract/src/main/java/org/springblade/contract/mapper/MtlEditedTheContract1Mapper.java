package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.MtlEditedTheContract1Entity;
import org.springblade.contract.vo.MtlEditedTheContract1RequestVO;

/**
 * 媒体类：修图合同关联表 Mapper 接口
 *
 * @author 媒体类：修图合同关联表
 * @date : 2020-12-11 05:00:48
 */
public interface MtlEditedTheContract1Mapper extends BaseMapper<MtlEditedTheContract1Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param mtlEditedTheContract1
	 * @return
	 */
	IPage<MtlEditedTheContract1Entity> pageList(IPage<MtlEditedTheContract1Entity> page, MtlEditedTheContract1RequestVO mtlEditedTheContract1);

}
