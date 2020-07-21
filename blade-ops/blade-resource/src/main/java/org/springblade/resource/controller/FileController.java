package org.springblade.resource.controller;

import io.minio.MinioClient;
import io.minio.errors.*;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springblade.core.tool.api.R;
import org.springblade.resource.builder.oss.OssBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.xmlpull.v1.XmlPullParserException;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@ApiIgnore
@RestController
@AllArgsConstructor
@RequestMapping("/file")
@Api(value = "文件管理", tags = "文件管理")
public class FileController {

	/**
	 * 对象存储构建类
	 */
	private OssBuilder ossBuilder;
	/**
	 * MinIO客户端
	 */
	private MinioClient client;



	/**
	 * 此方法为测试方法
	 * 禁止使用
	 * @param url 文件地址
	 * @param file 文件
	 * @return
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws InvalidArgumentException
	 * @throws InternalException
	 * @throws NoResponseException
	 * @throws InvalidBucketNameException
	 * @throws InsufficientDataException
	 * @throws ErrorResponseException
	 */

	@PostMapping("/save-file")
	public R<Object> saveFile(String url, MultipartFile file) throws IOException, XmlPullParserException, NoSuchAlgorithmException, InvalidKeyException, InvalidArgumentException, InternalException, NoResponseException, InvalidBucketNameException, InsufficientDataException, ErrorResponseException {
		// http://39.98.158.74:9000/bladex/upload/20200714/1907c32da2e94e5fa40b30609c84f573.docx
		/**
		 *
		 * 此方法禁止使用
		 * 待封装
		 *
		 */
		InputStream stream = file.getInputStream();
		client.putObject("bladex", "/upload/20200714/1907c32da2e94e5fa40b30609c84f573.docx", stream, (long) stream.available(), null, null, "application/octet-stream");
		return R.success("保存成功");
	}
}
