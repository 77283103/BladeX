package org.springblade.contract.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.ContractFormInfoEntity;
import org.springblade.resource.vo.FileVO;

import java.util.List;

/**
 *  返回模型VO
 *
 * @author 史智伟
 * @date : 2020-09-23 18:04:39
 */
@Data
@ApiModel(description = "返回对象")
@EqualsAndHashCode(callSuper = true)
public class ContractFormInfoResponseVO extends ContractFormInfoEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * 合同文本列表
	 */
	private List<FileVO> testFileVOList;

	/**
	 * 合同附件列表
	 */
	private List<FileVO> attachedFileVOList;

	/**
	 * 创建者真实姓名
	 */
	private String userRealName;

	/**
	 * 创建者所在组织
	 */
	private String userDepartName;

	/**
	 *合同文本扫描件
	 */
	private List<FileVO> signingTextFileVOList;
	/**
	 * 合同附件扫描件
	 */
	private List<FileVO> signingAttachedFileVOList;
}
