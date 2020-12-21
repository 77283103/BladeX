package org.springblade.abutment.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "获取依据数据的入参")
public class DocEntity implements Serializable {
    /**token自动获取*/
    @ApiModelProperty(hidden = true)
    private String token;
    @ApiModelProperty(value = "员工编号(员工编号与身份ID至少传递一个)")
    private String emplno;
    @ApiModelProperty(value = "身份ID(员工编号与身份ID至少传递一个)")
    private String idNo;
    @ApiModelProperty(value = "文档编号(选填,支持模糊查询)")
    private String docNumber;
    @ApiModelProperty(value = "分页参数,每页数据条数(选填,需与页码配合)")
    private String pageSize;
    @ApiModelProperty(value = "分页参数,页码(选填,需与每页数据条数配合)")
    private String currentPage;
}
