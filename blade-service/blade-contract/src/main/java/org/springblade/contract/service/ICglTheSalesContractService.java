package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.CglTheSalesContractRequestVO;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.CglTheSalesContractEntity;

/**
 * 采购类：新增原物料补充协议--买卖合同 服务类
 *
 * @author 王策
 * @date : 2020-12-10 19:07:49
 */
public interface ICglTheSalesContractService extends BaseService<CglTheSalesContractEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param cglTheSalesContract
	 * @return
	 */
	IPage<CglTheSalesContractEntity> pageList(IPage<CglTheSalesContractEntity> page, CglTheSalesContractRequestVO cglTheSalesContract);
}
