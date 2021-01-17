package org.springblade.contract.wrapper;

import org.springblade.contract.entity.MtlEditedTheContractEntity;
import org.springblade.contract.vo.MtlEditedTheContractRequestVO;
import org.springblade.contract.vo.MtlEditedTheContractResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springframework.stereotype.Component;


/**
 * 媒体类：修图合同 包装类,返回视图层所需的字段
 *
 * @author 媒体类：修图合同
 * @date : 2020-12-10 19:24:50
 */
@Component
public class MtlEditedTheContractWrapper implements IEntityWrapper<MtlEditedTheContractEntity, MtlEditedTheContractRequestVO, MtlEditedTheContractResponseVO> {

	public static MtlEditedTheContractWrapper build() {
		return new MtlEditedTheContractWrapper();
 	}

    @Override
	public MtlEditedTheContractEntity createEntity() {
		return new MtlEditedTheContractEntity();
	}

	@Override
	public MtlEditedTheContractRequestVO createQV() {
		return new MtlEditedTheContractRequestVO();
	}

	@Override
	public MtlEditedTheContractResponseVO createPV() {
		return new MtlEditedTheContractResponseVO();
	}

    @Override
    public void selectUserName(MtlEditedTheContractResponseVO responseVO) {

    }
}
