package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.MtlAudioProductionContract2Entity;
import org.springblade.contract.vo.MtlAudioProductionContract2RequestVO;

/**
 * 媒体类：音频制作合同关联表2 Mapper 接口
 *
 * @author 媒体类：音频制作合同关联表2
 * @date : 2020-12-11 03:31:36
 */
public interface MtlAudioProductionContract2Mapper extends BaseMapper<MtlAudioProductionContract2Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param mtlAudioProductionContract2
	 * @return
	 */
	IPage<MtlAudioProductionContract2Entity> pageList(IPage<MtlAudioProductionContract2Entity> page, MtlAudioProductionContract2RequestVO mtlAudioProductionContract2);

}
