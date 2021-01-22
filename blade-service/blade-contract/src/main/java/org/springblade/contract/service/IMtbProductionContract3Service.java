package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.service.impl.MtbProductionContract3ServiceImpl;
import org.springblade.contract.vo.MtbProductionContract2ResponseVO;
import org.springblade.contract.vo.MtbProductionContract3ResponseVO;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.MtbProductionContract3Entity;

import java.util.List;

/**
 * 媒体类：平面广告拍摄制作合同（关联表2） 服务类
 *
 * @author 韩杨
 * @date : 2021-01-21 11:27:01
 */
public interface IMtbProductionContract3Service extends BaseService<MtbProductionContract3Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param mtbProductionContract3
	 * @return
	 */
	IPage<MtbProductionContract3Entity> pageList(IPage<MtbProductionContract3Entity> page, MtbProductionContract3Entity mtbProductionContract3);

	void saveBatchByRefId(Long refId, List<MtbProductionContract3ResponseVO> responseVOList);

	List<MtbProductionContract3ResponseVO> selectRefList(Long refId);
}
