package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.SclLogisticsServiceEntity;
import org.springblade.contract.vo.SclLogisticsServiceRequestVO;
import org.springblade.core.mp.base.BaseService;

/**
 * 生产类：物流服务合同（二段配送） 服务类
 *
 * @author kx
 * @date : 2020-12-18 17:17:42
 */
public interface ISclLogisticsServiceService extends BaseService<SclLogisticsServiceEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param sclLogisticsService
	 * @return
	 */
	IPage<SclLogisticsServiceEntity> pageList(IPage<SclLogisticsServiceEntity> page, SclLogisticsServiceRequestVO sclLogisticsService);
}
