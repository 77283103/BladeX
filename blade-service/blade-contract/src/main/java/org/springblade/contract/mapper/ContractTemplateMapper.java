package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractTemplateEntity;

import java.util.List;

/**
 * 范本管理 Mapper 接口
 *
 * @author XHB
 * @date : 2020-09-24 13:57:36
 */
public interface ContractTemplateMapper extends BaseMapper<ContractTemplateEntity> {

	/**
	 * 分页查询
	 *
	 * @param page
	 * @param template
	 * @return
	 */
	IPage<ContractTemplateEntity> pageList(IPage<ContractTemplateEntity> page, ContractTemplateEntity template);


	/**
	 * 批量废弃后修改范本状态
	 *
	 * @param templateStatus
	 * @param ids
	 * @return
	 */
	boolean updateEachTemplateStatus(String templateStatus, String ids);

	/**
	 * 废弃范本后修改范本状态
	 * @param templateStatus
	 * @param id
	 * @return
	 */
	boolean updateTemplateStatus(String templateStatus, Long id);

    List<ContractTemplateEntity> versionInfo(Long id);

}