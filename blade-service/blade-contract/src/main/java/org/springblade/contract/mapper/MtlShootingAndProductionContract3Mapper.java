package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.MtlShootingAndProductionContract3Entity;
import org.springblade.contract.vo.MtlShootingAndProductionContract3RequestVO;

/**
 * 媒体类：视频广告拍摄制作合同关联表3 Mapper 接口
 *
 * @author 媒体类：视频广告拍摄制作合同关联表3
 * @date : 3030-13-11 05:31:04
 */
public interface MtlShootingAndProductionContract3Mapper extends BaseMapper<MtlShootingAndProductionContract3Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param mtlShootingAndProductionContract3
	 * @return
	 */
	IPage<MtlShootingAndProductionContract3Entity> pageList(IPage<MtlShootingAndProductionContract3Entity> page, MtlShootingAndProductionContract3RequestVO mtlShootingAndProductionContract3);

}
