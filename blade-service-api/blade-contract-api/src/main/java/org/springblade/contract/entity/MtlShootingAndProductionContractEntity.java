package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.core.mp.base.BaseEntity;
import org.springblade.core.tool.utils.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * 媒体类：视频广告拍摄制作合同 实体类
 *
 * @author 媒体类：视频广告拍摄制作合同
 * @date : 2020-12-10 19:36:02
 */
@Getter
@Setter
@TableName("mtl_shooting_and_production_contract")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "MtlShootingAndProductionContract对象", description = "媒体类：视频广告拍摄制作合同")
public class MtlShootingAndProductionContractEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 甲方
	 */
    @ApiModelProperty(value="甲方")
	private String mtlPatyA;
	/**
	 * 甲方联络邮箱
	 */
    @ApiModelProperty(value="甲方联络邮箱")
	private String mtlPatyAEmail;
	/**
	 * 甲方地址
	 */
    @ApiModelProperty(value="甲方地址")
	private String mtlContactEmail;
	/**
	 * 乙方
	 */
    @ApiModelProperty(value="乙方")
	private String mtlPatyB;
	/**
	 * 乙方联络邮箱
	 */
    @ApiModelProperty(value="乙方联络邮箱")
	private String mtlPatyBEmail;
	/**
	 * 乙方住所
	 */
    @ApiModelProperty(value="乙方住所")
	private String mtlPatyBHome;
	/**
	 * 甲方委托乙方完成【？】改编事宜，乙方接受甲方的委托
	 */
    @ApiModelProperty(value="甲方委托乙方完成【？】改编事宜，乙方接受甲方的委托")
	private String mtlAdaptationIssues;
	/**
	 * 广告名称
	 */
    @ApiModelProperty(value="广告名称")
	private String mtlNameOfAdvertising;
	/**
	 * 广告内容
	 */
    @ApiModelProperty(value="广告内容")
	private String mtlContentsOfAdvertisements;

	/**
	 * 作品中【？】（有/未）涉及第三方知识产权、肖像权
	 */
    @ApiModelProperty(value="作品中【？】（有/未）涉及第三方知识产权、肖像权")
	private String mtlHaveHasNot;
	/**
	 * 拍摄开始时间
	 */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="拍摄开始时间")
	private Date mtlProductionStartTime;
	/**
	 * 拍摄完成时间
	 */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="拍摄完成时间")
	private Date mtlProductionCompletionTime;
	/**
	 * 应同时提交以下内容
	 */
	@ApiModelProperty(value="拍摄完成时间")
    private String mtlSubmitCheck;
	/**
	 * 上述广告作品给甲方指定人员【？】验收
	 */
	@ApiModelProperty(value="上述广告作品给甲方指定人员【？】验收")
	private String mtlAcceptance;
	/**
	 * 本广告制作合同价款为(未税额人民币)
	 */
    @ApiModelProperty(value="本广告制作合同价款为(未税额人民币)")
	private BigDecimal mtlUnpaidTaxRmb;
	/**
	 * 税率
	 */
    @ApiModelProperty(value="税率")
	private Double mtlRate;
	/**
	 * 现含税金额人民币
	 */
    @ApiModelProperty(value="现含税金额人民币")
	private BigDecimal mtlTaxAmountIsRmb;
	/**
	 * 公司名
	 */
    @ApiModelProperty(value="公司名")
	private String mtlCompanyName;
	/**
	 * 开户行
	 */
    @ApiModelProperty(value="开户行")
	private String mtlWhereItIs;
	/**
	 * 账号
	 */
    @ApiModelProperty(value="账号")
	private String mtlAccount;

	/**
	 * 配音员VO  *  年的使用权
	 */
	@ApiModelProperty(value="配音员VO  *  年的使用权")
	private String mtlDubbingUse;

	/**
	 * 音乐词  *  年使用权
	 */
	@ApiModelProperty(value="音乐词  *  年使用权")
	private String mtlMusicUse;

	/**
	 * 歌手演唱  *  年的使用权
	 */
	@ApiModelProperty(value="歌手演唱  *  年的使用权")
	private String mtlSingerUse;

	/**
	 * 签订日期
	 */
	@ApiModelProperty(value = "签订日期")
	private Date dateSigning;
	/**
	 * 工作内容
	 */
	@ApiModelProperty(value = "工作内容")
	private String jobContent;
	/**
	 * 合同编号
	 */
	@ApiModelProperty(value = "合同编号")
	private String contractNo;
	/**
	 * 视频广告拍摄制作合同1
	 */
	@ApiModelProperty(value="视频广告拍摄制作合同关联表1")
	@TableField(exist = false)
	private List<MtlShootingAndProductionContract1Entity> mtlShootingAndProductionContract1EntityList;
	/**
	 * 视频广告拍摄制作合同2
	 */
	@ApiModelProperty(value="视频广告拍摄制作合同关联表2")
	@TableField(exist = false)
	private List<MtlShootingAndProductionContract2Entity> mtlShootingAndProductionContract2EntityList;
	/**
	 * 知识产权权利确认书(模板)
	 */
	@ApiModelProperty(value="知识产权权利确认书(模板)")
	@TableField(exist = false)
	private List<MtlShootingAndProductionContract3Entity> mtlShootingAndProductionContract3EntityList;

}
