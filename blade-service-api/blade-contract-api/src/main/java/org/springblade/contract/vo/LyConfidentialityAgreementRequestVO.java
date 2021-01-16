package org.springblade.contract.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.core.mp.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 梁艳-保密协议 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author wd
 * @date : 2021-01-15 14:57:38
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "梁艳-保密协议请求对象")
public class LyConfidentialityAgreementRequestVO extends BaseEntity {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="甲方联系地址")
	private String contactPartyA;

    @ApiModelProperty(value="乙方联系地址")
	private String contactPartyB;

    @ApiModelProperty(value="（1）涉及产品的名称")
	private String productNameA;

    @ApiModelProperty(value="（1）涉及知识产权、保密信息内容")
	private String productContentA;

    @ApiModelProperty(value="（2）涉及产品的名称")
	private String productNameB;

    @ApiModelProperty(value="（2）涉及知识产权、保密信息内容")
	private String productContentB;

    @ApiModelProperty(value="乙方在与甲方合作期间，若同时和[?]等与甲方企业经营")
	private String otherEnterprises;

    @ApiModelProperty(value="第六条 其他约定")
	private String otherAgreements;

    @ApiModelProperty(value="年月日")
	private Date specificDate;

}
