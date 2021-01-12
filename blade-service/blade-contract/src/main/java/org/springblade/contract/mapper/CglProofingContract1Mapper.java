package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.CglProofingContract1Entity;

/**
 * cgl_proofing_contract1 Mapper 接口
 *
 * @author cglProofingContract1
 * @date : 2021-01-12 13:48:12
 */
public interface CglProofingContract1Mapper extends BaseMapper<CglProofingContract1Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param cglProofingContract1
	 * @return
	 */
	IPage<CglProofingContract1Entity> pageList(IPage<CglProofingContract1Entity> page, CglProofingContract1Entity cglProofingContract1);

}
