package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.SclConstructionProjectEntity;
import org.springblade.contract.mapper.SclConstructionProjectMapper;
import org.springblade.contract.service.ISclConstructionProjectService;
import org.springblade.contract.vo.SclConstructionProjectRequestVO;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 生产类：加工承揽合同（代工合同） 服务实现类
 *
 * @author 生产类：加工承揽合同（代工合同）
 * @date : 2020-12-11 09:52:23
 */
@Service
public class SclConstructionProjectServiceImpl extends BaseServiceImpl<SclConstructionProjectMapper, SclConstructionProjectEntity> implements ISclConstructionProjectService {

	@Override
	public IPage<SclConstructionProjectEntity> pageList(IPage<SclConstructionProjectEntity> page, SclConstructionProjectRequestVO sclConstructionProject) {
		return baseMapper.pageList(page, sclConstructionProject);
	}
}
