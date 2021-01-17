package org.springblade.contract.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.core.mp.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 下脚品买卖合同模板 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author Wang Pengfei
 * @date : 2021-01-15 15:54:15
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "下脚品买卖合同模板请求对象")
public class InferiorProductContractRequestVO extends BaseEntity {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="甲方")
	private String infSaler;
	
    @ApiModelProperty(value="甲方地址")
	private String infSalerAddr;
	
    @ApiModelProperty(value="乙方")
	private String infBuyer;
	
    @ApiModelProperty(value="乙方地址")
	private String infBuyerAddr;
	
    @ApiModelProperty(value="填空，提醒一般类、废面类、废油类")
	private String infTypeFir;
	
    @ApiModelProperty(value="每周开始")
	private String infWeekStart;
	
    @ApiModelProperty(value="每周结束")
	private String infWeekEnd;
	
    @ApiModelProperty(value="时间开始")
	private Date infTimeStart;
	
    @ApiModelProperty(value="时间结束")
	private Date infTimeEnd;
	
    @ApiModelProperty(value="分拣作业地点")
	private String infSortAddr;
	
    @ApiModelProperty(value="当次赔偿金额小于")
	private BigDecimal infLeastAmount;
	
    @ApiModelProperty(value="元/次计算赔偿金")
	private BigDecimal infTimeAmount;
	
    @ApiModelProperty(value="装车地点")
	private String infLoadAddr;
	
    @ApiModelProperty(value="履约保证金万元")
	private BigDecimal infAppointAmount;
	
    @ApiModelProperty(value="履约保证金万元(大写)")
	private String infCap;
	
    @ApiModelProperty(value="日内将履约保证金补足")
	private String infTimeLeastFir;
	
    @ApiModelProperty(value="日仍未补足的")
	private String infTimeLeastSec;
	
    @ApiModelProperty(value="甲方户名")
	private String infSalerAccoutName;
	
    @ApiModelProperty(value="甲方账号")
	private String infSalerAccoutId;
	
    @ApiModelProperty(value="甲方开户行")
	private String infSalerAccoutBank;
	
    @ApiModelProperty(value="乙方户名")
	private String infBuyerAccoutName;
	
    @ApiModelProperty(value="乙方账号")
	private String infBuyerAccoutId;
	
    @ApiModelProperty(value="乙方开户行")
	private String infBuyerAccoutBank;
	
    @ApiModelProperty(value="日内甲方向乙方开具发票")
	private String infTimeLeastThi;
	
    @ApiModelProperty(value="甲方指定邮箱")
	private String infSalerMail;
	
    @ApiModelProperty(value="乙方指定邮箱")
	private String infBuyerMail;
	
    @ApiModelProperty(value="违约金")
	private BigDecimal infBreachAmountFir;
	
    @ApiModelProperty(value="甲方有权解除本合同并要求乙方支付违约金")
	private BigDecimal infBreachAmountSec;
	
    @ApiModelProperty(value="元作为解约补偿金")
	private BigDecimal infBreachAmountThi;
	
    @ApiModelProperty(value="乙方还应向甲方支付")
	private BigDecimal infBreachAmountFou;
	
    @ApiModelProperty(value="合同期限开始")
	private Date infContractStart;
	
    @ApiModelProperty(value="合同期限结束")
	private Date infContractEnd;
	
    @ApiModelProperty(value="甲乙双方各执")
	private String infContractNum;
	
    @ApiModelProperty(value="甲方电话")
	private String infSalerPhone;
	
    @ApiModelProperty(value="乙方电话")
	private String infBuyerPhone;
	
    @ApiModelProperty(value="甲方联系人")
	private String infSalerPerson;
	
    @ApiModelProperty(value="乙方联系人")
	private String infBuyerPerson;
	
    @ApiModelProperty(value="废面流向承诺")
	private String infSurf;
	
    @ApiModelProperty(value="废油流向承诺")
	private String infOil;
	
    @ApiModelProperty(value="废茶渣流向承诺")
	private String infTeaSurf;
	
}
