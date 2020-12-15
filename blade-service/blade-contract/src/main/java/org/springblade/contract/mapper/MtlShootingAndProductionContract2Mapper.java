package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.MtlShootingAndProductionContract2Entity;
import org.springblade.contract.vo.MtlShootingAndProductionContract2RequestVO;

/**
 * 媒体类：视频广告拍摄制作合同关联表2 Mapper 接口
 *
 * @author 媒体类：视频广告拍摄制作合同关联表2
 * @date : 2020-12-11 05:31:04
 */
public interface MtlShootingAndProductionContract2Mapper extends BaseMapper<MtlShootingAndProductionContract2Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param mtlShootingAndProductionContract2
	 * @return
	 */
	IPage<MtlShootingAndProductionContract2Entity> pageList(IPage<MtlShootingAndProductionContract2Entity> page, MtlShootingAndProductionContract2RequestVO mtlShootingAndProductionContract2);

}
