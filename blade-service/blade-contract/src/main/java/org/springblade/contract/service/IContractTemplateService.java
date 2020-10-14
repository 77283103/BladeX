package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.ContractTemplateEntity;

/**
 * 范本管理 服务类
 *
 * @author XHB
 * @date : 2020-09-24 13:57:37
 */
public interface IContractTemplateService extends BaseService<ContractTemplateEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param template
	 * @return
	 */
	IPage<ContractTemplateEntity> pageList(IPage<ContractTemplateEntity> page, ContractTemplateEntity template);

	/**
	 * 废弃范本后修改范本状态
	 * @param status,ids
	 * @return
	 */
	boolean updateTemplateStatus(String status,String id);
}
