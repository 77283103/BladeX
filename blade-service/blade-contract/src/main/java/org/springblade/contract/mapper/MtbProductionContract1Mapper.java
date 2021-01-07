package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.MtbProductionContract1Entity;
import org.springblade.contract.vo.MtbProductionContract1RequestVO;

/**
 * 媒体类：平面广告拍摄制作合同（关联表1） Mapper 接口
 *
 * @author 张文武
 * @date : 2021-01-04 11:27:46
 */
public interface MtbProductionContract1Mapper extends BaseMapper<MtbProductionContract1Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param mtbProductionContract1
	 * @return
	 */
	IPage<MtbProductionContract1Entity> pageList(IPage<MtbProductionContract1Entity> page, MtbProductionContract1RequestVO mtbProductionContract1);

}
