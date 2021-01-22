package org.springblade.contract.vo;

import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.contract.entity.MtbMarketResearchContractEntity;

/**
 * 媒体类：市调合同（定性+定量) 返回模型VO
 *
 * @author 刘是罕
 * @date : 2021-01-21 11:07:34
 */
@Getter
@Setter
@ApiModel(description = "媒体类：市调合同（定性+定量)返回对象")
@EqualsAndHashCode(callSuper = true)
public class MtbMarketResearchContractResponseVO extends MtbMarketResearchContractEntity {

	private static final long serialVersionUID = 1L;

}
