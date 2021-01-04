package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.SclContractTemplateEntity;
import org.springblade.contract.vo.SclContractTemplateRequestVO;

/**
 * 生产类：下脚品买卖合同模版 Mapper 接口
 *
 * @author 张文武
 * @date : 2021-01-04 15:17:29
 */
public interface SclContractTemplateMapper extends BaseMapper<SclContractTemplateEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param sclContractTemplate
	 * @return
	 */
	IPage<SclContractTemplateEntity> pageList(IPage<SclContractTemplateEntity> page, SclContractTemplateRequestVO sclContractTemplate);

}
