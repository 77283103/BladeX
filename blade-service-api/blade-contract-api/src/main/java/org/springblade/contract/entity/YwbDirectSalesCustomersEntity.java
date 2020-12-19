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
 * 业务类：22.直营客户促销执行通知函 实体类
 *
 * @author 业务类：22.直营客户促销执行通知函
 * @date : 2020-12-18 16:06:12
 */
@Getter
@Setter
@TableName("ywb_direct_sales_customers")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "YwbDirectSalesCustomers对象", description = "业务类：22.直营客户促销执行通知函")
public class YwbDirectSalesCustomersEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 甲方
	 */
    @ApiModelProperty(value="甲方")
	private String firstParty;
	/**
	 * 乙方
	 */
    @ApiModelProperty(value="乙方")
	private String partyB;
	/**
	 * 甲方指定邮箱地址
	 */
    @ApiModelProperty(value="甲方指定邮箱地址")
	private String emailAddressDesignated;

}
