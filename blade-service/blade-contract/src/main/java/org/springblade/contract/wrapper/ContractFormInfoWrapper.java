package org.springblade.contract.wrapper;

import org.springblade.contract.entity.ContractFormInfoEntity;
import org.springblade.contract.vo.ContractFormInfoResponseVO;
import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;

/**
 *  包装类,返回视图层所需的字段
 *
 * @author 史智伟
 * @date : 2020-09-23 18:04:38
 */
public class ContractFormInfoWrapper extends BaseEntityWrapper<ContractFormInfoEntity, ContractFormInfoResponseVO> {



	public static ContractFormInfoWrapper build() {
		return new ContractFormInfoWrapper();
 	}

	@Override
	public ContractFormInfoResponseVO entityVO(ContractFormInfoEntity contractFormInfo) {
		ContractFormInfoResponseVO contractFormInfoResponseVO = BeanUtil.copy(contractFormInfo, ContractFormInfoResponseVO.class);

		return contractFormInfoResponseVO;
	}

}
