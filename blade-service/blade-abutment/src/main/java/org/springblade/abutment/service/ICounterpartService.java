package org.springblade.abutment.service;

import org.springblade.abutment.entity.CounterpartEntity;
import org.springblade.abutment.vo.CounterpartVo;
import org.springblade.core.tool.api.R;

import java.util.List;

/**
 * @author xhbbo
 * 相对方更新增量接口
 */
public interface ICounterpartService {
	/**
	 * 获取依据接口的token
	 * @return
	 */
	String getToken() throws Exception;
	/**
	 * 相对方新增信息获取
	 * @return 返回相对方数据
	 * @throws Exception 抛出异常
	 */
	List<CounterpartEntity> getInsert(CounterpartEntity entity) throws Exception;
	/**
	 * 相对方跟新信息获取
	 * @return 返回相对方数据
	 * @throws Exception 抛出异常
	 */
	List<CounterpartEntity> getUpdate(CounterpartEntity entity) throws Exception;

	/**
	 * 相对方跟新和更新数据
	 * @return 返回相对方数据
	 * @throws Exception 抛出异常
	 */
	R<CounterpartVo> getInsOrUp(CounterpartEntity entity) throws Exception;
	/**
	 * 保存数据
	 * @param entity vo
	 * @return 状态
	 */
	boolean insOrUp(CounterpartEntity entity);
}
