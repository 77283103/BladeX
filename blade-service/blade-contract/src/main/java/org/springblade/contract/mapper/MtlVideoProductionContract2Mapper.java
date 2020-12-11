package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.MtlVideoProductionContract2Entity;
import org.springblade.contract.vo.MtlVideoProductionContract2RequestVO;

/**
 * 媒体类：视频制作合同关联表2 Mapper 接口
 *
 * @author 媒体类：视频制作合同关联表2
 * @date : 2020-12-11 08:48:36
 */
public interface MtlVideoProductionContract2Mapper extends BaseMapper<MtlVideoProductionContract2Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param mtlVideoProductionContract2
	 * @return
	 */
	IPage<MtlVideoProductionContract2Entity> pageList(IPage<MtlVideoProductionContract2Entity> page, MtlVideoProductionContract2RequestVO mtlVideoProductionContract2);

}
