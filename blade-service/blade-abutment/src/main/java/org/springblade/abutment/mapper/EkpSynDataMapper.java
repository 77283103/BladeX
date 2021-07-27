package org.springblade.abutment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.abutment.entity.EkpSynDataEntity;
import org.springblade.abutment.vo.EkpSynDataRequestVO;

/**
 * ekp同步数据 Mapper 接口
 *
 * @author chenzy
 * @date : 2021-07-27 15:42:11
 */
public interface EkpSynDataMapper extends BaseMapper<EkpSynDataEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param ekpSynData
	 * @return
	 */
	IPage<EkpSynDataEntity> pageList(IPage<EkpSynDataEntity> page, EkpSynDataRequestVO ekpSynData);

}
