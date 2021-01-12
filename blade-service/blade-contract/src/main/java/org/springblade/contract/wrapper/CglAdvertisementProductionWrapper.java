package org.springblade.contract.wrapper;

import org.springblade.contract.entity.CglAdvertisementProductionEntity;
import org.springblade.contract.vo.CglAdvertisementProductionRequestVO;
import org.springblade.contract.vo.CglAdvertisementProductionResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springframework.stereotype.Component;


/**
 * 采购类：广告制作安装合同模板 包装类,返回视图层所需的字段
 *
 * @author 采购类：广告制作安装合同模板
 * @date : 2021-01-11 15:05:51
 */
@Component
public class CglAdvertisementProductionWrapper implements IEntityWrapper<CglAdvertisementProductionEntity, CglAdvertisementProductionRequestVO, CglAdvertisementProductionResponseVO> {

	public static CglAdvertisementProductionWrapper build() {
		return new CglAdvertisementProductionWrapper();
 	}

    @Override
	public CglAdvertisementProductionEntity createEntity() {
		return new CglAdvertisementProductionEntity();
	}

	@Override
	public CglAdvertisementProductionRequestVO createQV() {
		return new CglAdvertisementProductionRequestVO();
	}

	@Override
	public CglAdvertisementProductionResponseVO createPV() {
		return new CglAdvertisementProductionResponseVO();
	}

	@Override
	public void selectUserName(CglAdvertisementProductionResponseVO responseVO) {

	}
}
