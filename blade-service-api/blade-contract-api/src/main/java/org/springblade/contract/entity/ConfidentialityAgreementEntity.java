package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.springblade.core.mp.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import java.time.LocalDateTime;


/**
 * 梁艳-保密协议（三方） 实体类
 *
 * @author 王策
 * @date : 2021-01-15 15:36:27
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("confidentiality_agreement")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ConfidentialityAgreement对象", description = "梁艳-保密协议（三方）")
public class ConfidentialityAgreementEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 投资方(甲方)
	 */
	@ApiModelProperty(value = "投资方(甲方)")
	private String jiaFang;
	/**
	 * 贸易方(乙方)
	 */
	@ApiModelProperty(value = "贸易方(乙方)")
	private String tradeSide;
	/**
	 * 生产方(丙方)
	 */
	@ApiModelProperty(value = "生产方(丙方)")
	private String manufacturer;
	/**
	 * 涉及产品的名称
	 */
	@ApiModelProperty(value = "涉及产品的名称")
	private String productInvolved;
	/**
	 * 涉及知识产权、保密信息内容
	 */
	@ApiModelProperty(value = "涉及知识产权、保密信息内容")
	private String contentsInformation;
	/**
	 * 乙方使用范围/权限
	 */
	@ApiModelProperty(value = "乙方使用范围/权限1")
	private String fbUsingRange;
	/**
	 *  丙方使用范围
	 */
	@ApiModelProperty(value = " 丙方使用范围1")
	private String fcUsingRange;
	/**
	 * 乙方使用范围/权限2
	 */
	@ApiModelProperty(value = "乙方使用范围/权限2")
	private String sbUsingRange;
	/**
	 *  丙方使用范围2
	 */
	@ApiModelProperty(value = " 丙方使用范围2")
	private String scUsingRange;
	/**
	 * 涉及产品的名称2
	 */
	@ApiModelProperty(value = "涉及产品的名称2")
	private String productInvolvedA;
	/**
	 * 涉及知识产权、保密信息内容2
	 */
	@ApiModelProperty(value = "涉及知识产权、保密信息内容2")
	private String contentsInformationA;
	/**
	 * 所有权主体确认
	 */
	@ApiModelProperty(value = "所有权主体确认")
	private String ownershipSubject;
	/**
	 * 若同时和什么等与甲方企业经营同类或相似业务品牌产品相关企业的任何一公司存在交易往来
	 */
	@ApiModelProperty(value = "若同时和什么等与甲方企业经营同类或相似业务品牌产品相关企业的任何一公司存在交易往来")
	private String blankField;
	/**
	 * 其他约定
	 */
	@ApiModelProperty(value = "其他约定")
	private String otherAgreements;
	/**
	 * 本协议经三方盖章后自时期生效
	 */
	@ApiModelProperty(value = "本协议经三方盖章后自时期生效")
	private Date specificDate;

}
