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
 * 采购类：活动执行合同 实体类
 *
 * @author 采购类：活动执行合同
 * @date : 2020-12-10 18:29:51
 */
@Getter
@Setter
@TableName("cgl_activity_execution_contract")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "CglActivityExecutionContract对象", description = "采购类：活动执行合同")
public class CglActivityExecutionContractEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;


}
