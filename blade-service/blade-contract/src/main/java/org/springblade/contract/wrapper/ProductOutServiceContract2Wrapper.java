package org.springblade.contract.wrapper;

import org.springblade.contract.entity.ProductOutServiceContract2Entity;
import org.springblade.contract.vo.ProductOutServiceContract2RequestVO;
import org.springblade.contract.vo.ProductOutServiceContract2ResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springframework.stereotype.Component;


/**
 * 生产项目外包服务合同子表2 包装类,返回视图层所需的字段
 *
 * @author Wang Pengfei
 * @date : 2021-01-19 10:23:07
 */
@Component
public class ProductOutServiceContract2Wrapper implements IEntityWrapper<ProductOutServiceContract2Entity, ProductOutServiceContract2RequestVO, ProductOutServiceContract2ResponseVO> {

	public static ProductOutServiceContract2Wrapper build() {
		return new ProductOutServiceContract2Wrapper();
 	}

    @Override
	public ProductOutServiceContract2Entity createEntity() {
		return new ProductOutServiceContract2Entity();
	}

	@Override
	public ProductOutServiceContract2RequestVO createQV() {
		return new ProductOutServiceContract2RequestVO();
	}

	@Override
	public ProductOutServiceContract2ResponseVO createPV() {
		return new ProductOutServiceContract2ResponseVO();
	}

	@Override
	public void selectUserName(ProductOutServiceContract2ResponseVO responseVO) {

	}
}
