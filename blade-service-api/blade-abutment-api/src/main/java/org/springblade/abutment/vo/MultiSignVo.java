package org.springblade.abutment.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "批量合同签署/作废返回数据")
public class MultiSignVo {
    @ApiModelProperty(value = "签署成功列表")
    private List<MultiSignInfoVo> successList;
    @ApiModelProperty(value = "签署失败列表")
    private List<MultiSignInfoVo> failList;
}
