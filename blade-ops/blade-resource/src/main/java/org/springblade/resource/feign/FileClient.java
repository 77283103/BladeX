package org.springblade.resource.feign;

import lombok.AllArgsConstructor;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.tool.api.R;
import org.springblade.resource.service.IFileService;
import org.springblade.resource.vo.FileVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 *  <类说明>   ： 文件远程调用服务
 * @author    : Mr.Feng
 * @date      : 2020-8-17 15:03
 */
@RestController
@AllArgsConstructor
public class FileClient implements IFileClient{

	private IFileService fileService;

	@Override
	@PostMapping(ADD_FILE)
	public R save(@Valid FileVO file) {
		if (null == file.getMultipartFile()){
			throw new ServiceException("请选择文件");
		}
		return R.data(fileService.addFile(file));
	}
}
