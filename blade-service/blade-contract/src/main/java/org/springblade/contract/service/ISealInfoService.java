package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.SealInfoEntity;

/**
 * 用印名称 服务类
 *
 * @author szw
 * @date : 2020-09-24 01:27:14
 */
public interface ISealInfoService extends BaseService<SealInfoEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param sealInfo
	 * @return
	 */
	IPage<SealInfoEntity> pageList(IPage<SealInfoEntity> page, SealInfoEntity sealInfo);
}
