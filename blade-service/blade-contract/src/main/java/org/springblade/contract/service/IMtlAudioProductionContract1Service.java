package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.MtlAudioProductionContract1Entity;
import org.springblade.contract.vo.MtlAudioProductionContract1RequestVO;
import org.springblade.contract.vo.MtlAudioProductionContract1ResponseVO;
import org.springblade.core.mp.base.BaseService;

import java.util.List;

/**
 * 媒体类：音频制作合同关联表 服务类
 *
 * @author 媒体类：音频制作合同关联表
 * @date : 2020-12-11 03:30:24
 */
public interface IMtlAudioProductionContract1Service extends BaseService<MtlAudioProductionContract1Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param mtlAudioProductionContract1
	 * @return
	 */
	IPage<MtlAudioProductionContract1Entity> pageList(IPage<MtlAudioProductionContract1Entity> page, MtlAudioProductionContract1RequestVO mtlAudioProductionContract1);
	void saveBatchByRefId(Long refId, List<MtlAudioProductionContract1ResponseVO> responseVOList);

	List<MtlAudioProductionContract1ResponseVO> selectRefList(Long refId);
}
