package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.CglRawMaterialsRequestVO;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.CglRawMaterialsEntity;
import org.springblade.contract.mapper.CglRawMaterialsMapper;
import org.springblade.contract.service.ICglRawMaterialsService;
import org.springframework.stereotype.Service;

/**
 * 采购类：原物料-买卖合同 服务实现类
 *
 * @author 王策
 * @date : 2020-12-10 19:17:23
 */
@Service
public class CglRawMaterialsServiceImpl extends BaseServiceImpl<CglRawMaterialsMapper, CglRawMaterialsEntity> implements ICglRawMaterialsService {

	@Override
	public IPage<CglRawMaterialsEntity> pageList(IPage<CglRawMaterialsEntity> page, CglRawMaterialsRequestVO cglRawMaterials) {
		return baseMapper.pageList(page, cglRawMaterials);
	}
}
