package org.springblade.abutment.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author xhbbo
 */
@Data
@ApiModel(value = "获取组织及人员信息的入参")
public class OrganizationEntity {
    @ApiModelProperty(value = "字段名(选填,不填写则返回所有字段,字段名称可填写:id,email,loginName,emplno,name,namePinyin,isAvailable,orgType,parentid,alterTime)")
    private List<String> fieldsName;
    @ApiModelProperty(value = "查询条件:ID(唯一ID,选填,不填写则返回所有)")
    private String id;
    @ApiModelProperty(value = "查询条件:email(email地址,选填,不填写则返回所有)")
    private String email;
    @ApiModelProperty(value = "查询条件:loginName(登录名,选填,不填写则返回所有)")
    private String loginName;
    @ApiModelProperty(value = "查询条件:emplno(员工编号,选填,不填写则返回所有)")
    private String emplno;
    @ApiModelProperty(value = "查询条件:name(名称,选填,不填写则返回所有)")
    private String name;
    @ApiModelProperty(value = "查询条件:namePinyin(名称拼音,选填,不填写则返回所有)")
    private String namePinyin;
    @ApiModelProperty(value = "查询条件:isAvailable(是否有效 1表示有效,选填,不填写则返回所有)")
    private String isAvailable;
    @ApiModelProperty(value = "查询条件:orgType(类型 2部门 4岗位 8个人,选填,不填写则返回所有)")
    private String orgType;
    @ApiModelProperty(value = "查询条件:parentid(父节点,选填,不填写则返回所有)")
    private String parentid;
    @ApiModelProperty(value = "查询条件:alterTime(最后修改时间,选填,不填写则返回所有)")
    private String alterTime;
	@ApiModelProperty(value = "查询条件:ins_date(创建时间,选填,不填写则返回所有)")
	private String ins_date;
}
