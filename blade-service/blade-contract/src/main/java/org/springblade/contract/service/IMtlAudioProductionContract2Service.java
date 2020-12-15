package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.MtlAudioProductionContract2Entity;
import org.springblade.contract.vo.MtlAudioProductionContract2RequestVO;
import org.springblade.contract.vo.MtlAudioProductionContract2ResponseVO;
import org.springblade.core.mp.base.BaseService;

import java.util.List;

/**
 * 媒体类：音频制作合同关联表2 服务类
 *
 * @author 媒体类：音频制作合同关联表2
 * @date : 2020-12-11 03:31:36
 */
public interface IMtlAudioProductionContract2Service extends BaseService<MtlAudioProductionContract2Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param mtlAudioProductionContract2
	 * @return
	 */
	IPage<MtlAudioProductionContract2Entity> pageList(IPage<MtlAudioProductionContract2Entity> page, MtlAudioProductionContract2RequestVO mtlAudioProductionContract2);
	void saveBatchByRefId(Long refId, List<MtlAudioProductionContract2ResponseVO> responseVOList);

	List<MtlAudioProductionContract2ResponseVO> selectRefList(Long refId);
}
