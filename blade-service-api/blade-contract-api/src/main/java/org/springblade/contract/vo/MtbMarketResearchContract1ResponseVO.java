package org.springblade.contract.vo;

import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.contract.entity.MtbMarketResearchContract1Entity;

/**
 * 市调合同 返回模型VO
 *
 * @author 刘是罕
 * @date : 2021-01-21 11:07:51
 */
@Getter
@Setter
@ApiModel(description = "市调合同返回对象")
@EqualsAndHashCode(callSuper = true)
public class MtbMarketResearchContract1ResponseVO extends MtbMarketResearchContract1Entity {

	private static final long serialVersionUID = 1L;

}
