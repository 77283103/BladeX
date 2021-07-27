package org.springblade.abutment.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xhbbo
 */
@Data
@ApiModel(value = "EKP单据推送返回数据")
public class EkpVo {
	@ApiModelProperty(value = "生成文档的fdid")
	private String doc_info;

	@ApiModelProperty(value = "生成ekp单据的合同信息单号")
	private String ekp_number;

	@ApiModelProperty(value = "生成预警代办或待阅的单据ID")
	private String notifyId;

	@ApiModelProperty(value = "返回的状态信息")
	private String msg_info;

	public EkpVo() {
	}

	public EkpVo(String doc_info) {
		this.doc_info = doc_info;
		this.notifyId = doc_info;
	}
	public EkpVo(String doc_info,String ekp_number,String msg_info) {
		this.doc_info = doc_info;
		this.notifyId = doc_info;
		this.ekp_number=ekp_number;
		this.msg_info=msg_info;
	}

}
