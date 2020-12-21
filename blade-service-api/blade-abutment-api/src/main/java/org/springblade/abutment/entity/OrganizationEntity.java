package org.springblade.abutment.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "人员及组织信息返回数据,返回数据为List集合")
public class OrganizationEntity {
    @ApiModelProperty(value = "唯一ID")
    private String id;
    @ApiModelProperty(value = "email地址")
    private String email;
    @ApiModelProperty(value = "登录名")
    private String loginName;
    @ApiModelProperty(value = "员工编号)")
    private String emplno;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "名称拼音")
    private String namePinyin;
    @ApiModelProperty(value = "是否有效 1表示有效")
    private String isAvailable;
    @ApiModelProperty(value = "类型 2部门 4岗位 8个人")
    private String orgType;
    @ApiModelProperty(value = "父节点")
    private String parentid;
    @ApiModelProperty(value = "最后修改时间")
    private String alterTime;
}
