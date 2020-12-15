package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.core.mp.base.BaseEntity;
import org.springblade.core.tool.utils.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * 媒体类：修图合同 实体类
 *
 * @author 媒体类：修图合同
 * @date : 2020-12-10 19:24:45
 */
@Getter
@Setter
@TableName("mtl_edited_the_contract")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "MtlEditedTheContract对象", description = "媒体类：修图合同")
public class MtlEditedTheContractEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 甲方
	 */
    @ApiModelProperty(value="甲方")
	private String mtlPatyA;
	/**
	 * 甲方联络邮箱
	 */
    @ApiModelProperty(value="甲方联络邮箱")
	private String mtlPatyAEmail;
	/**
	 * 甲方地址
	 */
    @ApiModelProperty(value="甲方地址")
	private String mtlContactEmail;
	/**
	 * 乙方
	 */
    @ApiModelProperty(value="乙方")
	private String mtlPatyB;
	/**
	 * 乙方联络邮箱
	 */
    @ApiModelProperty(value="乙方联络邮箱")
	private String mtlPatyBEmail;
	/**
	 * 乙方住所
	 */
    @ApiModelProperty(value="乙方住所")
	private String mtlPatyBHome;
	/**
	 * 图片小样
	 */
    @ApiModelProperty(value="图片小样")
	private String mtlAdaptationIssues;
	/**
	 * 修改要求
	 */
    @ApiModelProperty(value="修改要求")
	private String mtlNameOfTheVideo;
	/**
	 * 视频内容
	 */
    @ApiModelProperty(value="视频内容")
	private String mtlVideoContent;
	/**
	 * 交付时间
	 */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="交付时间")
	private Date mtlDeliveryForm;
	/**
	 * 交付方式
	 */
    @ApiModelProperty(value="交付方式")
	private String mtlNumberOf;
	/**
	 * 制作总费用(未税额人民币)
	 */
    @ApiModelProperty(value="制作总费用(未税额人民币)")
	private BigDecimal mtlUnpaidTaxRmb;
	/**
	 * 税率
	 */
    @ApiModelProperty(value="税率")
	private Double mtlRate;
	/**
	 * 现含税金额人民币
	 */
    @ApiModelProperty(value="现含税金额人民币")
	private BigDecimal mtlTaxAmountIsRmb;
	/**
	 * 乙方公司名
	 */
    @ApiModelProperty(value="乙方公司名")
	private String mtlCompanyName;
	/**
	 * 乙方开户行
	 */
    @ApiModelProperty(value="乙方开户行")
	private String mtlWhereItIs;
	/**
	 * 乙方账号
	 */
    @ApiModelProperty(value="乙方账号")
	private String mtlAccount;
	/**
	 * 修图合同关联表1
	 */
	@ApiModelProperty(value="修图合同关联表1")
	@TableField(exist = false)
    private List<MtlEditedTheContract1Entity> mtlEditedTheContract1EntityList;
}
