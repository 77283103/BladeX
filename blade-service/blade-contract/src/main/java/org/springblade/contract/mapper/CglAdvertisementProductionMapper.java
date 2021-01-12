package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.CglAdvertisementProductionEntity;

/**
 * 采购类：广告制作安装合同模板 Mapper 接口
 *
 * @author 采购类：广告制作安装合同模板
 * @date : 2021-01-12 14:02:00
 */
public interface CglAdvertisementProductionMapper extends BaseMapper<CglAdvertisementProductionEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param cglAdvertisementProduction
	 * @return
	 */
	IPage<CglAdvertisementProductionEntity> pageList(IPage<CglAdvertisementProductionEntity> page, CglAdvertisementProductionEntity cglAdvertisementProduction);

}
