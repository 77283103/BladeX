package org.springblade.contract.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.CglTheSalesContract1Entity;
import io.swagger.annotations.ApiModel;
import java.util.Date;

/**
 * 采购类：新增原物料补充协议--买卖合同 返回模型VO
 *
 * @author 采购类：新增原物料补充协议--买卖合同
 * @date : 2020-12-10 18:50:24
 */
@Getter
@Setter
@ToString
@ApiModel(description = "采购类：新增原物料补充协议--买卖合同返回对象")
@EqualsAndHashCode(callSuper = true)
public class CglTheSalesContract1ResponseVO extends CglTheSalesContract1Entity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
