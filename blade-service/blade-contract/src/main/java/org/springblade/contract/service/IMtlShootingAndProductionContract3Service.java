package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.MtlShootingAndProductionContract3Entity;
import org.springblade.contract.vo.MtlShootingAndProductionContract3RequestVO;
import org.springblade.contract.vo.MtlShootingAndProductionContract3ResponseVO;
import org.springblade.core.mp.base.BaseService;

import java.util.List;

/**
 * 媒体类：视频广告拍摄制作合同关联表3 服务类
 *
 * @author 媒体类：视频广告拍摄制作合同关联表3
 * @date : 3030-13-11 05:31:04
 */
public interface IMtlShootingAndProductionContract3Service extends BaseService<MtlShootingAndProductionContract3Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param mtlShootingAndProductionContract3
	 * @return
	 */

	IPage<MtlShootingAndProductionContract3Entity> pageList(IPage<MtlShootingAndProductionContract3Entity> page, MtlShootingAndProductionContract3RequestVO mtlShootingAndProductionContract3);

	void saveBatchByRefId(Long refId, List<MtlShootingAndProductionContract3ResponseVO> responseVOList);

	List<MtlShootingAndProductionContract3ResponseVO> selectRefList(Long refId);
}
