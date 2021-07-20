package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.CglLowCostHardware1Entity;
import org.springblade.contract.vo.CglLowCostHardware1RequestVO;

/**
 * 采购类：原物料-买卖合同 Mapper 接口
 *
 * @author 采购类：原物料-买卖合同
 * @date : 2020-12-10 18:54:34
 */
public interface CglLowCostHardware1Mapper extends BaseMapper<CglLowCostHardware1Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param cglRawMaterials1
	 * @return
	 */
	IPage<CglLowCostHardware1Entity> pageList(IPage<CglLowCostHardware1Entity> page, CglLowCostHardware1RequestVO cglRawMaterials1);

}
