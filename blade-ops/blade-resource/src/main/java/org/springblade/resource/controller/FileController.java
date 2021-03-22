package org.springblade.resource.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.resource.entity.FileEntity;
import org.springblade.resource.service.IFileService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;


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

	/**
	 * 下载文件
	 */
	@GetMapping("/downloadFiles")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "下载文件", notes = "传入文件id")
	public void downloadFiles(@RequestParam String id, HttpServletResponse response) {
		try {
			FileEntity fileEntity = fileService.getById(Long.valueOf(id));
			String fileName = fileEntity.getGenerateName();

			InputStream object = fileService.getObject(fileName);
			byte buf[] = new byte[1024];
			int length = 0;
			response.reset();
			response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileEntity.getName(), "UTF-8"));
			response.setContentType("application/octet-stream");
			response.setCharacterEncoding("utf-8");
			OutputStream outputStream = response.getOutputStream();
			while ((length = object.read(buf)) > 0) {
				outputStream.write(buf, 0, length);
			}
			outputStream.close();
		} catch (Exception ex) {
			System.out.println("导出失败");
		}
	}
}
