package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.SclProjectOutsourcingEntity;
import org.springblade.contract.mapper.SclProjectOutsourcingMapper;
import org.springblade.contract.service.ISclProjectOutsourcingService;
import org.springblade.contract.vo.SclProjectOutsourcingRequestVO;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 生产类：生产项目外包服务合同 服务实现类
 *
 * @author kx
 * @date : 2020-12-11 11:03:55
 */
@Service
public class SclProjectOutsourcingServiceImpl extends BaseServiceImpl<SclProjectOutsourcingMapper, SclProjectOutsourcingEntity> implements ISclProjectOutsourcingService {

	@Override
	public IPage<SclProjectOutsourcingEntity> pageList(IPage<SclProjectOutsourcingEntity> page, SclProjectOutsourcingRequestVO sclProjectOutsourcing) {
		return baseMapper.pageList(page, sclProjectOutsourcing);
	}
}
