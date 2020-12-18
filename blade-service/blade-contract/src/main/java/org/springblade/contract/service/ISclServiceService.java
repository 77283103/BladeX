package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.SclServiceEntity;
import org.springblade.contract.vo.SclServiceRequestVO;
import org.springblade.core.mp.base.BaseService;

/**
 * 生产类：物流服务合同（二段仓储+配送） 服务类
 *
 * @author kx
 * @date : 2020-12-18 17:08:06
 */
public interface ISclServiceService extends BaseService<SclServiceEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param sclService
	 * @return
	 */
	IPage<SclServiceEntity> pageList(IPage<SclServiceEntity> page, SclServiceRequestVO sclService);
}
