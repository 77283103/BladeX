package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ActAppDatabasechangeloglockEntity;
import org.springblade.contract.vo.ActAppDatabasechangeloglockRequestVO;
import org.springblade.core.mp.base.BaseService;

/**
 * 售货机类：2019.12.04修-售货机设备租赁合同—通用版（不可销售自选产品版本）补充协议（20191129）(1)(1) 服务类
 *
 * @author 售货机类
 * @date : 2020-12-18 16:13:50
 */
public interface IActAppDatabasechangeloglockService extends BaseService<ActAppDatabasechangeloglockEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param actAppDatabasechangeloglock
	 * @return
	 */
	IPage<ActAppDatabasechangeloglockEntity> pageList(IPage<ActAppDatabasechangeloglockEntity> page, ActAppDatabasechangeloglockRequestVO actAppDatabasechangeloglock);
}
