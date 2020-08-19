package org.springblade.resource.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.minio.MinioClient;
import lombok.AllArgsConstructor;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.oss.model.BladeFile;
import org.springblade.core.oss.props.OssProperties;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.resource.builder.oss.OssBuilder;
import org.springblade.resource.entity.FileEntity;
import org.springblade.resource.mapper.FileMapper;
import org.springblade.resource.service.IFileService;
import org.springblade.resource.vo.FileVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * 文件管理 服务实现类
 *
 * @author Feng
 */
@Service
@AllArgsConstructor
public class FileServiceImpl extends BaseServiceImpl<FileMapper, FileEntity> implements IFileService {

	private OssBuilder ossBuilder;
	/**
	 * MinIO客户端
	 */
	private MinioClient client;
	/**
	 * 配置类
	 */
	private OssProperties ossProperties;

	@Override
	public IPage<FileEntity> pageList(IPage<FileEntity> page, FileEntity file) {
		return baseMapper.pageList(page, file);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public FileVO addFile(MultipartFile file) {
		try {
			FileVO fileVO = new FileVO();
			/* 上传文件 */
			BladeFile bladeFile = ossBuilder.template().putFile(file.getOriginalFilename(), file.getInputStream());
			/* 保存文件信息 */
			BeanUtil.copy(bladeFile,fileVO);
			super.save(fileVO);
			return fileVO;
		} catch (Exception e) {
			log.error("文件保存失败", e);
			throw new ServiceException("文件保存失败");
		}
	}

	@Override
	public void updateFile(String url, MultipartFile file) {
		/* 去掉地址中的前半部分 */
		String replace = url.replace(ossProperties.getEndpoint(), "");
		InputStream stream = null;
		try {
			stream = file.getInputStream();
			client.putObject(ossProperties.getBucketName(), replace, stream, (long) stream.available(), null, null, "application/octet-stream");
		} catch (Exception e) {
			log.error("文件更新失败", e);
			throw new ServiceException("文件更新失败");
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean del(List<Long> ids) {
		// 所有要删除的对象
		List<FileEntity> fileEntities = baseMapper.selectBatchIds(ids);
		fileEntities.forEach(fileEntity -> {
			ossBuilder.template().removeFile(fileEntity.getName());
		});
		baseMapper.deleteBatchIds(ids);
		return true;
	}
}
