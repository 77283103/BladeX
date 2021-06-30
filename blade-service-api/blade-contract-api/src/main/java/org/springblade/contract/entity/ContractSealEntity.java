package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.core.mp.base.BaseEntity;


/**
 * 统一子公司（签章申请单位） 实体类
 *
 * @author xhb
 * @date : 2021-06-16 16:10:58
 */
@Getter
@Setter
@TableName("contract_seal")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ContractSeal对象", description = "统一子公司（签章申请单位）")
public class ContractSealEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 序号
	 */
	@ApiModelProperty(value = "序号")
	private String fdSeq;
	/**
	 * 企业编号
	 */
	@ApiModelProperty(value = "企业编号")
	private String fdFactno;
	/**
	 * 单位名称
	 */
	@ApiModelProperty(value = "单位名称")
	private String fdFactname;
	/**
	 * 单位编号
	 */
	@ApiModelProperty(value = "单位编号")
	private String fdTaxno;

}
