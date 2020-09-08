package org.springblade.flow.business.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 删除运行时节点信息
 *
 * @author : 史智伟
 * @date : 2020-8-26
 */
@Mapper
public interface IRunFlowableActinstDaoMapper {

	/**
	 * 根据id删除节点信息
	 * @param id id
	 */
	void deleteRunActinstsById (@Param("id") String id) ;
}
