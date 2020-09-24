package org.springblade.contract.wrapper;

import org.springblade.contract.entity.ChangeEntity;
import org.springblade.contract.vo.ChangeResponseVO;
import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;

/**
 * 合同变更 包装类,返回视图层所需的字段
 *
 * @author szw
 * @date : 2020-09-23 19:24:50
 */
public class ChangeWrapper extends BaseEntityWrapper<ChangeEntity, ChangeResponseVO> {

	public static ChangeWrapper build() {
		return new ChangeWrapper();
 	}

	@Override
	public ChangeResponseVO entityVO(ChangeEntity change) {
		ChangeResponseVO changeResponseVO = BeanUtil.copy(change, ChangeResponseVO.class);


		return changeResponseVO;
	}

}
