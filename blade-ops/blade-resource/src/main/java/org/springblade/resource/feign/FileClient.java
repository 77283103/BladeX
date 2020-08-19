package org.springblade.resource.feign;

import lombok.AllArgsConstructor;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.resource.service.IFileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	public R save(@Valid MultipartFile file) {
		return R.data(fileService.addFile(file));
	}

	@Override
	@PostMapping(DEL_FILE)
	public R remove(String ids) {
		return R.status(fileService.del(Func.toLongList(ids)));
	}
}
