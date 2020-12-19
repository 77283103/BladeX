package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ActAppDatabasechangeloglockEntity;
import org.springblade.contract.mapper.ActAppDatabasechangeloglockMapper;
import org.springblade.contract.service.IActAppDatabasechangeloglockService;
import org.springblade.contract.vo.ActAppDatabasechangeloglockRequestVO;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 售货机类：2019.12.04修-售货机设备租赁合同—通用版（不可销售自选产品版本）补充协议（20191129）(1)(1) 服务实现类
 *
 * @author 售货机类
 * @date : 2020-12-18 16:13:50
 */
@Service
public class ActAppDatabasechangeloglockServiceImpl extends BaseServiceImpl<ActAppDatabasechangeloglockMapper, ActAppDatabasechangeloglockEntity> implements IActAppDatabasechangeloglockService {

	@Override
	public IPage<ActAppDatabasechangeloglockEntity> pageList(IPage<ActAppDatabasechangeloglockEntity> page, ActAppDatabasechangeloglockRequestVO actAppDatabasechangeloglock) {
		return baseMapper.pageList(page, actAppDatabasechangeloglock);
	}
}
