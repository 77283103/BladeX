package org.springblade.abutment.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "发送短信验证码的入参")
public class SendSmsEntity implements Serializable {
    @ApiModelProperty(value = "签署者账号标识，以此获取账户的证书进行签署(必填) 个人：证件号码 企业：组织机构代码")
    private String idno;
}
