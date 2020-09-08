package org.springblade.resource.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.resource.entity.FileEntity;

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

}
