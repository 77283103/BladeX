package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.SclContractTemplateEntity;
import org.springblade.contract.vo.SclContractTemplateRequestVO;
import org.springblade.core.mp.base.BaseService;

/**
 * 生产类：下脚品买卖合同模版 服务类
 *
 * @author 张文武
 * @date : 2021-01-04 15:17:30
 */
public interface ISclContractTemplateService extends BaseService<SclContractTemplateEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param sclContractTemplate
	 * @return
	 */
	IPage<SclContractTemplateEntity> pageList(IPage<SclContractTemplateEntity> page, SclContractTemplateRequestVO sclContractTemplate);
}
