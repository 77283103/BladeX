package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.MtlShootingAndProductionContract1Entity;
import org.springblade.contract.vo.MtlShootingAndProductionContract1RequestVO;

/**
 * 媒体类：视频广告拍摄制作合同关联表 Mapper 接口
 *
 * @author 媒体类：视频广告拍摄制作合同关联表
 * @date : 2020-12-11 05:30:03
 */
public interface MtlShootingAndProductionContract1Mapper extends BaseMapper<MtlShootingAndProductionContract1Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param mtlShootingAndProductionContract1
	 * @return
	 */
	IPage<MtlShootingAndProductionContract1Entity> pageList(IPage<MtlShootingAndProductionContract1Entity> page, MtlShootingAndProductionContract1RequestVO mtlShootingAndProductionContract1);

}
