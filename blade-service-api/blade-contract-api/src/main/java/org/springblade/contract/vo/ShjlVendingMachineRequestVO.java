package org.springblade.contract.vo;

import lombok.ToString;
import lombok.Getter;
import lombok.Setter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import org.springblade.core.tool.utils.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import org.springblade.core.mp.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;


/**
 * 售货机类：电子公章-（点位+运营）UP售货机合作合同（20191016）法务(1) 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author 售货机类：电子公章-（点位+运营）UP售货机合作合同（20191016）法务(1)
 * @date : 2020-12-18 16:14:38
 */
@Getter
@Setter
@ToString
@ApiModel(description = "售货机类：电子公章-（点位+运营）UP售货机合作合同（20191016）法务(1)请求对象")
public class ShjlVendingMachineRequestVO extends BaseEntity{

	private static final long serialVersionUID = 1L;

}
