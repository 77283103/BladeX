package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.SclProjectOutsourcingEntity;
import org.springblade.contract.vo.SclProjectOutsourcingRequestVO;
import org.springblade.core.mp.base.BaseService;

/**
 * 生产类：生产项目外包服务合同 服务类
 *
 * @author kx
 * @date : 2020-12-11 11:03:55
 */
public interface ISclProjectOutsourcingService extends BaseService<SclProjectOutsourcingEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param sclProjectOutsourcing
	 * @return
	 */
	IPage<SclProjectOutsourcingEntity> pageList(IPage<SclProjectOutsourcingEntity> page, SclProjectOutsourcingRequestVO sclProjectOutsourcing);

}
