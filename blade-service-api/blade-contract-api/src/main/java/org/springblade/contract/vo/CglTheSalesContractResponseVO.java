package org.springblade.contract.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.CglTheSalesContractEntity;
import io.swagger.annotations.ApiModel;
import java.util.Date;

/**
 * 采购类：新增原物料补充协议--买卖合同 返回模型VO
 *
 * @author 王策
 * @date : 2020-12-10 19:07:53
 */
@Getter
@Setter
@ToString
@ApiModel(description = "采购类：新增原物料补充协议--买卖合同返回对象")
@EqualsAndHashCode(callSuper = true)
public class CglTheSalesContractResponseVO extends CglTheSalesContractEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
