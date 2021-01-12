package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.YwbBusinessContractTemplateEntity;

/**
 * 业务类：15.房屋租赁合同模板 Mapper 接口
 *
 * @author 王策
 * @date : 2021-01-12 17:30:28
 */
public interface YwbBusContractTemplateMapper extends BaseMapper<YwbBusinessContractTemplateEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param ywbBusinessContractTemplate
	 * @return
	 */
	IPage<YwbBusinessContractTemplateEntity> pageList(IPage<YwbBusinessContractTemplateEntity> page, YwbBusinessContractTemplateEntity ywbBusinessContractTemplate);

}
