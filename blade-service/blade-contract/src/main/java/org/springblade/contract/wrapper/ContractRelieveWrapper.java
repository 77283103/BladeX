package org.springblade.contract.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.contract.entity.ContractRelieveEntity;
import org.springblade.contract.vo.ContractRelieveResponseVO;

/**
 *  包装类,返回视图层所需的字段
 *
 * @author szw
 * @date : 2020-09-23 20:10:29
 */
public class ContractRelieveWrapper extends BaseEntityWrapper<ContractRelieveEntity, ContractRelieveResponseVO>  {

	public static ContractRelieveWrapper build() {
		return new ContractRelieveWrapper();
 	}

	@Override
	public ContractRelieveResponseVO entityVO(ContractRelieveEntity relieve) {
		ContractRelieveResponseVO relieveResponseVO = BeanUtil.copy(relieve, ContractRelieveResponseVO.class);
		return relieveResponseVO;
	}

}
