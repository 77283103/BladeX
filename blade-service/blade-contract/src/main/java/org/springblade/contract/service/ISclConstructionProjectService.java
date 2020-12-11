package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.SclConstructionProjectEntity;
import org.springblade.contract.vo.SclConstructionProjectRequestVO;
import org.springblade.core.mp.base.BaseService;

/**
 * 生产类：加工承揽合同（代工合同） 服务类
 *
 * @author 生产类：加工承揽合同（代工合同）
 * @date : 2020-12-11 09:52:22
 */
public interface ISclConstructionProjectService extends BaseService<SclConstructionProjectEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param sclConstructionProject
	 * @return
	 */
	IPage<SclConstructionProjectEntity> pageList(IPage<SclConstructionProjectEntity> page, SclConstructionProjectRequestVO sclConstructionProject);
}
