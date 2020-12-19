package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.CglSalesContract1Entity;
import org.springblade.contract.vo.CglSalesContract1RequestVO;
import org.springblade.contract.vo.CglSalesContract1ResponseVO;
import org.springblade.core.mp.base.BaseService;

import java.util.List;

/**
 * 采购类：买卖合同（国内设备购买）附表 服务类
 *
 * @author 王策
 * @date : 2020-12-18 16:12:25
 */
public interface ICglSalesContract1Service extends BaseService<CglSalesContract1Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param cglSalesContract1
	 * @return
	 */
	IPage<CglSalesContract1Entity> pageList(IPage<CglSalesContract1Entity> page, CglSalesContract1RequestVO cglSalesContract1);

	void saveBatchByRefId(Long refId, List<CglSalesContract1ResponseVO> responseVOList);

	List<CglSalesContract1ResponseVO> selectRefList(Long refId);
}
