package org.springblade.abutment.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.abutment.vo.EkpSynDataRequestVO;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.abutment.entity.EkpSynDataEntity;
import org.springblade.abutment.mapper.EkpSynDataMapper;
import org.springblade.abutment.service.IEkpSynDataService;
import org.springframework.stereotype.Service;

/**
 * ekp同步数据 服务实现类
 *
 * @author chenzy
 * @date : 2021-07-27 15:42:12
 */
@Service
public class EkpSynDataServiceImpl extends BaseServiceImpl<EkpSynDataMapper, EkpSynDataEntity> implements IEkpSynDataService {

	@Override
	public IPage<EkpSynDataEntity> pageList(IPage<EkpSynDataEntity> page, EkpSynDataRequestVO ekpSynData) {
		return baseMapper.pageList(page, ekpSynData);
	}
}
