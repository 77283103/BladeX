package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.MtlEditedTheContract1Entity;
import org.springblade.contract.vo.MtlEditedTheContract1RequestVO;
import org.springblade.contract.vo.MtlEditedTheContract1ResponseVO;
import org.springblade.core.mp.base.BaseService;

import java.util.List;

/**
 * 媒体类：修图合同关联表 服务类
 *
 * @author 媒体类：修图合同关联表
 * @date : 2020-12-11 05:00:49
 */
public interface IMtlEditedTheContract1Service extends BaseService<MtlEditedTheContract1Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param mtlEditedTheContract1
	 * @return
	 */
	IPage<MtlEditedTheContract1Entity> pageList(IPage<MtlEditedTheContract1Entity> page, MtlEditedTheContract1RequestVO mtlEditedTheContract1);
	void saveBatchByRefId(Long refId, List<MtlEditedTheContract1ResponseVO> responseVOList);

	List<MtlEditedTheContract1ResponseVO> selectRefList(Long refId);
}
