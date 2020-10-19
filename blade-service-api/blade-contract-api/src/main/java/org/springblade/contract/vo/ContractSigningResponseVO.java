package org.springblade.contract.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.contract.entity.ContractSigningEntity;
import io.swagger.annotations.ApiModel;
import org.springblade.resource.vo.FileVO;

import java.util.List;

/**
 * 合同签订表 返回模型VO
 *
 * @author liyj
 * @date : 2020-09-23 19:27:06
 */
@Getter
@Setter
@ApiModel(description = "合同签订表返回对象")
@EqualsAndHashCode(callSuper = true)
public class ContractSigningResponseVO extends ContractSigningEntity {

	private static final long serialVersionUID = 1L;

	/**
	 *合同文本扫描件
	 */
	private List<FileVO> signingTextFileVOList;
	/**
	 * 合同附件扫描件
	 */
	private List<FileVO> signingAttachedFileVOList;
}
