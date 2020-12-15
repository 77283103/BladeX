package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.MtlAdaptationContract1RequestVO;
import org.springblade.contract.vo.MtlAdaptationContract1ResponseVO;
import org.springblade.contract.vo.SclEquipmentMaintenance1ResponseVO;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.MtlAdaptationContract1Entity;

import java.util.List;

/**
 * 媒体类：视频广告改编合同关联表 服务类
 *
 * @author 媒体类：视频广告改编合同关联表
 * @date : 2020-12-11 08:36:15
 */
public interface IMtlAdaptationContract1Service extends BaseService<MtlAdaptationContract1Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param mtlAdaptationContract1
	 * @return
	 */
	IPage<MtlAdaptationContract1Entity> pageList(IPage<MtlAdaptationContract1Entity> page, MtlAdaptationContract1RequestVO mtlAdaptationContract1);

	void saveBatchByRefId(Long refId, List<MtlAdaptationContract1ResponseVO> responseVOList);

	List<MtlAdaptationContract1ResponseVO> selectRefList(Long refId);
}
