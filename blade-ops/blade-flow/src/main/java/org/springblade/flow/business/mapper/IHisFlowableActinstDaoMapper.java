package org.springblade.flow.business.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 删除历史节点信息
 *
 * @author : 史智伟
 * @date : 2020-8-26
 */
@Mapper
public interface IHisFlowableActinstDaoMapper {

	/**
	 * 根据id删除节点信息
	 * @param id id
	 */
	void deleteHisActinstsById(@Param("id") String id) ;
}
