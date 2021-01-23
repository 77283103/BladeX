package org.springblade.contract.wrapper;

import org.springblade.contract.entity.LaborDispatchEntity;
import org.springblade.contract.vo.LaborDispatchRequestVO;
import org.springblade.contract.vo.LaborDispatchResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springframework.stereotype.Component;


/**
 * 韩素娟劳务派遣合同模板(甲方有拼接附件） 包装类,返回视图层所需的字段
 *
 * @author wd
 * @date : 2021-01-22 15:16:18
 */
@Component
public class LaborDispatchWrapper implements IEntityWrapper<LaborDispatchEntity, LaborDispatchRequestVO, LaborDispatchResponseVO> {

	public static LaborDispatchWrapper build() {
		return new LaborDispatchWrapper();
 	}

    @Override
	public LaborDispatchEntity createEntity() {
		return new LaborDispatchEntity();
	}

	@Override
	public LaborDispatchRequestVO createQV() {
		return new LaborDispatchRequestVO();
	}

	@Override
	public LaborDispatchResponseVO createPV() {
		return new LaborDispatchResponseVO();
	}

	@Override
	public void selectUserName(LaborDispatchResponseVO responseVO) {

	}
}
