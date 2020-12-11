package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.MtlShootingAndProductionContractEntity;
import org.springblade.contract.vo.MtlShootingAndProductionContractRequestVO;

/**
 * 媒体类：视频广告拍摄制作合同 Mapper 接口
 *
 * @author 媒体类：视频广告拍摄制作合同
 * @date : 2020-12-10 19:36:04
 */
public interface MtlShootingAndProductionContractMapper extends BaseMapper<MtlShootingAndProductionContractEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param mtlShootingAndProductionContract
	 * @return
	 */
	IPage<MtlShootingAndProductionContractEntity> pageList(IPage<MtlShootingAndProductionContractEntity> page, MtlShootingAndProductionContractRequestVO mtlShootingAndProductionContract);

}
