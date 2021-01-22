package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.MtbProductionContract3Entity;

/**
 * 媒体类：平面广告拍摄制作合同（关联表2） Mapper 接口
 *
 * @author 韩杨
 * @date : 2021-01-21 11:27:01
 */
public interface MtbProductionContract3Mapper extends BaseMapper<MtbProductionContract3Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param mtbProductionContract3
	 * @return
	 */
	IPage<MtbProductionContract3Entity> pageList(IPage<MtbProductionContract3Entity> page, MtbProductionContract3Entity mtbProductionContract3);

}
