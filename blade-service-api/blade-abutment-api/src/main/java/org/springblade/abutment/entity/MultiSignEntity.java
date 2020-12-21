package org.springblade.abutment.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel(value = "批量合同签署/作废的入参")
public class MultiSignEntity implements Serializable {
    @ApiModelProperty(value = "签署者账号标识，个人：证件号码 企业：组织机构代码(必填)")
    private String idno;
    @ApiModelProperty(value = "签署文档和签署位置信息集合（上限为50个）(必填)")
    private List<SignParamsEntity> signParams;
    @ApiModelProperty(value = "短信验证码，必须通过“发送签署短信验证码接口发送(必填)")
    private String code;
}
