package org.springblade.contract.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.ContractAccordingEntity;
import org.springblade.contract.entity.ContractRelieveEntity;
import io.swagger.annotations.ApiModel;
import org.springblade.core.tool.utils.DateUtil;
import org.springblade.resource.feign.IFileClient;
import org.springblade.resource.vo.FileVO;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 合同解除 返回模型VO
 *
 * @author 合同解除
 * @date : 2020-11-05 09:24:02
 */
@Getter
@Setter
@ToString
@ApiModel(description = "合同解除返回对象")
@EqualsAndHashCode(callSuper = true)
public class ContractRelieveResponseVO extends ContractRelieveEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;

	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	private Date createSystemTime;

	private  List<FileVO> termAgreementFileVOList;

	/**
	 * 合同依据管理
	 */
	private ContractAccordingEntity accordingEntity;
}
