package org.springblade.abutment.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "EKP单据推送返回数据")
public class EkpVo {
    @ApiModelProperty(value = "生成文档的fdid")
    private String doc_info;

	@ApiModelProperty(value = "生成ekp单据的合同信息单号")
	private String ekp_number;

    public EkpVo(){}
    public EkpVo(String doc_info){this.doc_info=doc_info;}
}
