package org.springblade.resource.feign;

import org.springblade.core.tool.api.R;
import org.springblade.resource.entity.FileEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 *  <类说明>   ： 远程调用失败处理类
 * @author    : Mr.Feng
 * @date      : 2020-8-17 15:02
 */
@Component
public class IFileClientFallback implements IFileClient {

	@Override
	public R save(@RequestPart(value = "file") MultipartFile file) {
		return R.fail("远程调用失败");
	}

	@Override
	public R remove(List<Long> ids) {
		return R.fail("远程调用失败");
	}

	@Override
	public R getByIds(String ids) {
		return R.fail("远程调用失败");
	}

	@Override
	public R<FileEntity> getById(Long id) {
		return R.fail("远程调用失败");
	}
}
