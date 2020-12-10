package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.CglActivityExecutionContractEntity;
import org.springblade.contract.vo.CglActivityExecutionContractRequestVO;

/**
 * 采购类：活动执行合同 Mapper 接口
 *
 * @author 采购类：活动执行合同
 * @date : 2020-12-10 18:29:51
 */
public interface CglActivityExecutionContractMapper extends BaseMapper<CglActivityExecutionContractEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param cglActivityExecutionContract
	 * @return
	 */
	IPage<CglActivityExecutionContractEntity> pageList(IPage<CglActivityExecutionContractEntity> page, CglActivityExecutionContractRequestVO cglActivityExecutionContract);

}
