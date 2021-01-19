package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springblade.core.mp.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * 生产项目外包服务合同 实体类
 *
 * @author Wang Pengfei
 * @date : 2021-01-19 10:20:00
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("product_out_service_contract")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ProductOutServiceContract对象", description = "生产项目外包服务合同")
public class ProductOutServiceContractEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 甲方
	 */
	@ApiModelProperty(value = "甲方")
	private String proSaler;
	/**
	 * 甲方住所
	 */
	@ApiModelProperty(value = "甲方住所")
	private String proSalerAddr;
	/**
	 * 乙方
	 */
	@ApiModelProperty(value = "乙方")
	private String proBuyer;
	/**
	 * 乙方住所
	 */
	@ApiModelProperty(value = "乙方住所")
	private String proBuyerAddr;
	/**
	 * 承包项目服务期限-始
	 */
	@ApiModelProperty(value = "承包项目服务期限-始")
	private Date proTimeStart;
	/**
	 * 承包项目服务期限-止
	 */
	@ApiModelProperty(value = "承包项目服务期限-止")
	private Date proTimeEnd;
	/**
	 * 则以?元计算赔偿金
	 */
	@ApiModelProperty(value = "则以?元计算赔偿金")
	private BigDecimal proAmount;
	/**
	 * 年满?周岁-?周岁
	 */
	@ApiModelProperty(value = "年满?周岁-?周岁")
	private String proAgeRequire;
	/**
	 * 未投保人数比例超过?%
	 */
	@ApiModelProperty(value = "未投保人数比例超过?%")
	private Double proMaxPercent;
	/**
	 * 乙方需按?元/次支付违约金
	 */
	@ApiModelProperty(value = "乙方需按?元/次支付违约金")
	private BigDecimal proTimeAmount;
	/**
	 * 乙方联系人
	 */
	@ApiModelProperty(value = "乙方联系人")
	private String proBuyerPerson;
	/**
	 * 乙方联系方式
	 */
	@ApiModelProperty(value = "乙方联系方式")
	private String proBuyerPhone;
	/**
	 * 次月?日前
	 */
	@ApiModelProperty(value = "次月?日前")
	private String proLastDayFir;
	/**
	 * 次月?日前开具合法增值税专用发票
	 */
	@ApiModelProperty(value = "次月?日前开具合法增值税专用发票")
	private String proLastDaySec;
	/**
	 * 甲方收到发票后?日内
	 */
	@ApiModelProperty(value = "甲方收到发票后?日内")
	private String proLastDayThi;
	/**
	 * 以?形式支付上月承包服务作业费用
	 */
	@ApiModelProperty(value = "以?形式支付上月承包服务作业费用")
	private String inPayType;
	/**
	 * 乙方户名
	 */
	@ApiModelProperty(value = "乙方户名")
	private String proBuyerAccountName;
	/**
	 * 乙方账号
	 */
	@ApiModelProperty(value = "乙方账号")
	private String proBuyerAccountId;
	/**
	 * 乙方开户行
	 */
	@ApiModelProperty(value = "乙方开户行")
	private String proBuyerAccountBank;
	/**
	 * 向甲方支付人民币?万元作为履约保证金
	 */
	@ApiModelProperty(value = "向甲方支付人民币?万元作为履约保证金")
	private BigDecimal proBondAmountFir;
	/**
	 * 乙方应在?日内将履约保证金补足
	 */
	@ApiModelProperty(value = "乙方应在?日内将履约保证金补足")
	private String proLastDayFou;
	/**
	 * 超过?日仍未补足的
	 */
	@ApiModelProperty(value = "超过?日仍未补足的")
	private String proLastDayFiv;
	/**
	 * 甲方户名
	 */
	@ApiModelProperty(value = "甲方户名")
	private String proSalerAccoutName;
	/**
	 * 乙方账号
	 */
	@ApiModelProperty(value = "乙方账号")
	private String proSalerAccoutId;
	/**
	 * 乙方开户行
	 */
	@ApiModelProperty(value = "乙方开户行")
	private String proSalerAccoutBank;
	/**
	 * 支付?万元作为提前解约违约金
	 */
	@ApiModelProperty(value = "支付?万元作为提前解约违约金")
	private BigDecimal proBondAmountSec;
	/**
	 * 要求乙方支付违约金?万元
	 */
	@ApiModelProperty(value = "要求乙方支付违约金?万元")
	private BigDecimal proBondAmountThi;
	/**
	 * 要求乙方支付违约金?万元
	 */
	@ApiModelProperty(value = "要求乙方支付违约金?万元")
	private BigDecimal proBondAmountFou;
	/**
	 * 乙方须同时支付甲方违约金?万元
	 */
	@ApiModelProperty(value = "乙方须同时支付甲方违约金?万元")
	private BigDecimal proBondAmountFiv;
	/**
	 * 违约金金额为?万元
	 */
	@ApiModelProperty(value = "违约金金额为?万元")
	private BigDecimal proBondAmountSix;
	/**
	 * 补充约定
	 */
	@ApiModelProperty(value = "补充约定")
	private String proSupplyArrange;
	/**
	 * 附件一：项目服务作业内容和要求
	 */
	@ApiModelProperty(value = "附件一：项目服务作业内容和要求")
	private String proAnnexFir;
	/**
	 * 附件二：甲方厂区管理制度
	 */
	@ApiModelProperty(value = "附件二：甲方厂区管理制度")
	private String proAnnexSec;
	/**
	 * 关联子表1标识
	 */
	@ApiModelProperty(value = "关联子表1标识")
	private Long proOutServiceCon1Id;
	/**
	 * 关联子表2标识
	 */
	@ApiModelProperty(value = "关联子表2标识")
	private Long proOutServiceCon2Id;
	/**
	 * 关联子表3标识
	 */
	@ApiModelProperty(value = "关联子表3标识")
	private Long proOutServiceCon3Id;

	/**
	 * 关联子表1标识
	 */
	@ApiModelProperty(value = "关联子表1标识")
	@TableField(exist = false)
	private List<ProductOutServiceContract1Entity> productOutServiceContract1EntityList;
	/**
	 * 关联子表2标识
	 */
	@ApiModelProperty(value = "关联子表2标识")
	@TableField(exist = false)
	private List<ProductOutServiceContract2Entity> productOutServiceContract2EntityList;
	/**
	 * 关联子表3标识
	 */
	@ApiModelProperty(value = "关联子表3标识")
	@TableField(exist = false)
	private List<ProductOutServiceContract3Entity> productOutServiceContract3EntityList;

}
