package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.CglTheSalesContractEntity;
import org.springblade.contract.vo.CglTheSalesContractRequestVO;

/**
 * 采购类：新增原物料补充协议--买卖合同 Mapper 接口
 *
 * @author 王策
 * @date : 2020-12-10 19:07:48
 */
public interface CglTheSalesContractMapper extends BaseMapper<CglTheSalesContractEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param cglTheSalesContract
	 * @return
	 */
	IPage<CglTheSalesContractEntity> pageList(IPage<CglTheSalesContractEntity> page, CglTheSalesContractRequestVO cglTheSalesContract);

}
