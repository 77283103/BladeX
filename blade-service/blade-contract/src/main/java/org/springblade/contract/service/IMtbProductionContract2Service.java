package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.MtbProductionContract2Entity;
import org.springblade.contract.vo.MtbProductionContract2RequestVO;
import org.springblade.contract.vo.MtbProductionContract2ResponseVO;
import org.springblade.core.mp.base.BaseService;

import java.util.List;

/**
 * 媒体类：平面广告拍摄制作合同（关联表2） 服务类
 *
 * @author 张文武
 * @date : 2021-01-04 11:24:01
 */
public interface IMtbProductionContract2Service extends BaseService<MtbProductionContract2Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param mtbProductionContract2
	 * @return
	 */
	IPage<MtbProductionContract2Entity> pageList(IPage<MtbProductionContract2Entity> page, MtbProductionContract2RequestVO mtbProductionContract2);

	void saveBatchByRefId(Long refId, List<MtbProductionContract2ResponseVO> responseVOList);

	List<MtbProductionContract2ResponseVO> selectRefList(Long refId);
}
