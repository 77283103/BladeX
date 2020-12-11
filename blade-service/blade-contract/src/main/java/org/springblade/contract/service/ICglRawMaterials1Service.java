package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.CglRawMaterials1RequestVO;
import org.springblade.contract.vo.CglRawMaterials1ResponseVO;
import org.springblade.contract.vo.CglTheSalesContract1ResponseVO;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.CglRawMaterials1Entity;

import java.util.List;

/**
 * 采购类：原物料-买卖合同 服务类
 *
 * @author 采购类：原物料-买卖合同
 * @date : 2020-12-10 18:54:35
 */
public interface ICglRawMaterials1Service extends BaseService<CglRawMaterials1Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param cglRawMaterials1
	 * @return
	 */
	IPage<CglRawMaterials1Entity> pageList(IPage<CglRawMaterials1Entity> page, CglRawMaterials1RequestVO cglRawMaterials1);

	void saveBatchByRefId(Long refId, List<CglRawMaterials1ResponseVO> responseVOList);

	List<CglRawMaterials1ResponseVO> selectRefList(Long refId);
}
