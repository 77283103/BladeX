package org.springblade.resource.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseService;
import org.springblade.resource.entity.FileEntity;
import org.springblade.resource.vo.FileVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 文件管理 服务类
 *
 * @author Feng
 */
public interface IFileService extends BaseService<FileEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param file
	 * @return
	 */
	IPage<FileEntity> pageList(IPage<FileEntity> page, FileEntity file);

	/**
	 * 保存文件
	 * @param file 文件信息
	 * @return FileVO
	 * @throws IOException
	 */
	FileVO addFile(FileVO file);

	/**
	 * 更新文件
	 * @param url 文件地址
	 * @param file 文件
	 */
	void updateFile(String url, MultipartFile file);
}
