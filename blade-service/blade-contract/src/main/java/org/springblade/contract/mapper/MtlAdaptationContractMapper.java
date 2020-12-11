package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.MtlAdaptationContractEntity;
import org.springblade.contract.vo.MtlAdaptationContractRequestVO;

/**
 * 媒体类：视频广告改编合同 Mapper 接口
 *
 * @author  媒体类：视频广告改编合同
 * @date : 2020-12-10 19:40:31
 */
public interface MtlAdaptationContractMapper extends BaseMapper<MtlAdaptationContractEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param mtlAdaptationContract
	 * @return
	 */
	IPage<MtlAdaptationContractEntity> pageList(IPage<MtlAdaptationContractEntity> page, MtlAdaptationContractRequestVO mtlAdaptationContract);

}
