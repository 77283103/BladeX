package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.YwlANewDisplayRequestVO;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.YwlAnewDisplayEntity;
import org.springblade.contract.mapper.YwlANewDisplayMapper;
import org.springblade.contract.service.IYwlANewDisplayService;
import org.springframework.stereotype.Service;

/**
 * 业务类：21.新陈列协议书 服务实现类
 *
 * @author szw
 * @date : 2020-12-07 15:37:42
 */
@Service
public class YwlANewDisplayServiceImpl extends BaseServiceImpl<YwlANewDisplayMapper, YwlAnewDisplayEntity> implements IYwlANewDisplayService {

	@Override
	public IPage<YwlAnewDisplayEntity> pageList(IPage<YwlAnewDisplayEntity> page, YwlANewDisplayRequestVO ywlANewDisplay) {
		return baseMapper.pageList(page, ywlANewDisplay);
	}
}
