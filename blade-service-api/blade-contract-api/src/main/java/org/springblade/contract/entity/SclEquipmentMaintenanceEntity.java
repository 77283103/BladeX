package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
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
 * 生产类：设备维修保养合同 实体类
 *
 * @author kx
 * @date : 2020-12-11 10:56:37
 */
@Getter
@Setter
@TableName("scl_equipment_maintenance")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SclEquipmentMaintenance对象", description = "生产类：设备维修保养合同")
public class SclEquipmentMaintenanceEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 甲方
	 */
    @ApiModelProperty(value="甲方")
	private String sclPatyA;
	/**
	 * 甲方地址
	 */
    @ApiModelProperty(value="甲方地址")
	private String sclPatyBs;
	/**
	 * 乙方
	 */
    @ApiModelProperty(value="乙方")
	private String sclPatyB;
	/**
	 * 乙方地址
	 */
    @ApiModelProperty(value="乙方地址")
	private String sclAddress;
	/**
	 * 维修保养项目名称
	 */
    @ApiModelProperty(value="维修保养项目名称")
	private String sclProjectName;
	/**
	 * 维修保养设备的所在地
	 */
    @ApiModelProperty(value="维修保养设备的所在地")
	private String sclHome;
	/**
	 * 总计人民币（未税）
	 */
    @ApiModelProperty(value="总计人民币（未税）")
	private BigDecimal sclTotalRmb;
	/**
	 * 大写人民币
	 */
    @ApiModelProperty(value="大写人民币")
	private String sclCapitalRmb;
	/**
	 * 备注：以上价格适用以下 【？】 约定
	 */
    @ApiModelProperty(value="备注：以上价格适用以下 【？】 约定")
	private String sclNote;
	/**
	 * 设备维修保养维护服务按以下第【？】种方式进行
	 */
    @ApiModelProperty(value="设备维修保养维护服务按以下第【？】种方式进行")
	private String sclEquipment;
	/**
	 * 累计运行时间维修保养：每运行【？】小时进行检修
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="累计运行时间维修保养：每运行【？】小时进行检修")
	private Integer sclMaintenancessss;
	/**
	 * 质保期为 【？】小时
	 */
	@ApiModelProperty(value = "质保期为 【？】小时")
	private Integer sclGuaranteePeriod;
	/**
	 * 单次维修保养：质保期为【？】个月
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="单次维修保养：质保期为【？】个月")
	private Integer sclQuality;
	/**
	 * 月度维修保养：每月进行【？】次例行检修
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="月度维修保养：每月进行【？】次例行检修")
	private Integer sclRoutineMaintenancess;
	/**
	 * 质保期为【？】个月
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="质保期为【？】个月")
	private Integer sclMonth;
	/**
	 * 季度维修保养：每季进行【？】次例行检修
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="季度维修保养：每季进行【？】次例行检修")
	private Integer sclMaintenance;
	/**
	 * 质保期为【？】个月
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="质保期为【？】个月")
	private Integer sclPeriod;
	/**
	 * 年度维修保养：每年进行【？】
	 */
    @ApiModelProperty(value="年度维修保养：每年进行【？】")
	private String sclMaintenances;
	/**
	 * 次例行检修，质保期为【？】个月
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="次例行检修，质保期为【？】个月")
	private Integer sclRoutine;
	/**
	 * 经乙方维修保养的设备，须在每次维修保养后连续或累计【？】个月内有效、正常运转
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="经乙方维修保养的设备，须在每次维修保养后连续或累计【？】个月内有效、正常运转")
	private Integer sclCumulative;
	/**
	 * （第四.5项）特别约定：【？】
	 */
    @ApiModelProperty(value="（第四.5项）特别约定：【？】")
	private String sclSpecificallyAgreed;
	/**
	 * （第六.3项违约金）
	 */
    @ApiModelProperty(value="（第六.3项违约金）")
	private String sclContract;
	/**
	 * 每次维修保养后应于【？】个工作日内向甲方提交书面维护保养清单及维修保养报告
	 */
    @ApiModelProperty(value="每次维修保养后应于【？】个工作日内向甲方提交书面维护保养清单及维修保养报告")
	private String sclPartyAs;
	/**
	 * 乙方的服务电话：【？】
	 */
    @ApiModelProperty(value="乙方的服务电话：【？】")
	private String sclServiceTelephone;
	/**
	 * 乙方应于甲方报修后【？】个小时到场维修
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="乙方应于甲方报修后【？】个小时到场维修")
	private Integer sclShouldBeIn;
	/**
	 * 紧急维修项目应在【？】个小时内到场维修
	 */
    @ApiModelProperty(value="紧急维修项目应在【？】个小时内到场维修")
	private String sclMaintenanceq;
	/**
	 * 双方同意采用以下第【？】种约定付款
	 */
    @ApiModelProperty(value="双方同意采用以下第【？】种约定付款")
	private String sclSides;
	/**
	 * 款项均以【？】方式支付
	 */
    @ApiModelProperty(value="款项均以【？】方式支付")
	private String sclPayment;
	/**
	 * 其他方式：【？】
	 */
    @ApiModelProperty(value="其他方式：【？】")
	private String sclOtherWay;
	/**
	 * 开户行
	 */
    @ApiModelProperty(value="开户行")
	private String sclBank;
	/**
	 * 户名
	 */
    @ApiModelProperty(value="户名")
	private String sclName;
	/**
	 * 账号
	 */
    @ApiModelProperty(value="账号")
	private String sclAccount;
	/**
	 * （第八.1项违约金）
	 */
    @ApiModelProperty(value="（第八.1项违约金）")
	private String sclContracts;
	/**
	 * 逾期日
	 */
    @ApiModelProperty(value="逾期日")
	private String sclLimit;
	/**
	 * （第八.2项违约金）
	 */
    @ApiModelProperty(value="（第八.2项违约金）")
	private String sclGold;
	/**
	 * 合同期内，乙方维修保养后的设备累计超过【？】次检测不合格的
	 */
    @ApiModelProperty(value="合同期内，乙方维修保养后的设备累计超过【？】次检测不合格的")
	private String sclThan;
	/**
	 * （第八.4项违约金）
	 */
    @ApiModelProperty(value="（第八.4项违约金）")
	private String sclBou;
	/**
	 * 本合同有效期:开始时间
	 */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="本合同有效期:开始时间")
	private Date sclStart;
	/**
	 * 本合同有效期:结束时间
	 */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="本合同有效期:结束时间")
	private Date sclLaste;
	/**
	 * 统一 【?】公司
	 */
    @ApiModelProperty(value="统一 【?】公司")
	private String sclCompany;
	/**
	 * 其他约定事项
	 */
    @ApiModelProperty(value="其他约定事项")
	private String sclAgreed;
	/**
	 * 附件一名称
	 */
	@ApiModelProperty(value = "附件一名称")
	private String sclFujian;
	/**
	 * 附件二名称
	 */
	@ApiModelProperty(value = "附件二名称")
	private String sclFujian2;
	/**
	 * 附件三名称
	 */
	@ApiModelProperty(value = "附件三名称")
	private String sclFujian3;

	/**
	 * 联系人甲方
	 */
	@ApiModelProperty(value = "附件三名称")
	private String sclLianxirenjia;
	/**
	 * 联系人乙方
	 */
	@ApiModelProperty(value = "附件三名称")
	private String sclLianxirenyi;
	/**
	 * 联系电话甲方
	 */
	@ApiModelProperty(value = "附件三名称")
	private String sclLianxielejia;

	/**
	 * 联系电话乙方
	 */
	@ApiModelProperty(value = "附件三名称")
	private String sclLianxieleyi;
	/**
	 * 拼接附件
	 */
	@ApiModelProperty(value = "拼接附件")
	private String annex;

	@ApiModelProperty(value = "设备维修保养合同(关联表）")
	@TableField(exist = false)
	private List<SclEquipmentMaintenance1Entity> sclEquipmentMaintenance1List;

}
