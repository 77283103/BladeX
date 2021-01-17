package org.springblade.contract.wrapper;

import org.springblade.contract.entity.InferiorProductContractEntity;
import org.springblade.contract.vo.InferiorProductContractRequestVO;
import org.springblade.contract.vo.InferiorProductContractResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springframework.stereotype.Component;


/**
 * 下脚品买卖合同模板 包装类,返回视图层所需的字段
 *
 * @author Wang Pengfei
 * @date : 2021-01-15 15:54:19
 */
@Component
public class InferiorProductContractWrapper implements IEntityWrapper<InferiorProductContractEntity, InferiorProductContractRequestVO, InferiorProductContractResponseVO> {

	public static InferiorProductContractWrapper build() {
		return new InferiorProductContractWrapper();
 	}

    @Override
	public InferiorProductContractEntity createEntity() {
		return new InferiorProductContractEntity();
	}

	@Override
	public InferiorProductContractRequestVO createQV() {
		return new InferiorProductContractRequestVO();
	}

	@Override
	public InferiorProductContractResponseVO createPV() {
		return new InferiorProductContractResponseVO();
	}

	@Override
	public void selectUserName(InferiorProductContractResponseVO responseVO) {

	}
}
