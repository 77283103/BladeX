package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.YwbBusinessContractTemplateEntity;

/**
 * 业务类：15.房屋租赁合同模板 服务类
 *
 * @author 王策
 * @date : 2021-01-12 17:30:29
 */
public interface IYwbBusContracteTemplateService extends BaseService<YwbBusinessContractTemplateEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param ywbBusinessContractTemplate
	 * @return
	 */
	IPage<YwbBusinessContractTemplateEntity> pageList(IPage<YwbBusinessContractTemplateEntity> page, YwbBusinessContractTemplateEntity ywbBusinessContractTemplate);
}
