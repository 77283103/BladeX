package org.springblade.contract.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.ContractArchiveNotEntity;
import io.swagger.annotations.ApiModel;
import org.springblade.contract.entity.ContractCounterpartEntity;
import org.springblade.core.tool.utils.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 未归档原因 返回模型VO
 *
 * @author 未归档原因
 * @date : 2020-11-09 15:19:19
 */
@Getter
@Setter
@ToString
@ApiModel(description = "未归档原因返回对象")
@EqualsAndHashCode(callSuper = true)
public class ContractArchiveNotResponseVO extends ContractArchiveNotEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;

	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	private Date createSystemTime;

	/**
	 * 对方公司
	 */
	@ApiModelProperty(value="对方公司")
	private List<ContractCounterpartEntity> otherCompanyNameList;
}
