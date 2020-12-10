package org.springblade.contract.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springblade.contract.entity.ContractArchiveEntity;
import io.swagger.annotations.ApiModel;
import org.springblade.core.tool.utils.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 合同归档 返回模型VO
 *
 * @author 合同归档
 * @date : 2020-11-05 09:41:40
 */
@Getter
@Setter
@ToString
@ApiModel(description = "合同归档返回对象")
@EqualsAndHashCode(callSuper = true)
public class ContractArchiveResponseVO extends ContractArchiveEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;

	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	private Date createSystemTime;

	@ApiModelProperty(value="未归档原因")
	private String notArchiveReason;
}
