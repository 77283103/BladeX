package org.springblade.resource.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.springblade.resource.entity.FileEntity;

import java.util.List;

/**
 * 文件管理 Mapper 接口
 *
 * @author Feng
 */
public interface FileMapper extends BaseMapper<FileEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param file
	 * @return
	 */
	IPage<FileEntity> pageList(IPage<FileEntity> page, FileEntity file);

	/**
	 * 批量删除
	 * @param ids
	 */
    void deleteByIds(@Param("ids") List<Long> ids);
}
