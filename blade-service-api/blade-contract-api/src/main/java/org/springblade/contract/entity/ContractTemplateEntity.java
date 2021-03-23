package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springblade.core.mp.base.BaseEntity;

import java.util.List;


/**
 * 范本管理 实体类
 *
 * @author XHB
 * @date : 2020-09-24 13:57:35
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("contract_template")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Template对象", description = "范本管理")
public class ContractTemplateEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 范本名称
	 */
	@ApiModelProperty(value = "范本名称")
	private String name;
	/**
	 * 范本编号
	 */
	@ApiModelProperty(value = "范本编号")
	private String templateNumber;
	/**
	 * 所属合同大类
	 */
	@ApiModelProperty(value = "所属合同大类")
	private String contractBigCategory;
	/**
	 * 所属合同小类
	 */
	@ApiModelProperty(value = "所属合同小类")
	private String contractSmallCategory;
	/**
	 * 范本类型
	 */
	@ApiModelProperty(value = "范本类型")
	private String templateCategory;
	/**
	 * 创建单位标识
	 */
	@ApiModelProperty(value = "创建单位标识")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long createUnit;
	/**
	 * 使用范围
	 */
	@ApiModelProperty(value = "使用范围")
	private String useRange;
	/**
	 * 范本说明
	 */
	@ApiModelProperty(value = "范本说明")
	private String templateDescription;
	/**
	 * 版本号
	 */
	@ApiModelProperty(value = "版本号")
	private String recordVersion;

	/**
	 * 范本附件
	 */
	@ApiModelProperty(value = "范本附件")
	private String attachedFiles;
	/**
	 * 上级版本id
	 */
	@ApiModelProperty(value = "上级版本id")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long originalTemplateId;
	/**
	 * 关联合同
	 */
	@ApiModelProperty(value = "关联合同")
	private String  contractId;
	/**
	 * 范本状态
	 */
	@ApiModelProperty(value = "范本状态")
	private String templateStatus;
	/**
	 * 正在履行合同数量
	 */
	@ApiModelProperty(value = "正在履行合同数量")
	private Integer authenticPerformanceCount;
	/**
	 * 已完成合同数量
	 */
	@ApiModelProperty(value = "已完成合同数量")
	private Integer completedContractCount;
	/**
	 * 使用率
	 */
	@ApiModelProperty(value = "使用率")
	private String usageRate;

	/**
	 * 使用记录
	 */
	@ApiModelProperty(value = "使用记录")
	private String usageRecord;
	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注")
	private String remarks;
	/**
	 * 模板编号
	 */
	@ApiModelProperty(value = "模板编号")
	private String templateCode;
	/**
	 * 模板文件id
	 */
	@ApiModelProperty(value = "模板文件id")
	private  String templateFileId;
	/**
	 * json页面
	 */
	@ApiModelProperty(value = "json页面")
	private String json;
	/**
	 * 是否启用
	 */
	@ApiModelProperty(value = "是否启用")
	private String enabled;
	/**
	 * 范本使用的合同集合
	 */
	@ApiModelProperty(value = "范本使用的合同集合")
	@TableField(exist = false)
	private List<ContractFormInfoEntity> formInfoEntityList;
}
