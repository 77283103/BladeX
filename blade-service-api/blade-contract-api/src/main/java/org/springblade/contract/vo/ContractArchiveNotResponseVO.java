package org.springblade.contract.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.ContractArchiveNotEntity;
import io.swagger.annotations.ApiModel;
import java.util.Date;

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
}
