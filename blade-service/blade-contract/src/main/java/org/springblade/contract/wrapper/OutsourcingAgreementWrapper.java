package org.springblade.contract.wrapper;


import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.OutsourcingAgreementEntity;
import org.springblade.contract.vo.OutsourcingAgreementRequestVO;
import org.springblade.contract.vo.OutsourcingAgreementResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * 作 业 外 包 协 议 包装类,返回视图层所需的字段
 *
 * @author 王策
 * @date : 2021-01-20 13:42:17
 */
@Component
public class OutsourcingAgreementWrapper implements IEntityWrapper<OutsourcingAgreementEntity, OutsourcingAgreementRequestVO, OutsourcingAgreementResponseVO> {

	public static OutsourcingAgreementWrapper build() {
		return new OutsourcingAgreementWrapper();
 	}

    @Override
	public OutsourcingAgreementEntity createEntity() {
		return new OutsourcingAgreementEntity();
	}

	@Override
	public OutsourcingAgreementRequestVO createQV() {
		return new OutsourcingAgreementRequestVO();
	}

	@Override
	public OutsourcingAgreementResponseVO createPV() {
		return new OutsourcingAgreementResponseVO();
	}

	@Override
	public void selectUserName(OutsourcingAgreementResponseVO responseVO) {

	}

	@Override
	public OutsourcingAgreementRequestVO entityQV(OutsourcingAgreementEntity entity) {
		return null;
	}

	@Override
	public OutsourcingAgreementResponseVO entityPV(OutsourcingAgreementEntity entity) {
		return null;
	}

	@Override
	public OutsourcingAgreementEntity QVEntity(OutsourcingAgreementRequestVO requestVO) {
		return null;
	}

	@Override
	public OutsourcingAgreementEntity PVEntity(OutsourcingAgreementResponseVO responseVO) {
		return null;
	}

	@Override
	public List<OutsourcingAgreementRequestVO> entityQVList(List<OutsourcingAgreementEntity> list) {
		return null;
	}

	@Override
	public List<OutsourcingAgreementResponseVO> entityPVList(List<OutsourcingAgreementEntity> list) {
		return null;
	}

	@Override
	public List<OutsourcingAgreementEntity> QVEntityList(List<OutsourcingAgreementRequestVO> list) {
		return null;
	}

	@Override
	public List<OutsourcingAgreementEntity> PVEntityList(List<OutsourcingAgreementResponseVO> list) {
		return null;
	}

	@Override
	public IPage<OutsourcingAgreementResponseVO> entityPVPage(IPage<OutsourcingAgreementEntity> page) {
		return null;
	}
}
