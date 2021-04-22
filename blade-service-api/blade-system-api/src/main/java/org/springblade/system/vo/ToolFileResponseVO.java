package org.springblade.system.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.resource.vo.FileVO;
import org.springblade.system.entity.ToolFileEntity;
import io.swagger.annotations.ApiModel;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * 工具 返回模型VO
 *
 * @author xhb
 * @date : 2021-04-22 10:09:31
 */
@Getter
@Setter
@ToString
@ApiModel(description = "工具返回对象")
@EqualsAndHashCode(callSuper = true)
public class ToolFileResponseVO extends ToolFileEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;

	/**
	 * 合同文本列表
	 */
	private List<FileVO> toolFilesList;
}
