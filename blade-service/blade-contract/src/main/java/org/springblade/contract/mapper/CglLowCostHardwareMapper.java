package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.CglLowCostHardwareEntity;
import org.springblade.contract.vo.CglLowCostHardwareRequestVO;

/**
 * 采购类：买卖合同（五金低耗类） Mapper 接口
 *
 * @author 王策
 * @date : 2020-12-10 19:01:55
 */
public interface CglLowCostHardwareMapper extends BaseMapper<CglLowCostHardwareEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param cglLowCostHardware
	 * @return
	 */
	IPage<CglLowCostHardwareEntity> pageList(IPage<CglLowCostHardwareEntity> page, CglLowCostHardwareRequestVO cglLowCostHardware);

}
