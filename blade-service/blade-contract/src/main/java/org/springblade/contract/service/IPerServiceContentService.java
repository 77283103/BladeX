package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.PerServiceContentRequestVO;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.PerServiceContentEntity;
import org.springblade.core.tool.api.R;

/**
 * 履约服务内容 服务类
 *
 * @author chenzy
 * @date : 2021-04-20 16:02:06
 */
public interface IPerServiceContentService extends BaseService<PerServiceContentEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param perServiceContent
	 * @return
	 */
	IPage<PerServiceContentEntity> pageList(IPage<PerServiceContentEntity> page, PerServiceContentRequestVO perServiceContent);

	/**
	 * 保存履约信息
	 * @return
	 */
	R addPerData(PerServiceContentRequestVO serviceContentRequestVO,Long contractId);


	/**
	 * 根据合同标识删除履约数据
	 * @param contractId
	 */
	void deleteByContractId(Long contractId);

}
