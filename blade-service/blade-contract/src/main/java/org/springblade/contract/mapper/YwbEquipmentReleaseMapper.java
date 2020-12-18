package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.YwbEquipmentReleaseEntity;
import org.springblade.contract.vo.YwbEquipmentReleaseRequestVO;

/**
 * 业务类：19.设备投放使用协议 Mapper 接口
 *
 * @author 业务类：19.设备投放使用协议
 * @date : 2020-12-18 16:07:46
 */
public interface YwbEquipmentReleaseMapper extends BaseMapper<YwbEquipmentReleaseEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param ywbEquipmentRelease
	 * @return
	 */
	IPage<YwbEquipmentReleaseEntity> pageList(IPage<YwbEquipmentReleaseEntity> page, YwbEquipmentReleaseRequestVO ywbEquipmentRelease);

}
