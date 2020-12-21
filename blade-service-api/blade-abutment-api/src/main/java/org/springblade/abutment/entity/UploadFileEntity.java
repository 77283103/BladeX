package org.springblade.abutment.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.File;
import java.io.Serializable;
import java.util.List;

@Data
@ApiModel(value = "上传文件的入参")
public class UploadFileEntity implements Serializable {
    @ApiModelProperty(value = "文件集合,可上传多个文件(必填)")
    private List<File> file;
    @ApiModelProperty(value = "是否合并文件,0不合并 1合并(必填)")
    private String isMerge;
    @ApiModelProperty(value = "合并后的文件名(是否合并文件为1时必填)")
    private String fileName;
}
