package org.springblade.contract.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.CglRawMaterialsEntity;
import io.swagger.annotations.ApiModel;
import java.util.Date;

/**
 * 采购类：原物料-买卖合同 返回模型VO
 *
 * @author 王策
 * @date : 2020-12-10 19:17:26
 */
@Getter
@Setter
@ToString
@ApiModel(description = "采购类：原物料-买卖合同返回对象")
@EqualsAndHashCode(callSuper = true)
public class CglRawMaterialsResponseVO extends CglRawMaterialsEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
