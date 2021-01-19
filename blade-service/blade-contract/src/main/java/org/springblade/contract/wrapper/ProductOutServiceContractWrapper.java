package org.springblade.contract.wrapper;

import org.springblade.contract.entity.ProductOutServiceContractEntity;
import org.springblade.contract.vo.ProductOutServiceContractRequestVO;
import org.springblade.contract.vo.ProductOutServiceContractResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springframework.stereotype.Component;


/**
 * 生产项目外包服务合同 包装类,返回视图层所需的字段
 *
 * @author Wang Pengfei
 * @date : 2021-01-19 10:20:08
 */
@Component
public class ProductOutServiceContractWrapper implements IEntityWrapper<ProductOutServiceContractEntity, ProductOutServiceContractRequestVO, ProductOutServiceContractResponseVO> {

	public static ProductOutServiceContractWrapper build() {
		return new ProductOutServiceContractWrapper();
 	}

    @Override
	public ProductOutServiceContractEntity createEntity() {
		return new ProductOutServiceContractEntity();
	}

	@Override
	public ProductOutServiceContractRequestVO createQV() {
		return new ProductOutServiceContractRequestVO();
	}

	@Override
	public ProductOutServiceContractResponseVO createPV() {
		return new ProductOutServiceContractResponseVO();
	}

	@Override
	public void selectUserName(ProductOutServiceContractResponseVO responseVO) {

	}
}
