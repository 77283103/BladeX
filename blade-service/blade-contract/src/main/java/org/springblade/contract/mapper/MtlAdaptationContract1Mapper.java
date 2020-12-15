package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.MtlAdaptationContract1Entity;
import org.springblade.contract.vo.MtlAdaptationContract1RequestVO;

/**
 * 媒体类：视频广告改编合同关联表 Mapper 接口
 *
 * @author 媒体类：视频广告改编合同关联表
 * @date : 2020-12-11 08:36:15
 */
public interface MtlAdaptationContract1Mapper extends BaseMapper<MtlAdaptationContract1Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param mtlAdaptationContract1
	 * @return
	 */
	IPage<MtlAdaptationContract1Entity> pageList(IPage<MtlAdaptationContract1Entity> page, MtlAdaptationContract1RequestVO mtlAdaptationContract1);

}
