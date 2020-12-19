package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ShjbGeneralVersionEntity;
import org.springblade.contract.mapper.ShjbGeneralVersionMapper;
import org.springblade.contract.service.IShjbGeneralVersionService;
import org.springblade.contract.vo.ShjbGeneralVersionRequestVO;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 售货机类：2020.2.24修 -售货机设备租赁合同—通用版（可销售自选产品版本）） 服务实现类
 *
 * @author 售货机类：2020.2.24修 -售货机设备租赁合同—通用版（可销售自选产品版本））
 * @date : 2020-12-18 16:02:26
 */
@Service
public class ShjbGeneralVersionServiceImpl extends BaseServiceImpl<ShjbGeneralVersionMapper, ShjbGeneralVersionEntity> implements IShjbGeneralVersionService {

	@Override
	public IPage<ShjbGeneralVersionEntity> pageList(IPage<ShjbGeneralVersionEntity> page, ShjbGeneralVersionRequestVO shjbGeneralVersion) {
		return baseMapper.pageList(page, shjbGeneralVersion);
	}
}
