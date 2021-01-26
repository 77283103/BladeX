package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springblade.core.mp.base.BaseEntity;
import org.springblade.core.tool.utils.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * 市调合同 实体类
 *
 * @author 刘是罕
 * @date : 2021-01-21 11:07:49
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("mtb_market_research_contract1")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "MtbMarketResearchContract1对象", description = "市调合同")
public class MtbMarketResearchContract1Entity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 时间
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	@ApiModelProperty(value = "时间")
	private Date mtbTime;
	/**
	 * 事项
	 */
	@ApiModelProperty(value = "事项")
	private String mtbMatter;
	/**
	 * 合同ID
	 */
	@ApiModelProperty(value = "合同ID")
	private Long contractId;

}
