package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.MtbProductionContract1Entity;
import org.springblade.contract.vo.MtbProductionContract1RequestVO;
import org.springblade.contract.vo.MtbProductionContract1ResponseVO;
import org.springblade.core.mp.base.BaseService;

import java.util.List;

/**
 * 媒体类：平面广告拍摄制作合同（关联表1） 服务类
 *
 * @author 张文武
 * @date : 2021-01-04 11:27:47
 */
public interface IMtbProductionContract1Service extends BaseService<MtbProductionContract1Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param mtbProductionContract1
	 * @return
	 */
	IPage<MtbProductionContract1Entity> pageList(IPage<MtbProductionContract1Entity> page, MtbProductionContract1RequestVO mtbProductionContract1);

	void saveBatchByRefId(Long refId, List<MtbProductionContract1ResponseVO> responseVOList);

	List<MtbProductionContract1ResponseVO> selectRefList(Long refId);
}
