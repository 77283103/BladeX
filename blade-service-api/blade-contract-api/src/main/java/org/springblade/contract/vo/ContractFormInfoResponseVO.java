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

}
