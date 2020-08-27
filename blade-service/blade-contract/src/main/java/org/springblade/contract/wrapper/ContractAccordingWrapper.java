package org.springblade.contract.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.contract.entity.ContractAccordingEntity;
import org.springblade.contract.vo.ContractAccordingResponseVO;

/**
 *  包装类,返回视图层所需的字段
 *
 * @author feng
 */
public class ContractAccordingWrapper extends BaseEntityWrapper<ContractAccordingEntity, ContractAccordingResponseVO>  {

	public static ContractAccordingWrapper build() {
		return new ContractAccordingWrapper();
 	}

	@Override
	public ContractAccordingResponseVO entityVO(ContractAccordingEntity according) {
		return BeanUtil.copy(according, ContractAccordingResponseVO.class);
	}

}
