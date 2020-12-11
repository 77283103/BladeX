package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.MtlAudioProductionContract1Entity;
import org.springblade.contract.vo.MtlAudioProductionContract1RequestVO;

/**
 * 媒体类：音频制作合同关联表 Mapper 接口
 *
 * @author 媒体类：音频制作合同关联表
 * @date : 2020-12-11 03:30:24
 */
public interface MtlAudioProductionContract1Mapper extends BaseMapper<MtlAudioProductionContract1Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param mtlAudioProductionContract1
	 * @return
	 */
	IPage<MtlAudioProductionContract1Entity> pageList(IPage<MtlAudioProductionContract1Entity> page, MtlAudioProductionContract1RequestVO mtlAudioProductionContract1);

}
