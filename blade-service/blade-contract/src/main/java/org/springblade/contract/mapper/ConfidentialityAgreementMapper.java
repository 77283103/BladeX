package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ConfidentialityAgreementEntity;

/**
 * 梁艳-保密协议（三方） Mapper 接口
 *
 * @author 王策
 * @date : 2021-01-15 15:36:27
 */
public interface ConfidentialityAgreementMapper extends BaseMapper<ConfidentialityAgreementEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param confidentialityAgreement
	 * @return
	 */
	IPage<ConfidentialityAgreementEntity> pageList(IPage<ConfidentialityAgreementEntity> page, ConfidentialityAgreementEntity confidentialityAgreement);

}
