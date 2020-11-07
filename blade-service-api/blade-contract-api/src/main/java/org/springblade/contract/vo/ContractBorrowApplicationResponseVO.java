package org.springblade.contract.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.ContractBorrowApplicationEntity;
import io.swagger.annotations.ApiModel;
import org.springblade.contract.entity.ContractBorrowHandleEntity;
import org.springblade.contract.entity.ContractBorrowReturnEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 借阅申请 返回模型VO
 *
 * @author xhb
 * @date : 2020-10-30 09:27:11
 */
@Getter
@Setter
@ToString
@ApiModel(description = "借阅申请返回对象")
@EqualsAndHashCode(callSuper = true)
public class ContractBorrowApplicationResponseVO extends ContractBorrowApplicationEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createSystemTime;

}
