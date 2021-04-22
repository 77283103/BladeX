package org.springblade.system.wrapper;

import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.system.vo.ToolFileResponseVO;
import org.springblade.system.vo.ToolFileRequestVO;
import org.springblade.system.entity.ToolFileEntity;
import org.springframework.stereotype.Component;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;


/**
 * 工具 包装类,返回视图层所需的字段
 *
 * @author xhb
 * @date : 2021-04-22 10:09:30
 */
@Component
public class ToolFileWrapper implements IEntityWrapper<ToolFileEntity, ToolFileRequestVO, ToolFileResponseVO> {

	public static ToolFileWrapper build() {
		return new ToolFileWrapper();
 	}

    @Override
	public ToolFileEntity createEntity() {
		return new ToolFileEntity();
	}

	@Override
	public ToolFileRequestVO createQV() {
		return new ToolFileRequestVO();
	}

	@Override
	public ToolFileResponseVO createPV() {
		return new ToolFileResponseVO();
	}

    @Override
    public void selectUserName(ToolFileResponseVO responseVO) {
    }
}
