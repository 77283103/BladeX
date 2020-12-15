package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.MtlShootingAndProductionContract2RequestVO;
import org.springblade.contract.vo.MtlShootingAndProductionContract2ResponseVO;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.MtlShootingAndProductionContract2Entity;

import java.util.List;

/**
 * 媒体类：视频广告拍摄制作合同关联表2 服务类
 *
 * @author 媒体类：视频广告拍摄制作合同关联表2
 * @date : 2020-12-11 05:31:04
 */
public interface IMtlShootingAndProductionContract2Service extends BaseService<MtlShootingAndProductionContract2Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param mtlShootingAndProductionContract2
	 * @return
	 */

	IPage<MtlShootingAndProductionContract2Entity> pageList(IPage<MtlShootingAndProductionContract2Entity> page, MtlShootingAndProductionContract2RequestVO mtlShootingAndProductionContract2);

	void saveBatchByRefId(Long refId, List<MtlShootingAndProductionContract2ResponseVO> responseVOList);

	List<MtlShootingAndProductionContract2ResponseVO> selectRefList(Long refId);
}
