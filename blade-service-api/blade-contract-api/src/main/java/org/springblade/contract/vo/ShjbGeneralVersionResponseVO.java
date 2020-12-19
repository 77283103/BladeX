package org.springblade.contract.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.ShjbGeneralVersionEntity;
import io.swagger.annotations.ApiModel;
import java.util.Date;

/**
 * 售货机类：2020.2.24修 -售货机设备租赁合同—通用版（可销售自选产品版本）） 返回模型VO
 *
 * @author 售货机类：2020.2.24修 -售货机设备租赁合同—通用版（可销售自选产品版本））
 * @date : 2020-12-18 16:02:29
 */
@Getter
@Setter
@ToString
@ApiModel(description = "售货机类：2020.2.24修 -售货机设备租赁合同—通用版（可销售自选产品版本））返回对象")
@EqualsAndHashCode(callSuper = true)
public class ShjbGeneralVersionResponseVO extends ShjbGeneralVersionEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
