package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springblade.core.mp.base.BaseEntity;

import java.math.BigDecimal;


/**
 *  实体类
 *
 * @author 史智伟
 * @date : 2020-09-23 18:04:37
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("contract_form_info")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ContractFormInfo对象", description = "")
public class ContractFormInfoEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 合同名称
	 */
	@ApiModelProperty(value = "合同名称")
	private String contractName;
	/**
	 * 合同编号
	 */
	@ApiModelProperty(value = "合同编号")
	private String contractNumber;
	/**
	 * 合同文本
	 */
	@ApiModelProperty(value = "合同文本")
	private String textFile;
	/**
	 * 合同附件
	 */
	@ApiModelProperty(value = "合同附件")
	private String attachedFiles;
	/**
	 * 币种
	 */
	@ApiModelProperty(value = "币种")
	private String currencyCategory;
	/**
	 * 合同金额
	 */
	@ApiModelProperty(value = "合同金额")
	private BigDecimal contractAmount;
	/**
	 * 变更状态
	 */
	@ApiModelProperty(value = "变更状态")
	private String changeCategory;
	/**
	 * 变更合同ID
	 */
	@ApiModelProperty(value = "变更合同ID")
	private Long changeContractId;
	/**
	 * 合同节点状态
	 */
	@ApiModelProperty(value = "合同节点状态")
	private String contractStatus;
	/**
	 * 合同审核状态
	 */
	@ApiModelProperty(value = "合同审核状态")
	private String submitStatus;
	/**
	 * 合同来源
	 */
	@ApiModelProperty(value = "合同来源")
	private String contractSoure;
	/**
	 * 合同文件导出状态，是否导出
	 */
	@ApiModelProperty(value = "合同文件导出状态，是否导出")
	private String fileExportCategory;
	/**
	 * 合同一级分类
	 */
	@ApiModelProperty(value = "合同一级分类")
	private String contractBigCategory;
	/**
	 * 合同二级分类
	 */
	@ApiModelProperty(value = "合同二级分类")
	private String contractSmallCategory;

}
