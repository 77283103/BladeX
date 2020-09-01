package org.springblade.flow.business.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.flow.business.vo.ProcessResponseVO;
import org.springblade.flow.core.entity.ProcessEntity;

/**
 * 流程定义信息表 包装类,返回视图层所需的字段
 *
 * @author tianah
 * @date 2020-8-27
 */
public class ProcessWrapper extends BaseEntityWrapper<ProcessEntity, ProcessResponseVO>  {

	public static ProcessWrapper build() {
		return new ProcessWrapper();
 	}

	@Override
	public ProcessResponseVO entityVO(ProcessEntity process) {
		ProcessResponseVO processResponseVO = BeanUtil.copy(process, ProcessResponseVO.class);
		return processResponseVO;
	}

}
