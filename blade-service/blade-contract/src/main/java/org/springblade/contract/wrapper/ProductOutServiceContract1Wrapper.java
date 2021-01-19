package org.springblade.contract.wrapper;

import org.springblade.contract.entity.ProductOutServiceContract1Entity;
import org.springblade.contract.vo.ProductOutServiceContract1RequestVO;
import org.springblade.contract.vo.ProductOutServiceContract1ResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springframework.stereotype.Component;


/**
 * 生产项目外包服务合同子表1 包装类,返回视图层所需的字段
 *
 * @author Wang Pengfei
 * @date : 2021-01-19 10:22:13
 */
@Component
public class ProductOutServiceContract1Wrapper implements IEntityWrapper<ProductOutServiceContract1Entity, ProductOutServiceContract1RequestVO, ProductOutServiceContract1ResponseVO> {

	public static ProductOutServiceContract1Wrapper build() {
		return new ProductOutServiceContract1Wrapper();
 	}

    @Override
	public ProductOutServiceContract1Entity createEntity() {
		return new ProductOutServiceContract1Entity();
	}

	@Override
	public ProductOutServiceContract1RequestVO createQV() {
		return new ProductOutServiceContract1RequestVO();
	}

	@Override
	public ProductOutServiceContract1ResponseVO createPV() {
		return new ProductOutServiceContract1ResponseVO();
	}

	@Override
	public void selectUserName(ProductOutServiceContract1ResponseVO responseVO) {

	}
}
