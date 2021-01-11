package org.springblade.resource.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.minio.MinioClient;
import lombok.AllArgsConstructor;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.oss.model.BladeFile;
import org.springblade.core.oss.props.OssProperties;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.resource.builder.oss.OssBuilder;
import org.springblade.resource.entity.FileEntity;
import org.springblade.resource.mapper.FileMapper;
import org.springblade.resource.service.IFileService;
import org.springblade.resource.vo.FileVO;
import org.springblade.resource.wrapper.FileWrapper;
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
			fileVO.setName(bladeFile.getOriginalName());
			fileVO.setGenerateName(bladeFile.getName());
			fileVO.setFileSizes(this.getPrintSize(file.getSize()));
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
		/* 删除文件服务器中的文件 */
		fileEntities.forEach(fileEntity -> ossBuilder.template().removeFile(fileEntity.getGenerateName()));
		/* 删除表中数据*/
		baseMapper.deleteByIds(ids);
		return true;
	}

	@Override
	public List<FileVO> getByIds(String ids) {
		List<FileEntity> fileEntityList = baseMapper.selectBatchIds(Func.toLongList(ids));
		return FileWrapper.build().listVO(fileEntityList);
	}
	@Override
	public InputStream getObject(String fileName) {
		try {
			//MinioClient minioClient = new MinioClient(ossProperties.getEndpoint(), ossProperties.getAccessKey(), ossProperties.getSecretKey());
			//InputStream object = client.getObject("000000-"+ossProperties.getBucketName(),fileName);
			InputStream object = client.getObject(ossProperties.getBucketName(),fileName);
			return object;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 把字节数B转化为KB、MB、GB
	 * @param size 文件大小
	 * @return
	 */
	private String getPrintSize(long size) {
		/* 如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义 */
		int fileUnit = 1024;

		if (size < fileUnit) {
			return size + "B";
		} else {
			size = size / fileUnit;
		}
		/* 如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位 */
		/* 因为还没有到达要使用另一个单位的时候 */
		/* 接下去以此类推 */
		if (size < fileUnit) {
			return size + "KB";
		} else {
			size = size / fileUnit;
		}
		if (size < fileUnit) {
			/* 因为如果以MB为单位的话，要保留最后1位小数 */
			/* 因此，把此数乘以100之后再取余 */
			size = size * 100;
			return (size / 100) + "." + (size % 100) + "MB";
		} else {
			/* 否则如果要以GB为单位的，先除于1024再作同样的处理 */
			size = size * 100 / 1024;
			return (size / 100) + "." + (size % 100) + "GB";
		}
	}
}
