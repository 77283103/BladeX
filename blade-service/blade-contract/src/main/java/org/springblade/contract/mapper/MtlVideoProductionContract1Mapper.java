package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.MtlVideoProductionContract1Entity;
import org.springblade.contract.vo.MtlVideoProductionContract1RequestVO;

/**
 * 媒体类：视频制作合同关联表 Mapper 接口
 *
 * @author 媒体类：视频制作合同关联表
 * @date : 2020-12-11 08:47:26
 */
public interface MtlVideoProductionContract1Mapper extends BaseMapper<MtlVideoProductionContract1Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param mtlVideoProductionContract1
	 * @return
	 */
	IPage<MtlVideoProductionContract1Entity> pageList(IPage<MtlVideoProductionContract1Entity> page, MtlVideoProductionContract1RequestVO mtlVideoProductionContract1);

}
