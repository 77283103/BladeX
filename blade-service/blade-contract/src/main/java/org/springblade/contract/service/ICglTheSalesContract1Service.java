package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.CglCategorySalesContracts1ResponseVO;
import org.springblade.contract.vo.CglTheSalesContract1RequestVO;
import org.springblade.contract.vo.CglTheSalesContract1ResponseVO;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.CglTheSalesContract1Entity;

import java.util.List;

/**
 * 采购类：新增原物料补充协议--买卖合同 服务类
 *
 * @author 采购类：新增原物料补充协议--买卖合同
 * @date : 2020-12-10 18:50:21
 */
public interface ICglTheSalesContract1Service extends BaseService<CglTheSalesContract1Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param cglTheSalesContract1
	 * @return
	 */
	IPage<CglTheSalesContract1Entity> pageList(IPage<CglTheSalesContract1Entity> page, CglTheSalesContract1RequestVO cglTheSalesContract1);

	void saveBatchByRefId(Long refId, List<CglTheSalesContract1ResponseVO> responseVOList);

	List<CglTheSalesContract1ResponseVO> selectRefList(Long refId);
}
