package org.springblade.contract.wrapper;

import org.springblade.contract.entity.YwbBusinessContractTemplateEntity;
import org.springblade.contract.vo.YwbBusinessContractTemplateRequestVO;
import org.springblade.contract.vo.YwbBusinessContractTemplateResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springframework.stereotype.Component;


/**
 * 业务类：15.房屋租赁合同模板 包装类,返回视图层所需的字段
 *
 * @author 王策
 * @date : 2021-01-12 17:30:31
 */
@Component
public class YwbBusinessContractTemplateWrapper implements IEntityWrapper<YwbBusinessContractTemplateEntity, YwbBusinessContractTemplateRequestVO, YwbBusinessContractTemplateResponseVO> {

	public static YwbBusinessContractTemplateWrapper build() {
		return new YwbBusinessContractTemplateWrapper();
 	}

    @Override
	public YwbBusinessContractTemplateEntity createEntity() {
		return new YwbBusinessContractTemplateEntity();
	}

	@Override
	public YwbBusinessContractTemplateRequestVO createQV() {
		return new YwbBusinessContractTemplateRequestVO();
	}

	@Override
	public YwbBusinessContractTemplateResponseVO createPV() {
		return new YwbBusinessContractTemplateResponseVO();
	}

	@Override
	public void selectUserName(YwbBusinessContractTemplateResponseVO responseVO) {

	}
}
