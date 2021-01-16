package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.LyConfidentialityAgreementEntity;

/**
 * 梁艳-保密协议 Mapper 接口
 *
 * @author wd
 * @date : 2021-01-15 14:57:39
 */
public interface LyConfidentialityAgreementMapper extends BaseMapper<LyConfidentialityAgreementEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param lyConfidentialityAgreement
	 * @return
	 */
	IPage<LyConfidentialityAgreementEntity> pageList(IPage<LyConfidentialityAgreementEntity> page, LyConfidentialityAgreementEntity lyConfidentialityAgreement);

}
