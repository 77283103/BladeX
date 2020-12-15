package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.MtlShootingAndProductionContract1RequestVO;
import org.springblade.contract.vo.MtlShootingAndProductionContract1ResponseVO;
import org.springblade.contract.vo.SclEquipmentMaintenance1ResponseVO;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.MtlShootingAndProductionContract1Entity;

import java.util.List;

/**
 * 媒体类：视频广告拍摄制作合同关联表 服务类
 *
 * @author 媒体类：视频广告拍摄制作合同关联表
 * @date : 2020-12-11 05:30:04
 */
public interface IMtlShootingAndProductionContract1Service extends BaseService<MtlShootingAndProductionContract1Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param mtlShootingAndProductionContract1
	 * @return
	 */
	IPage<MtlShootingAndProductionContract1Entity> pageList(IPage<MtlShootingAndProductionContract1Entity> page, MtlShootingAndProductionContract1RequestVO mtlShootingAndProductionContract1);

	void saveBatchByRefId(Long refId, List<MtlShootingAndProductionContract1ResponseVO> responseVOList);

	List<MtlShootingAndProductionContract1ResponseVO> selectRefList(Long refId);

}
