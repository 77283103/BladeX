package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.MtbProductionContract2Entity;
import org.springblade.contract.vo.MtbProductionContract2RequestVO;

/**
 * 媒体类：平面广告拍摄制作合同（关联表2） Mapper 接口
 *
 * @author 张文武
 * @date : 2021-01-04 11:24:00
 */
public interface MtbProductionContract2Mapper extends BaseMapper<MtbProductionContract2Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param mtbProductionContract2
	 * @return
	 */
	IPage<MtbProductionContract2Entity> pageList(IPage<MtbProductionContract2Entity> page, MtbProductionContract2RequestVO mtbProductionContract2);

}
