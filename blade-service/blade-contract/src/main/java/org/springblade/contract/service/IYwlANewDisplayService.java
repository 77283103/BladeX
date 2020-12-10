package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.YwlANewDisplayRequestVO;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.YwlANewDisplayEntity;

/**
 * 业务类：21.新陈列协议书 服务类
 *
 * @author szw
 * @date : 2020-12-07 15:37:42
 */
public interface IYwlANewDisplayService extends BaseService<YwlANewDisplayEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param ywlANewDisplay
	 * @return
	 */
	IPage<YwlANewDisplayEntity> pageList(IPage<YwlANewDisplayEntity> page, YwlANewDisplayRequestVO ywlANewDisplay);
}
