package org.springblade.contract.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springblade.contract.entity.MtbMarketResearchContractEntity;
import java.util.Date;

/**
 * 媒体类：市调合同（定性+定量) 模型DTO
 *
 * @author 王策
 * @date : 2020-12-10 19:37:15
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class MtbMarketResearchContractDTO extends MtbMarketResearchContractEntity {

	private static final long serialVersionUID = 1L;

}
