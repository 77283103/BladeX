package org.springblade.contract.vo;

import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springblade.contract.entity.ContractSigningEntity;
import org.springblade.resource.vo.FileVO;

import java.util.List;

/**
 * 合同签订表 返回模型VO
 *
 * @author 合同签订
 * @date : 2020-11-05 09:34:34
 */
@Getter
@Setter
@ToString
@ApiModel(description = "合同签订表返回对象")
@EqualsAndHashCode(callSuper = true)
public class ContractSigningResponseVO extends ContractSigningEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;

	/**
	 * 签订文件扫描件列表
	 */
	private List<FileVO> signingTextFileVOList;
	/**
	 * 签订附件扫描件列表
	 */
	private List<FileVO> signingAttachedFileVOList;
}
