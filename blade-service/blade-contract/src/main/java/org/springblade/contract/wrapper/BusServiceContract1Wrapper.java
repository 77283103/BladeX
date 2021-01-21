package org.springblade.contract.wrapper;

import org.springblade.contract.entity.BusServiceContract1Entity;
import org.springblade.contract.vo.BusServiceContract1RequestVO;
import org.springblade.contract.vo.BusServiceContract1ResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springframework.stereotype.Component;


/**
 * 班车服务合同子表1 包装类,返回视图层所需的字段
 *
 * @author Wang Pengfei
 * @date : 2021-01-19 10:29:14
 */
@Component
public class BusServiceContract1Wrapper implements IEntityWrapper<BusServiceContract1Entity, BusServiceContract1RequestVO, BusServiceContract1ResponseVO> {

	public static BusServiceContract1Wrapper build() {
		return new BusServiceContract1Wrapper();
 	}

    @Override
	public BusServiceContract1Entity createEntity() {
		return new BusServiceContract1Entity();
	}

	@Override
	public BusServiceContract1RequestVO createQV() {
		return new BusServiceContract1RequestVO();
	}

	@Override
	public BusServiceContract1ResponseVO createPV() {
		return new BusServiceContract1ResponseVO();
	}

	@Override
	public void selectUserName(BusServiceContract1ResponseVO responseVO) {

	}
}
