package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.LaborDispatchEntity;

/**
 * 韩素娟劳务派遣合同模板(甲方有拼接附件） 服务类
 *
 * @author wd
 * @date : 2021-01-22 15:16:16
 */
public interface ILaborDispatchService extends BaseService<LaborDispatchEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param laborDispatch
	 * @return
	 */
	IPage<LaborDispatchEntity> pageList(IPage<LaborDispatchEntity> page, LaborDispatchEntity laborDispatch);
}
