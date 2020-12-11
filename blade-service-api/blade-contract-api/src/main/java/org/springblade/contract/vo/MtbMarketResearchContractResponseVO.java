package org.springblade.contract.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.MtbMarketResearchContractEntity;
import io.swagger.annotations.ApiModel;
import java.util.Date;

/**
 * 媒体类：市调合同（定性+定量) 返回模型VO
 *
 * @author 王策
 * @date : 2020-12-10 19:37:23
 */
@Getter
@Setter
@ToString
@ApiModel(description = "媒体类：市调合同（定性+定量)返回对象")
@EqualsAndHashCode(callSuper = true)
public class MtbMarketResearchContractResponseVO extends MtbMarketResearchContractEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
