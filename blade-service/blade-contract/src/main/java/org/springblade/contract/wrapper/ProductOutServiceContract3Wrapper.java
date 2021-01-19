package org.springblade.contract.wrapper;

import org.springblade.contract.entity.ProductOutServiceContract3Entity;
import org.springblade.contract.vo.ProductOutServiceContract3RequestVO;
import org.springblade.contract.vo.ProductOutServiceContract3ResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springframework.stereotype.Component;


/**
 * 生产项目外包服务合同子表3 包装类,返回视图层所需的字段
 *
 * @author Wang Pengfei
 * @date : 2021-01-19 10:24:11
 */
@Component
public class ProductOutServiceContract3Wrapper implements IEntityWrapper<ProductOutServiceContract3Entity, ProductOutServiceContract3RequestVO, ProductOutServiceContract3ResponseVO> {

	public static ProductOutServiceContract3Wrapper build() {
		return new ProductOutServiceContract3Wrapper();
 	}

    @Override
	public ProductOutServiceContract3Entity createEntity() {
		return new ProductOutServiceContract3Entity();
	}

	@Override
	public ProductOutServiceContract3RequestVO createQV() {
		return new ProductOutServiceContract3RequestVO();
	}

	@Override
	public ProductOutServiceContract3ResponseVO createPV() {
		return new ProductOutServiceContract3ResponseVO();
	}

	@Override
	public void selectUserName(ProductOutServiceContract3ResponseVO responseVO) {

	}
}
