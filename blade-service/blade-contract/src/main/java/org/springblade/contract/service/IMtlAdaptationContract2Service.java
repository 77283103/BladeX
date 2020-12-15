package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.MtlAdaptationContract1ResponseVO;
import org.springblade.contract.vo.MtlAdaptationContract2RequestVO;
import org.springblade.contract.vo.MtlAdaptationContract2ResponseVO;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.MtlAdaptationContract2Entity;

import java.util.List;

/**
 * 媒体类：视频广告改编合同关联表2 服务类
 *
 * @author 媒体类：视频广告改编合同关联表2
 * @date : 2020-12-11 08:36:47
 */
public interface IMtlAdaptationContract2Service extends BaseService<MtlAdaptationContract2Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param mtlAdaptationContract2
	 * @return
	 */
	IPage<MtlAdaptationContract2Entity> pageList(IPage<MtlAdaptationContract2Entity> page, MtlAdaptationContract2RequestVO mtlAdaptationContract2);

	void saveBatchByRefId(Long refId, List<MtlAdaptationContract2ResponseVO> responseVOList);

	List<MtlAdaptationContract2ResponseVO> selectRefList(Long refId);
}
