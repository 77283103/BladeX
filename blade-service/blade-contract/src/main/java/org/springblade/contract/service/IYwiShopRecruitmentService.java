package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.YwiShopRecruitmentEntity;
import org.springblade.contract.vo.YwlShopRecruitmentRequestVO;
import org.springblade.core.mp.base.BaseService;

/**
 * 业务类：14.店招合同 服务类
 *
 * @author szw
 * @date : 2020-12-04 19:04:56
 */
public interface IYwiShopRecruitmentService extends BaseService<YwiShopRecruitmentEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param ywlShopRecruitment
	 * @return
	 */
	IPage<YwiShopRecruitmentEntity> pageList(IPage<YwiShopRecruitmentEntity> page, YwlShopRecruitmentRequestVO ywlShopRecruitment);
}
