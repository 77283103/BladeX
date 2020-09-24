package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.SealInfoEntity;
import org.springblade.contract.mapper.SealInfoMapper;
import org.springblade.contract.service.ISealInfoService;
import org.springframework.stereotype.Service;

/**
 * 用印名称 服务实现类
 *
 * @author szw
 * @date : 2020-09-24 01:27:14
 */
@Service
public class SealInfoServiceImpl extends BaseServiceImpl<SealInfoMapper, SealInfoEntity> implements ISealInfoService {

	@Override
	public IPage<SealInfoEntity> pageList(IPage<SealInfoEntity> page, SealInfoEntity sealInfo) {
		return baseMapper.pageList(page, sealInfo);
	}
}
