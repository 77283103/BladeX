package org.springblade.abutment.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "查看合同的入参")
public class ReadSignedEntity implements Serializable {
    @ApiModelProperty(value = "合同文件唯一id(必填)")
    private String id;

    @ApiModelProperty(value = "操作类型(必填,1 预览 2 下载)")
    private String type;
}
