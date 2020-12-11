package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.SclConstructionProject1Entity;
import org.springblade.contract.vo.SclConstructionProject1RequestVO;
import org.springblade.contract.vo.SclConstructionProject1ResponseVO;
import org.springblade.core.mp.base.BaseService;

import java.util.List;

/**
 * 生产类：加工承揽合同（代工合同）关联表 服务类
 *
 * @author 生产类：加工承揽合同（代工合同）关联表
 * @date : 2020-12-11 10:10:13
 */
public interface ISclConstructionProject1Service extends BaseService<SclConstructionProject1Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param sclConstructionProject1
	 * @return
	 */
	IPage<SclConstructionProject1Entity> pageList(IPage<SclConstructionProject1Entity> page, SclConstructionProject1RequestVO sclConstructionProject1);
	void saveBatchByRefId(Long refId, List<SclConstructionProject1ResponseVO> responseVOList);

	List<SclConstructionProject1ResponseVO> selectRefList(Long refId);
}
