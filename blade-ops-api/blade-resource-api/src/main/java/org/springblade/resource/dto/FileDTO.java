package org.springblade.resource.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.resource.entity.FileEntity;

/**
 * 文件管理 模型DTO
 *
 * @author Feng
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FileDTO extends FileEntity {

	private static final long serialVersionUID = 1L;

}
