package org.springblade.abutment.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "盖章完成后自动加盖其他章")
public class AutoSignEntity implements Serializable {
    @ApiModelProperty(value = "签署者账号标识，个人：证件号码 企业：组织机构代码(必填)")
    private String idno;
    @ApiModelProperty(value = "签章类型(必填):Single：单页签章,Multi：多页签章,Edges：签骑缝章,Key：关键字签章")
    private String signType;
    @ApiModelProperty(value = "签章位置信息,除骑缝签章外必填")
    private SignPosEntity signPos;
}
