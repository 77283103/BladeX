package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.SclProductionCategoryEntity;
import org.springblade.contract.vo.SclProductionCategoryRequestVO;

/**
 * 生产类：物流服务合同（一段+调拨运输） Mapper 接口
 *
 * @author kx
 * @date : 2020-12-18 17:18:03
 */
public interface SclProductionCategoryMapper extends BaseMapper<SclProductionCategoryEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param sclProductionCategory
	 * @return
	 */
	IPage<SclProductionCategoryEntity> pageList(IPage<SclProductionCategoryEntity> page, SclProductionCategoryRequestVO sclProductionCategory);

}
