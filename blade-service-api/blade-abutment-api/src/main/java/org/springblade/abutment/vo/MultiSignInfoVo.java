package org.springblade.abutment.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "批量合同签署/作废返回数据详情")
public class MultiSignInfoVo {
    @ApiModelProperty(value = "单个合同执行状态")
    private String errCode;
    @ApiModelProperty(value = "单个合同是否存在错误信息")
    private String errShow;
    @ApiModelProperty(value = "错误码对应信息")
    private String msg;
    @ApiModelProperty(value = "")
    private String stream;
    @ApiModelProperty(value = "签署记录serviceId")
    private String signServiceId;
    @ApiModelProperty(value = "合同文件id")
    private String filePath;
    @ApiModelProperty(value = "")
    private String dstFilePath;
    @ApiModelProperty(value = "签署文件地址")
    private String signDetailUrl;
}
