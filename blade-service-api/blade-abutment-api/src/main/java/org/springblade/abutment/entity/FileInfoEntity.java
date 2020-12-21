package org.springblade.abutment.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "签章PDF文档信息")
public class FileInfoEntity implements Serializable {
    @ApiModelProperty(value = "合同文件id(必填)")
    private String id;
    @ApiModelProperty(value = "文档名称,e签宝签署日志对应的文档名,若为空则取文档路径中的名称(选填)")
    private String fileName;
    @ApiModelProperty(value = "文档编辑密码,当目标PDF设置权限保护时必填")
    private String ownerPassword;
    @ApiModelProperty(value = "是否增加e签宝logo图标(选填)")
    private Boolean showImage;
    @ApiModelProperty(value = "客户自由标示位，用于辨识文件(选填)")
    private String markBit;
}
