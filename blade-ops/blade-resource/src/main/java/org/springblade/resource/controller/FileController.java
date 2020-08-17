package org.springblade.resource.controller;

import io.minio.MinioClient;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.core.boot.ctrl.BladeController;

import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.oss.props.OssProperties;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.tool.api.R;
import org.springframework.web.bind.annotation.*;

import org.springblade.resource.vo.FileVO;
import org.springblade.resource.service.IFileService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;


/**
 * 文件管理 控制器
 *
 * @author Feng
 */
@RestController
@AllArgsConstructor
@RequestMapping("/file")
@Api(value = "文件管理", tags = "文件管理")
public class FileController extends BladeController {
	private IFileService fileService;

	/**
	 * 更新文件
	 * @param url 文件地址
	 * @param file 文件
	 * @return R
	 */
	@PostMapping("/update-file")
	@PreAuth("hasPermission('resource:file:add')")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "更新文件", notes = "传入文件和文件地址")
	public R<String> updateFile(String url, MultipartFile file) {
		fileService.updateFile(url,file);
		return R.success("保存成功");
	}

}
