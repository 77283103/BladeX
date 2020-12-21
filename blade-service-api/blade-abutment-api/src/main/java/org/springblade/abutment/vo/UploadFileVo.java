package org.springblade.abutment.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "文件上传返回数据")
public class UploadFileVo {
    @ApiModelProperty(value = "文件唯一标识")
    private String id;

    public UploadFileVo(){}
    public UploadFileVo(String id){this.id=id;}
}
