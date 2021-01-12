package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.CglProofingContract1Entity;
import org.springblade.contract.vo.CglProofingContract1ResponseVO;
import org.springblade.core.mp.base.BaseService;

import java.util.List;

/**
 * cgl_proofing_contract1 服务类
 *
 * @author cglProofingContract1
 * @date : 2021-01-12 13:48:13
 */
public interface ICglProofingContract1Service extends BaseService<CglProofingContract1Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param cglProofingContract1
	 * @return
	 */
	IPage<CglProofingContract1Entity> pageList(IPage<CglProofingContract1Entity> page, CglProofingContract1Entity cglProofingContract1);
	void saveBatchByRefId(Long refId, List<CglProofingContract1ResponseVO> responseVOList);

	List<CglProofingContract1ResponseVO> selectRefList(Long refId);
}
