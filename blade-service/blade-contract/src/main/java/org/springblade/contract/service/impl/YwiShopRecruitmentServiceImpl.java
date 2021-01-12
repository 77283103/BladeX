package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.service.IYwiShopRecruitmentService;
import org.springblade.contract.vo.YwlShopRecruitmentRequestVO;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.YwiShopRecruitmentEntity;
import org.springblade.contract.mapper.YwlShopRecruitmentMapper;
import org.springframework.stereotype.Service;

/**
 * 业务类：14.店招合同 服务实现类
 *
 * @author szw
 * @date : 2020-12-04 19:04:56
 */
@Service
public class YwiShopRecruitmentServiceImpl extends BaseServiceImpl<YwlShopRecruitmentMapper, YwiShopRecruitmentEntity> implements IYwiShopRecruitmentService {

	@Override
	public IPage<YwiShopRecruitmentEntity> pageList(IPage<YwiShopRecruitmentEntity> page, YwlShopRecruitmentRequestVO ywlShopRecruitment) {
		return baseMapper.pageList(page, ywlShopRecruitment);
	}
}
