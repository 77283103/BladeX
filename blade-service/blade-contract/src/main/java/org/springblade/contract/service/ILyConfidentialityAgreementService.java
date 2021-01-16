package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.LyConfidentialityAgreementEntity;

/**
 * 梁艳-保密协议 服务类
 *
 * @author wd
 * @date : 2021-01-15 14:57:39
 */
public interface ILyConfidentialityAgreementService extends BaseService<LyConfidentialityAgreementEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param lyConfidentialityAgreement
	 * @return
	 */
	IPage<LyConfidentialityAgreementEntity> pageList(IPage<LyConfidentialityAgreementEntity> page, LyConfidentialityAgreementEntity lyConfidentialityAgreement);
}
