package org.springblade.resource.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseService;
import org.springblade.resource.entity.FileEntity;
import org.springblade.resource.vo.FileVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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
	FileVO addFile(MultipartFile file);

	/**
	 * 更新文件
	 * @param url 文件地址
	 * @param file 文件
	 */
	void updateFile(String url, MultipartFile file);

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	boolean del(List<Long> ids);

	/**
	 * 根据文件ids查询
	 * @param ids
	 * @return
	 */
	List<FileVO> getByIds(String ids);


	InputStream getObject(String fileName);

}
