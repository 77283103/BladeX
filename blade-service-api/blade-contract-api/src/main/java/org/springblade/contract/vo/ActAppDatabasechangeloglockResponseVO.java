package org.springblade.contract.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.ActAppDatabasechangeloglockEntity;
import io.swagger.annotations.ApiModel;
import java.util.Date;

/**
 * 售货机类：2019.12.04修-售货机设备租赁合同—通用版（不可销售自选产品版本）补充协议（20191129）(1)(1) 返回模型VO
 *
 * @author 售货机类
 * @date : 2020-12-18 16:13:51
 */
@Getter
@Setter
@ToString
@ApiModel(description = "售货机类：2019.12.04修-售货机设备租赁合同—通用版（不可销售自选产品版本）补充协议（20191129）(1)(1)返回对象")
@EqualsAndHashCode(callSuper = true)
public class ActAppDatabasechangeloglockResponseVO extends ActAppDatabasechangeloglockEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
