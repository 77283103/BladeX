package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.CglRawMaterialsEntity;
import org.springblade.contract.vo.CglRawMaterialsRequestVO;

/**
 * 采购类：原物料-买卖合同 Mapper 接口
 *
 * @author 王策
 * @date : 2020-12-10 19:17:22
 */
public interface CglRawMaterialsMapper extends BaseMapper<CglRawMaterialsEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param cglRawMaterials
	 * @return
	 */
	IPage<CglRawMaterialsEntity> pageList(IPage<CglRawMaterialsEntity> page, CglRawMaterialsRequestVO cglRawMaterials);

}
