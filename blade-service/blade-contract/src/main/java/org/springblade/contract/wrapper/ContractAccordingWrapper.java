package org.springblade.contract.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.contract.entity.ContractAccordingEntity;
import org.springblade.contract.vo.ContractAccordingVO;

/**
 *  包装类,返回视图层所需的字段
 *
 * @author Feng
 */
public class ContractAccordingWrapper extends BaseEntityWrapper<ContractAccordingEntity, ContractAccordingVO>  {

	public static ContractAccordingWrapper build() {
		return new ContractAccordingWrapper();
 	}

	@Override
	public ContractAccordingVO entityVO(ContractAccordingEntity according) {
		ContractAccordingVO accordingVO = BeanUtil.copy(according, ContractAccordingVO.class);
		return accordingVO;
	}

}
