package org.springblade.abutment.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "签署文档和签署位置信息集合（上限为50个）")
public class SignParamsEntity implements Serializable {
    @ApiModelProperty(value = "签章PDF文档信息")
    private FileInfoEntity fileBean;
    @ApiModelProperty(value = "签章类型(必填):Single：单页签章,Multi：多页签章,Edges：签骑缝章,Key：关键字签章")
    private String signType;
    @ApiModelProperty(value = "签章位置信息,除骑缝签章外必填")
    private SignPosEntity signPos;
}
