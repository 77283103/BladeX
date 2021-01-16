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
 * 梁艳-保密协议（三方） 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author 王策
 * @date : 2021-01-15 15:36:27
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "梁艳-保密协议（三方）请求对象")
public class ConfidentialityAgreementRequestVO extends BaseEntity {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="贸易方(乙方)")
	private String tradeSide;

    @ApiModelProperty(value="生产方(丙方)")
	private String manufacturer;

    @ApiModelProperty(value="涉及产品的名称")
	private String productInvolved;

    @ApiModelProperty(value="涉及知识产权、保密信息内容")
	private String contentsInformation;

    @ApiModelProperty(value="涉及产品的名称2")
	private String productInvolvedA;

    @ApiModelProperty(value="涉及知识产权、保密信息内容2")
	private String contentsInformationA;

    @ApiModelProperty(value="所有权主体确认")
	private String ownershipSubject;

    @ApiModelProperty(value="若同时和什么等与甲方企业经营同类或相似业务品牌产品相关企业的任何一公司存在交易往来")
	private String blankField;

    @ApiModelProperty(value="其他约定")
	private String otherAgreements;

    @ApiModelProperty(value="本协议经三方盖章后自时期生效")
	private Date specificDate;

}
