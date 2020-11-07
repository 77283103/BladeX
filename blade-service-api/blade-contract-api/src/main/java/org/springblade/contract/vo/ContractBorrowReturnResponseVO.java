package org.springblade.contract.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.ContractBorrowReturnEntity;
import io.swagger.annotations.ApiModel;
import java.util.Date;

/**
 * 借阅归还 返回模型VO
 *
 * @author xhb
 * @date : 2020-10-30 09:29:01
 */
@Getter
@Setter
@ToString
@ApiModel(description = "借阅归还返回对象")
@EqualsAndHashCode(callSuper = true)
public class ContractBorrowReturnResponseVO extends ContractBorrowReturnEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
