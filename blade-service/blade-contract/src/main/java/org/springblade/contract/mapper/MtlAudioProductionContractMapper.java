package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.MtlAudioProductionContractEntity;
import org.springblade.contract.vo.MtlAudioProductionContractRequestVO;

/**
 * 媒体类：音频制作合同 Mapper 接口
 *
 * @author 媒体类：音频制作合同
 * @date : 2020-12-10 19:21:35
 */
public interface MtlAudioProductionContractMapper extends BaseMapper<MtlAudioProductionContractEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param mtlAudioProductionContract
	 * @return
	 */
	IPage<MtlAudioProductionContractEntity> pageList(IPage<MtlAudioProductionContractEntity> page, MtlAudioProductionContractRequestVO mtlAudioProductionContract);

}
