package org.springblade.abutment.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "依据查询返回数据,返回数据为List集合")
public class DocVo {
    @ApiModelProperty(value = "文档名称")
    private String doc_name;
    @ApiModelProperty(value = "文档编号")
    private String doc_number;
    @ApiModelProperty(value = "文档类型")
    private String doc_type;
    @ApiModelProperty(value = "文档ID")
    private String doc_id;
}
