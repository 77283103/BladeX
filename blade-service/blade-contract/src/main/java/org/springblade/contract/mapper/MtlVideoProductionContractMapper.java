package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.MtlVideoProductionContractEntity;
import org.springblade.contract.vo.MtlVideoProductionContractRequestVO;

/**
 * 媒体类：视频制作合同 Mapper 接口
 *
 * @author 媒体类：视频制作合同
 * @date : 2020-12-10 19:31:01
 */
public interface MtlVideoProductionContractMapper extends BaseMapper<MtlVideoProductionContractEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param mtlVideoProductionContract
	 * @return
	 */
	IPage<MtlVideoProductionContractEntity> pageList(IPage<MtlVideoProductionContractEntity> page, MtlVideoProductionContractRequestVO mtlVideoProductionContract);

}
