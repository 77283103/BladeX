package org.springblade.resource.controller;

import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.core.boot.ctrl.BladeController;

import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springframework.web.bind.annotation.*;

import org.springblade.resource.service.IFileService;
import org.springframework.web.multipart.MultipartFile;


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
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "新增", notes = "传入file")
	public R save(@Valid @RequestBody MultipartFile file) {
		return R.data(fileService.addFile(file));
	}

	/**
	 * 更新文件
	 * @param url 文件地址
	 * @param file 文件
	 * @return R
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "更新文件", notes = "传入文件和文件地址")
	public R<String> updateFile(String url, MultipartFile file) {
		fileService.updateFile(url,file);
		return R.success("保存成功");
	}

	/**
	 * 删除文件
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "删除文件", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(fileService.del(Func.toLongList(ids)));
	}

}
