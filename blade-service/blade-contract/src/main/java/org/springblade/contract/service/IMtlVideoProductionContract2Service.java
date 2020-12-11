package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.MtlVideoProductionContract2Entity;
import org.springblade.contract.vo.MtlVideoProductionContract2RequestVO;
import org.springblade.contract.vo.MtlVideoProductionContract2ResponseVO;
import org.springblade.core.mp.base.BaseService;

import java.util.List;

/**
 * 媒体类：视频制作合同关联表2 服务类
 *
 * @author 媒体类：视频制作合同关联表2
 * @date : 2020-12-11 08:48:37
 */
public interface IMtlVideoProductionContract2Service extends BaseService<MtlVideoProductionContract2Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param mtlVideoProductionContract2
	 * @return
	 */
	IPage<MtlVideoProductionContract2Entity> pageList(IPage<MtlVideoProductionContract2Entity> page, MtlVideoProductionContract2RequestVO mtlVideoProductionContract2);
	void saveBatchByRefId(Long refId, List<MtlVideoProductionContract2ResponseVO> responseVOList);

	List<MtlVideoProductionContract2ResponseVO> selectRefList(Long refId);
}
