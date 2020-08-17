package org.springblade.resource.feign;

import org.springblade.core.launch.constant.AppConstant;
import org.springblade.core.tool.api.R;
import org.springblade.resource.vo.FileVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 *  <类说明>   ： 文件管理
 * @author    : Mr.Feng
 * @date      : 2020-8-17 14:57
 */
@FeignClient(
	value = AppConstant.APPLICATION_RESOURCE_NAME,
	fallback = ISmsClientFallback.class
)
public interface IFileClient {

	String API_PREFIX = "/client";
	String ADD_FILE = API_PREFIX + "/add-file";


	@PostMapping(ADD_FILE)
	R save(@Valid @RequestBody FileVO file);
}
