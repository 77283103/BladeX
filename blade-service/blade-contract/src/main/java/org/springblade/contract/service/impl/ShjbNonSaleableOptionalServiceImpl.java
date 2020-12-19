package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ShjbNonSaleableOptionalEntity;
import org.springblade.contract.mapper.ShjbNonSaleableOptionalMapper;
import org.springblade.contract.service.IShjbNonSaleableOptionalService;
import org.springblade.contract.vo.ShjbNonSaleableOptionalRequestVO;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 售货机类：2020.2.24修 -售货机设备租赁合同—通用版（不可销售自选产品版本） 服务实现类
 *
 * @author 售货机类：2020.2.24修 -售货机设备租赁合同—通用版（不可销售自选产品版本）
 * @date : 2020-12-18 16:01:19
 */
@Service
public class ShjbNonSaleableOptionalServiceImpl extends BaseServiceImpl<ShjbNonSaleableOptionalMapper, ShjbNonSaleableOptionalEntity> implements IShjbNonSaleableOptionalService {

	@Override
	public IPage<ShjbNonSaleableOptionalEntity> pageList(IPage<ShjbNonSaleableOptionalEntity> page, ShjbNonSaleableOptionalRequestVO shjbNonSaleableOptional) {
		return baseMapper.pageList(page, shjbNonSaleableOptional);
	}
}
