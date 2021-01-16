package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.springblade.core.mp.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import java.time.LocalDateTime;


/**
 * 梁艳-保密协议 实体类
 *
 * @author wd
 * @date : 2021-01-15 14:57:38
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("ly_confidentiality_agreement")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "LyConfidentialityAgreement对象", description = "梁艳-保密协议")
public class LyConfidentialityAgreementEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 甲方联系地址
	 */
	@ApiModelProperty(value = "甲方联系地址")
	private String contactPartyA;
	/**
	 * 乙方联系地址
	 */
	@ApiModelProperty(value = "乙方联系地址")
	private String contactPartyB;
	/**
	 * （1）涉及产品的名称
	 */
	@ApiModelProperty(value = "（1）涉及产品的名称")
	private String productNameA;
	/**
	 * （1）涉及知识产权、保密信息内容
	 */
	@ApiModelProperty(value = "（1）涉及知识产权、保密信息内容")
	private String productContentA;
	/**
	 * （2）涉及产品的名称
	 */
	@ApiModelProperty(value = "（2）涉及产品的名称")
	private String productNameB;
	/**
	 * （2）涉及知识产权、保密信息内容
	 */
	@ApiModelProperty(value = "（2）涉及知识产权、保密信息内容")
	private String productContentB;
	/**
	 * 乙方在与甲方合作期间，若同时和[?]等与甲方企业经营
	 */
	@ApiModelProperty(value = "乙方在与甲方合作期间，若同时和[?]等与甲方企业经营")
	private String otherEnterprises;
	/**
	 * 第六条 其他约定
	 */
	@ApiModelProperty(value = "第六条 其他约定")
	private String otherAgreements;
	/**
	 * 年月日
	 */
	@ApiModelProperty(value = "年月日")
	private Date specificDate;

}
