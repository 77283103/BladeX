package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.SclConstructionProject2Entity;
import org.springblade.contract.vo.SclConstructionProject2RequestVO;
import org.springblade.contract.vo.SclConstructionProject2ResponseVO;
import org.springblade.core.mp.base.BaseService;

import java.util.List;

/**
 * 生产类：加工承揽合同（代工合同）关联表2 服务类
 *
 * @author 生产类：加工承揽合同（代工合同）关联表2
 * @date : 2020-12-11 10:22:08
 */
public interface ISclConstructionProject2Service extends BaseService<SclConstructionProject2Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param sclConstructionProject2
	 * @return
	 */
	IPage<SclConstructionProject2Entity> pageList(IPage<SclConstructionProject2Entity> page, SclConstructionProject2RequestVO sclConstructionProject2);
	void saveBatchByRefId(Long refId, List<SclConstructionProject2ResponseVO> responseVOList);

	List<SclConstructionProject2ResponseVO> selectRefList(Long refId);
}
