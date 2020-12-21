package org.springblade.abutment.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "签章位置信息，签骑缝章可全不填，则默认所有页面该骑缝章")
public class SignPosEntity implements Serializable {
    @ApiModelProperty(hidden = true,example = "0")
    private Integer posType;
    @ApiModelProperty(value = "签署页码，若为多页签章，支持输入例如“1-3,5,8”的格式,除关键字签署外不能为空")
    private String posPage;
    @ApiModelProperty(value = "签署位置的x坐标,如是关键字签署则为关键字的x坐标偏移量(选填,默认0)",example = "0")
    private Float posX;
    @ApiModelProperty(value = "签署位置的y坐标,如是关键字签署则为关键字的y坐标偏移量(选填,默认0)",example = "0")
    private Float posY;
    @ApiModelProperty(value = "关键字，仅限关键字签章时有效，若为关键字签署时，不可空")
    private String key;
    @ApiModelProperty(value = "印章展现宽度(选填)，不填选则默认最大宽度159，小于159则以签章尺寸为准",example = "0")
    private Float width;
    @ApiModelProperty(value = "是否是二维码签署(选填),默认为false,骑缝签章和多页签章下不生效")
    private Boolean qrcodeSign;
    @ApiModelProperty(value = "是否是作废签签署(选填),默认为false")
    private Boolean cacellingSign;
    @ApiModelProperty(value = "是否显示本地签署时间(选填),默认为false,如选true印章宽度最小要大于92")
    private Boolean addSignTime;
}
