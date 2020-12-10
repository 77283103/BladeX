package org.springblade.resource.feign;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import io.swagger.annotations.ApiParam;
import org.springblade.core.launch.constant.AppConstant;
import org.springblade.core.tool.api.R;
import org.springblade.resource.Config.FeignConfig;
import org.springblade.resource.vo.FileVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

/**
 *  <类说明>   ： 文件管理
 * @author    : Mr.Feng
 * @date      : 2020-8-17 14:57
 */
@FeignClient(
	value = AppConstant.APPLICATION_RESOURCE_NAME,
	//fallback = IFileClientFallback.class,
	configuration = FeignConfig.class
)
public interface IFileClient {

	String API_PREFIX = "/client";
	String ADD_FILE = API_PREFIX + "/file-add";
	String DEL_FILE = API_PREFIX + "/file-remove";
	String FILES_INFO = API_PREFIX + "/files-info";


	/**
	 * 新增文件
	 * @param file 文件
	 * @return R
	 */
	@PostMapping(value = "/client/file-add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	R<FileVO> save(@RequestPart(value = "file") MultipartFile file);

	/**
	 * 删除文件
	 * @param ids id以逗号分隔
	 * @return R
	 */
	@PostMapping(DEL_FILE)
	R remove(@ApiParam(value = "主键集合", required = true) @RequestParam List<Long> ids);

	/**
	 * 根据文件ids查询
	 * @param ids id以逗号分隔
	 * @return R
	 */
	@GetMapping(FILES_INFO)
	R<List<FileVO>> getByIds(@ApiParam(value = "主键集合", required = true) @RequestParam String ids);

}
