package org.springblade.contract.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.ContractSealUsingInfoEntity;
import io.swagger.annotations.ApiModel;
import org.springblade.core.tool.utils.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 合同用印 返回模型VO
 *
 * @author 合同用印
 * @date : 2020-11-05 09:29:27
 */
@Getter
@Setter
@ToString
@ApiModel(description = "合同用印返回对象")
@EqualsAndHashCode(callSuper = true)
public class ContractSealUsingInfoResponseVO extends ContractSealUsingInfoEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;

	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	private Date createSystemTime;
}
