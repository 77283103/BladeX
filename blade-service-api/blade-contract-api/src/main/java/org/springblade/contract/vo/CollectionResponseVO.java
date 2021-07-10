package org.springblade.contract.vo;

import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springblade.contract.entity.CollectionEntity;

/**
 * 采购类：新增原物料补充协议--买卖合同 返回模型VO
 *
 * @author 王策
 * @date : 2020-12-10 19:07:53
 */
@Getter
@Setter
@ToString
@ApiModel(description = "合同收款明细")
@EqualsAndHashCode(callSuper = true)
public class CollectionResponseVO extends CollectionEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
