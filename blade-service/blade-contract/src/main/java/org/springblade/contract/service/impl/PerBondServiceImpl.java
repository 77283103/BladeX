package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.PerBondListVo;
import org.springblade.contract.vo.PerBondRequestVO;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.PerBondEntity;
import org.springblade.contract.mapper.PerBondMapper;
import org.springblade.contract.service.IPerBondService;
import org.springframework.stereotype.Service;


/**
 * 履约计划保证金 服务实现类
 *
 * @author chenzy
 * @date : 2021-04-27 17:06:22
 */
@Service
public class PerBondServiceImpl extends BaseServiceImpl<PerBondMapper, PerBondEntity> implements IPerBondService {

	@Override
	public IPage<PerBondEntity> pageList(IPage<PerBondEntity> page, PerBondRequestVO perBond) {
		return baseMapper.pageList(page, perBond);
	}


	@Override
	public IPage<PerBondListVo> perBondList(IPage<PerBondEntity> page, PerBondRequestVO perBond) {
		return baseMapper.perBondList(page, perBond);
	}
}
