package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.CglRawMaterialsRequestVO;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.CglRawMaterialsEntity;

/**
 * 采购类：原物料-买卖合同 服务类
 *
 * @author 王策
 * @date : 2020-12-10 19:17:23
 */
public interface ICglRawMaterialsService extends BaseService<CglRawMaterialsEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param cglRawMaterials
	 * @return
	 */
	IPage<CglRawMaterialsEntity> pageList(IPage<CglRawMaterialsEntity> page, CglRawMaterialsRequestVO cglRawMaterials);
}
