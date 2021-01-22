package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.LaborDispatchEntity;
import org.springblade.contract.mapper.LaborDispatchMapper;
import org.springblade.contract.service.ILaborDispatchService;
import org.springframework.stereotype.Service;

/**
 * 韩素娟劳务派遣合同模板(甲方有拼接附件） 服务实现类
 *
 * @author wd
 * @date : 2021-01-22 15:16:17
 */
@Service
public class LaborDispatchServiceImpl extends BaseServiceImpl<LaborDispatchMapper, LaborDispatchEntity> implements ILaborDispatchService {

	@Override
	public IPage<LaborDispatchEntity> pageList(IPage<LaborDispatchEntity> page, LaborDispatchEntity laborDispatch) {
		return baseMapper.pageList(page, laborDispatch);
	}
}
