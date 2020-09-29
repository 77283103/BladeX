package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractTemplateEntity;

/**
 * 范本管理 Mapper 接口
 *
 * @author XHB
 * @date : 2020-09-24 13:57:36
 */
public interface ContractTemplateMapper extends BaseMapper<ContractTemplateEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param template
	 * @return
	 */
	IPage<ContractTemplateEntity> pageList(IPage<ContractTemplateEntity> page, ContractTemplateEntity template);

}
