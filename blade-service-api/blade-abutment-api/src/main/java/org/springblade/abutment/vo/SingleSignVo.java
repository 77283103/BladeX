package org.springblade.abutment.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "单个合同签署/作废返回数据")
public class SingleSignVo {
    @ApiModelProperty(value = "合同文件id")
    private String filePath;
    @ApiModelProperty(value = "签署记录serviceId")
    private String signServiceId;
    @ApiModelProperty(value = "签署文件地址")
    private String signDetailUrl;
    @ApiModelProperty(value = "自动加盖签署记录serviceId")
    private String autoSignServiceId;
    @ApiModelProperty(value = "自动加盖文件地址")
    private String autoSignDetailUrl;
}
