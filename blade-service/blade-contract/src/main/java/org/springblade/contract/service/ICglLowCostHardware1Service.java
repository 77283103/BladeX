package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.CglLowCostHardware1Entity;
import org.springblade.contract.vo.CglLowCostHardware1RequestVO;
import org.springblade.contract.vo.CglLowCostHardware1ResponseVO;
import org.springblade.core.mp.base.BaseService;

import java.util.List;

/**
 * 采购类：原物料-买卖合同 服务类
 *
 * @author 采购类：原物料-买卖合同
 * @date : 2020-12-10 18:54:35
 */
public interface ICglLowCostHardware1Service extends BaseService<CglLowCostHardware1Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param cglRawMaterials1
	 * @return
	 */
	IPage<CglLowCostHardware1Entity> pageList(IPage<CglLowCostHardware1Entity> page, CglLowCostHardware1RequestVO cglRawMaterials1);

	void saveBatchByRefId(Long refId, List<CglLowCostHardware1ResponseVO> responseVOList);

	List<CglLowCostHardware1ResponseVO> selectRefList(Long refId);
}
