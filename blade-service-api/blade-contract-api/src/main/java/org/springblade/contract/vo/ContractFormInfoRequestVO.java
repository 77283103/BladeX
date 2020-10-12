package org.springblade.contract.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.ContractFormInfoEntity;
import org.springblade.contract.entity.ContractPerformanceEntity;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.List;

/**
 *  请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author 史智伟
 * @date : 2020-09-23 18:04:37
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "请求对象")
public class ContractFormInfoRequestVO extends ContractFormInfoEntity {

	private static final long serialVersionUID = 1L;

    @NotBlank(message = "合同名称不能为空")
    @ApiModelProperty(value="合同名称",required = true)
	private String contractName;

    @ApiModelProperty(value="合同编号")
	private String contractNumber;

    @ApiModelProperty(value="合同文本")
	private String textFile;

    @ApiModelProperty(value="合同附件")
	private String attachedFiles;

    @ApiModelProperty(value="币种")
	private String currencyCategory;

    @ApiModelProperty(value="合同金额")
	private BigDecimal contractAmount;

    @ApiModelProperty(value="变更状态")
	private String changeCategory;

    @ApiModelProperty(value="变更合同ID")
	private Long changeContractId;

    @ApiModelProperty(value="合同节点状态")
	private String contractStatus;

    @ApiModelProperty(value="合同审核状态")
	private String submitStatus;

    @ApiModelProperty(value="合同来源")
	private String contractSoure;

    @ApiModelProperty(value="合同文件导出状态，是否导出")
	private String fileExportCategory;

	@ApiModelProperty(value="合同文件导出次数统计")
    private Integer fileExportCount;

    @ApiModelProperty(value="合同一级分类")
	private String contractBigCategory;

    @ApiModelProperty(value="合同二级分类")
	private String contractSmallCategory;

	@ApiModelProperty(value="开始时间")
	private String startTime;

	@ApiModelProperty(value="结束时间")
	private String endTime;

	@ApiModelProperty(value="最低合同金额")
	private String minContractAmount;

	@ApiModelProperty(value="最高合同金额")
	private String maxContractAmount;

	@ApiModelProperty(value="相对方ids")
	private List<Long> counterpart;

	@ApiModelProperty(value="依据ids")
	private List<Long> according;

	@ApiModelProperty(value="评估id")
	private Long assessment;

	@ApiModelProperty(value="依据ids")
	private List<ContractPerformanceEntity> performanceList;


}
