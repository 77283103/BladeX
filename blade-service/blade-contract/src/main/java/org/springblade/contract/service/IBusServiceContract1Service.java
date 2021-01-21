package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.BusServiceContract1Entity;
import org.springblade.contract.vo.BusServiceContract1ResponseVO;
import org.springblade.core.mp.base.BaseService;

import java.util.List;

/**
 * 班车服务合同子表1 服务类
 *
 * @author Wang Pengfei
 * @date : 2021-01-19 10:29:13
 */
public interface IBusServiceContract1Service extends BaseService<BusServiceContract1Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param busServiceContract1
	 * @return
	 */
	IPage<BusServiceContract1Entity> pageList(IPage<BusServiceContract1Entity> page, BusServiceContract1Entity busServiceContract1);
	void saveBatchByRefId(Long refId, List<BusServiceContract1ResponseVO> responseVOList);

	List<BusServiceContract1ResponseVO> selectRefList(Long refId);
}
