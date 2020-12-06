package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.YwlShopRecruitmentRequestVO;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.YwlShopRecruitmentEntity;

/**
 * 业务类：14.店招合同 服务类
 *
 * @author szw
 * @date : 2020-12-04 19:04:56
 */
public interface IYwlShopRecruitmentService extends BaseService<YwlShopRecruitmentEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param ywlShopRecruitment
	 * @return
	 */
	IPage<YwlShopRecruitmentEntity> pageList(IPage<YwlShopRecruitmentEntity> page, YwlShopRecruitmentRequestVO ywlShopRecruitment);
}
