package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.OutsourcingAgreementEntity;

/**
 * 作 业 外 包 协 议 Mapper 接口
 *
 * @author 王策
 * @date : 2021-01-20 13:42:14
 */
public interface OutsourcingAgreementMapper extends BaseMapper<OutsourcingAgreementEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param outsourcingAgreement
	 * @return
	 */
	IPage<OutsourcingAgreementEntity> pageList(IPage<OutsourcingAgreementEntity> page, OutsourcingAgreementEntity outsourcingAgreement);

}
