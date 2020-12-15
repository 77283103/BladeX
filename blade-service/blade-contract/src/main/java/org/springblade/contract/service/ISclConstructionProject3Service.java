package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.SclConstructionProject3Entity;
import org.springblade.contract.vo.SclConstructionProject3RequestVO;
import org.springblade.contract.vo.SclConstructionProject3ResponseVO;
import org.springblade.core.mp.base.BaseService;

import java.util.List;

/**
 * 生产类：加工承揽合同（代工合同）关联表3 服务类
 *
 * @author 生产类：加工承揽合同（代工合同）关联表3
 * @date : 2020-12-11 10:36:43
 */
public interface ISclConstructionProject3Service extends BaseService<SclConstructionProject3Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param sclConstructionProject3
	 * @return
	 */
	IPage<SclConstructionProject3Entity> pageList(IPage<SclConstructionProject3Entity> page, SclConstructionProject3RequestVO sclConstructionProject3);
	void saveBatchByRefId(Long refId, List<SclConstructionProject3ResponseVO> responseVOList);

	List<SclConstructionProject3ResponseVO> selectRefList(Long refId);
}
