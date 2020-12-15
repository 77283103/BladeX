package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.MtlVideoProductionContract1Entity;
import org.springblade.contract.vo.MtlVideoProductionContract1RequestVO;
import org.springblade.contract.vo.MtlVideoProductionContract1ResponseVO;
import org.springblade.core.mp.base.BaseService;

import java.util.List;

/**
 * 媒体类：视频制作合同关联表 服务类
 *
 * @author 媒体类：视频制作合同关联表
 * @date : 2020-12-11 08:47:26
 */
public interface IMtlVideoProductionContract1Service extends BaseService<MtlVideoProductionContract1Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param mtlVideoProductionContract1
	 * @return
	 */
	IPage<MtlVideoProductionContract1Entity> pageList(IPage<MtlVideoProductionContract1Entity> page, MtlVideoProductionContract1RequestVO mtlVideoProductionContract1);
	void saveBatchByRefId(Long refId, List<MtlVideoProductionContract1ResponseVO> responseVOList);

	List<MtlVideoProductionContract1ResponseVO> selectRefList(Long refId);
}
