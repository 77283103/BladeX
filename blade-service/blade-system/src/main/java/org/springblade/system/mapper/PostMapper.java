/*
 *      Copyright (c) 2018-2028, Chill Zhuang All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the dreamlu.net developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: Chill 庄骞 (smallchill@163.com)
 */
package org.springblade.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.springblade.system.entity.Post;
import org.springblade.system.vo.PostVO;

import java.util.List;

/**
 * 岗位表 Mapper 接口
 *
 * @author Chill
 */
public interface PostMapper extends BaseMapper<Post> {

	/**
	 * 分页查询
	 * @param page
	 * @param post
	 * @return
	 */
	IPage<Post> pageList(@Param("page")IPage<Post> page, @Param("post")Post post);

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param post
	 * @return
	 */
	List<PostVO> selectPostPage(IPage page, PostVO post);

	/**
	 * 获取岗位名
	 *
	 * @param ids
	 * @return
	 */
	List<String> getPostNames(Long[] ids);

	/**
	 * 根据lunid获取岗位Id
	 *
	 * @param associationId
	 * @return
	 */
	Post getPostIdByAssociationId(String associationId);

	/**
	 *
	 *批量新增
	 * @param postList
	 * @return
	 */
	boolean saveBatchPost(@Param("postList") List<Post> postList);

	/**
	 *
	 * 清理数据
	 * @return
	 */
	boolean clearDate();
	/**
	 *
	 * 清理数据
	 * @return
	 */
	boolean clearEmpty();
}
