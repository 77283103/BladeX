package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.CglProofingContractEntity;

/**
 * 采购类_打样合同书 Mapper 接口
 *
 * @author 采购类_打样合同书
 * @date : 2021-01-12 13:24:35
 */
public interface CglProofingContractMapper extends BaseMapper<CglProofingContractEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param cglProofingContract
	 * @return
	 */
	IPage<CglProofingContractEntity> pageList(IPage<CglProofingContractEntity> page, CglProofingContractEntity cglProofingContract);

}
