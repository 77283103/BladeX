package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ShjbGeneralVersionEntity;
import org.springblade.contract.vo.ShjbGeneralVersionRequestVO;
import org.springblade.core.mp.base.BaseService;

/**
 * 售货机类：2020.2.24修 -售货机设备租赁合同—通用版（可销售自选产品版本）） 服务类
 *
 * @author 售货机类：2020.2.24修 -售货机设备租赁合同—通用版（可销售自选产品版本））
 * @date : 2020-12-18 16:02:26
 */
public interface IShjbGeneralVersionService extends BaseService<ShjbGeneralVersionEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param shjbGeneralVersion
	 * @return
	 */
	IPage<ShjbGeneralVersionEntity> pageList(IPage<ShjbGeneralVersionEntity> page, ShjbGeneralVersionRequestVO shjbGeneralVersion);
}
