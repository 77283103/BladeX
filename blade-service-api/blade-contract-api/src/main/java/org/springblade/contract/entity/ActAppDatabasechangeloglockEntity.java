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
 * 售货机类：2019.12.04修-售货机设备租赁合同—通用版（不可销售自选产品版本）补充协议（20191129）(1)(1) 实体类
 *
 * @author 售货机类
 * @date : 2020-12-18 16:13:49
 */
@Getter
@Setter
@TableName("shjb_non_supplementary_agreement")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ActAppDatabasechangeloglock对象", description = "售货机类：2019.12.04修-售货机设备租赁合同—通用版（不可销售自选产品版本）补充协议（20191129）(1)(1)")
public class ActAppDatabasechangeloglockEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 乙方
	 */
    @ApiModelProperty(value="乙方")
	private String partyB;
	/**
	 * 在甲乙双方签订的编号为【？】的《售货机租赁合同（通用版）》
	 */
    @ApiModelProperty(value="在甲乙双方签订的编号为【？】的《售货机租赁合同（通用版）》")
	private String signedParty;
	/**
	 * 户名
	 */
    @ApiModelProperty(value="户名")
	private String accountName;
	/**
	 * 账号
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="账号")
	private Integer accountNumber;
	/**
	 * 开户行全称
	 */
    @ApiModelProperty(value="开户行全称")
	private String openingBank;

}
