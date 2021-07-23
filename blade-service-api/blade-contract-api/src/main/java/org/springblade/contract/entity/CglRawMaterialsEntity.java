package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.core.mp.base.BaseEntity;

import java.util.List;


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
    @ApiModelProperty(value="开户行名称")
	private String cglNameOfBank;
	/**
	 * 乙方账号
	 */
    @ApiModelProperty(value="账号")
	private String cglAccount;
	/**
	 * 乙方账户名称
	 */
    @ApiModelProperty(value="账户名称")
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
	/**
	 * 收付款条件
	 */
	@ApiModelProperty(value="收付款条件")
	private String cglPayment;
	/**
	 * 拼接附件
	 */
	@ApiModelProperty(value="拼接附件")
	private String annex;

	@ApiModelProperty(value = "原物料-买卖合同关联表")
	@TableField(exist = false)
	private List<CglRawMaterials1Entity> cglRawMaterials1List;
}
