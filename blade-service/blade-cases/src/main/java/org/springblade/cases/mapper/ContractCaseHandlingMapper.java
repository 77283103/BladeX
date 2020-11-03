package org.springblade.cases.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.cases.entity.ContractCaseHandlingEntity;
import org.springblade.cases.vo.ContractCaseHandlingRequestVO;

/**
 * 案件处理 Mapper 接口
 *
 * @author xhb
 * @date : 2020-10-30 10:04:16
 */
public interface ContractCaseHandlingMapper extends BaseMapper<ContractCaseHandlingEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param contractCaseHandling
	 * @return
	 */
	IPage<ContractCaseHandlingEntity> pageList(IPage<ContractCaseHandlingEntity> page, ContractCaseHandlingRequestVO contractCaseHandling);

}
