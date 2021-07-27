package org.springblade.abutment.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.abutment.vo.EkpSynDataRequestVO;
import org.springblade.core.mp.base.BaseService;
import org.springblade.abutment.entity.EkpSynDataEntity;


/**
 * ekp同步数据 服务类
 *
 * @author chenzy
 * @date : 2021-07-27 15:42:12
 */
public interface IEkpSynDataService extends BaseService<EkpSynDataEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param ekpSynData
	 * @return
	 */
	IPage<EkpSynDataEntity> pageList(IPage<EkpSynDataEntity> page, EkpSynDataRequestVO ekpSynData);
}
