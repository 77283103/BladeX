package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.YwbUnifiedAgreementEntity;
import org.springblade.contract.vo.YwbUnifiedAgreementRequestVO;

/**
 * 2021年统一e商城平台入驻服务协议（统一经销商） Mapper 接口
 *
 * @author 2021年统一e商城平台入驻服务协议（统一经销商）
 * @date : 2020-12-18 16:03:30
 */
public interface YwbUnifiedAgreementMapper extends BaseMapper<YwbUnifiedAgreementEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param ywbUnifiedAgreement
	 * @return
	 */
	IPage<YwbUnifiedAgreementEntity> pageList(IPage<YwbUnifiedAgreementEntity> page, YwbUnifiedAgreementRequestVO ywbUnifiedAgreement);

}
