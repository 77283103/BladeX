package org.springblade.contract.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.contract.entity.RelieveEntity;
import org.springblade.contract.vo.RelieveResponseVO;

/**
 *  包装类,返回视图层所需的字段
 *
 * @author szw
 * @date : 2020-09-23 20:10:29
 */
public class RelieveWrapper extends BaseEntityWrapper<RelieveEntity, RelieveResponseVO>  {

	public static RelieveWrapper build() {
		return new RelieveWrapper();
 	}

	@Override
	public RelieveResponseVO entityVO(RelieveEntity relieve) {
		RelieveResponseVO relieveResponseVO = BeanUtil.copy(relieve, RelieveResponseVO.class);
		return relieveResponseVO;
	}

}
