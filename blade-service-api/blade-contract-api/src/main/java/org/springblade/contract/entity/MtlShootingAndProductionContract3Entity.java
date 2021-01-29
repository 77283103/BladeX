package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.core.mp.base.BaseEntity;
import org.springblade.core.tool.utils.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * 媒体类：视频广告拍摄制作合同关联表2 实体类
 *
 * @author 媒体类：视频广告拍摄制作合同关联表2
 * @date : 2020-12-11 05:31:03
 */
@Getter
@Setter
@TableName("mtl_shooting_and_production_contract3")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "MtlShootingAndProductionContract3对象", description = "媒体类：视频广告拍摄制作合同关联表3")
public class MtlShootingAndProductionContract3Entity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 文件
     */
    @ApiModelProperty(value = "文件")
    private String file;
    /**
     * 创作时间
     */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value = "创作时间")
    private Date creationTime;
    /**
     * 完成
     * 地点
     */
    @ApiModelProperty(value = "完成地点")
    private String completeplace;
    /**
     * 创作者
     */
    @ApiModelProperty(value = "创作者")
    private String creator;
    /**
     * 职务作品or委托创作职务作品or委托创作职务作品委托创作
     */
    @ApiModelProperty(value = "职务作品 or 委托创作 职务作品 or 委托创作 职务作品委托创作")
    private String employment;
    /**
     * 合同ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "合同ID")
    private Long contractId;

}
