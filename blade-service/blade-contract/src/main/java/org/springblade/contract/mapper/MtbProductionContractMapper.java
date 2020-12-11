package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.MtbProductionContractEntity;
import org.springblade.contract.vo.MtbProductionContractRequestVO;

/**
 * 媒体类：平面广告拍摄制作合同 Mapper 接口
 *
 * @author 王策
 * @date : 2020-12-10 19:30:53
 */
public interface MtbProductionContractMapper extends BaseMapper<MtbProductionContractEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param mtbProductionContract
	 * @return
	 */
	IPage<MtbProductionContractEntity> pageList(IPage<MtbProductionContractEntity> page, MtbProductionContractRequestVO mtbProductionContract);

}
