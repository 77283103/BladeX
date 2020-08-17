package org.springblade.resource.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.resource.entity.FileEntity;
import org.springblade.resource.vo.FileVO;

/**
 * 文件管理 包装类,返回视图层所需的字段
 *
 * @author Feng
 */
public class FileWrapper extends BaseEntityWrapper<FileEntity, FileVO>  {

	public static FileWrapper build() {
		return new FileWrapper();
 	}

	@Override
	public FileVO entityVO(FileEntity file) {
		FileVO fileVO = BeanUtil.copy(file, FileVO.class);

		//User createUser = UserCache.getUser(file.getCreateUser());
		//User updateUser = UserCache.getUser(file.getUpdateUser());
		//fileVO.setCreateUserName(createUser.getName());
		//fileVO.setUpdateUserName(updateUser.getName());

		return fileVO;
	}

}
