package org.springblade.contract.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.ContractBorrowHandleEntity;
import io.swagger.annotations.ApiModel;
import java.util.Date;

/**
 * 借阅处理 返回模型VO
 *
 * @author xhb
 * @date : 2020-10-30 09:28:26
 */
@Getter
@Setter
@ToString
@ApiModel(description = "借阅处理返回对象")
@EqualsAndHashCode(callSuper = true)
public class ContractBorrowHandleResponseVO extends ContractBorrowHandleEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
