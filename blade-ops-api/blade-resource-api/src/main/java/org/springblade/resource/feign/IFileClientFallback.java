package org.springblade.resource.feign;

import org.springblade.core.tool.api.R;
import org.springblade.resource.vo.FileVO;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

/**
 *  <类说明>   ： 远程调用失败处理类
 * @author    : Mr.Feng
 * @date      : 2020-8-17 15:02
 */
public class IFileClientFallback implements IFileClient {

	@Override
	public R save(@Valid MultipartFile file) {
		return R.fail("远程调用失败");
	}

	@Override
	public R remove(String ids) {
		return R.fail("远程调用失败");
	}
}
