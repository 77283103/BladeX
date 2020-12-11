package org.springblade.contract.entity;

import org.springframework.format.annotation.DateTimeFormat;
import org.springblade.core.tool.utils.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springblade.core.mp.base.BaseEntity;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * 采购类：原物料-买卖合同 实体类
 *
 * @author 王策
 * @date : 2020-12-10 19:17:21
 */
@Getter
@Setter
@TableName("cgl_raw_materials")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "CglRawMaterials对象", description = "采购类：原物料-买卖合同")
public class CglRawMaterialsEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 买方
	 */
    @ApiModelProperty(value="买方")
	private String cglBuyer;
	/**
	 * 买方地址
	 */
    @ApiModelProperty(value="买方地址")
	private String cglBuyerAddress;
	/**
	 * 买方联系人
	 */
    @ApiModelProperty(value="买方联系人")
	private String cglBuyerContact;
	/**
	 * 买方联系方式
	 */
    @ApiModelProperty(value="买方联系方式")
	private String cglContactInformation;
	/**
	 * 卖方
	 */
    @ApiModelProperty(value="卖方")
	private String cglSeller;
	/**
	 * 卖方地址
	 */
    @ApiModelProperty(value="卖方地址")
	private String cglSellerAddress;
	/**
	 * 卖方联系人
	 */
    @ApiModelProperty(value="卖方联系人")
	private String cglSellerContact;
	/**
	 * 卖方联系方式
	 */
    @ApiModelProperty(value="卖方联系方式")
	private String cglSellerContactInformation;
	/**
	 * 甲方指定邮箱
	 */
    @ApiModelProperty(value="甲方指定邮箱")
	private String cglAEmailAddress;
	/**
	 * 乙方指定邮箱
	 */
    @ApiModelProperty(value="乙方指定邮箱")
	private String cglBEmailAddress;
	/**
	 * 甲方传真号码
	 */
    @ApiModelProperty(value="甲方传真号码")
	private String cglANumber;
	/**
	 * 乙方传真号码
	 */
    @ApiModelProperty(value="乙方传真号码")
	private String cglBNumber;
	/**
	 * （第四.4.3项）其他约定
	 */
    @ApiModelProperty(value="（第四.4.3项）其他约定")
	private String cglOtherConventions1;
	/**
	 * （第六.6.14项）其他约定
	 */
    @ApiModelProperty(value="（第六.6.14项）其他约定")
	private String cglOtherConventions2;
	/**
	 * 乙方开户行名称
	 */
    @ApiModelProperty(value="乙方开户行名称")
	private String cglNameOfBank;
	/**
	 * 乙方账号
	 */
    @ApiModelProperty(value="乙方账号")
	private String cglAccount;
	/**
	 * 乙方账户名称
	 */
    @ApiModelProperty(value="乙方账户名称")
	private String cglNameOfTheAccount;
	/**
	 * 第九.9.13项）其他约定
	 */
    @ApiModelProperty(value="第九.9.13项）其他约定")
	private String cglOtherConventions3;
	/**
	 * （第十一.11.2项）其他约定
	 */
    @ApiModelProperty(value="（第十一.11.2项）其他约定")
	private String cglOtherConventions4;

}
