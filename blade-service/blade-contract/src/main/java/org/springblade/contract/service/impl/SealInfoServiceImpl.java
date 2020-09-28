package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.contract.service.IContractFormInfoService;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.SealInfoEntity;
import org.springblade.contract.mapper.SealInfoMapper;
import org.springblade.contract.service.ISealInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用印名称 服务实现类
 *
 * @author szw
 * @date : 2020-09-24 01:27:14
 */
@Service
public class SealInfoServiceImpl extends BaseServiceImpl<SealInfoMapper, SealInfoEntity> implements ISealInfoService {

	@Resource
	private IContractFormInfoService contractFormInfoService;

	@Override
	public IPage<SealInfoEntity> pageList(IPage<SealInfoEntity> page, SealInfoEntity sealInfo) {
		return baseMapper.pageList(page, sealInfo);
	}

	@Override
	public boolean save(String contractStatus,SealInfoEntity entity) {
		contractFormInfoService.updateExportStatus(contractStatus,entity.getRefContractId());
		if(baseMapper.insert(entity) == 1) {
			return true;
		}
		return false;
	}

}
