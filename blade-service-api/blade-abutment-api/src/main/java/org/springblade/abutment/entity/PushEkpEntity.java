package org.springblade.abutment.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * KEP接口传输的入参
 * @author xhbbo
 */
@Data
public class PushEkpEntity implements Serializable {
    private String fdTemplateId;
    private DocCreatorEntity docCreator;
    private String docSubject;
    private String token;
    @ApiModelProperty(value = "合同归档需要的该字段")
	private String emplno;
	private List<Attachment> fd_attachment;
    private FormValuesEntity formValues;
    private BorrowAc borrowAc;
    private TemplateAc templateAc;
    private FormMultiEntity formMulti;
    //代办，待阅
	@ApiModelProperty(value = "系统类型，请传递固定字符串c_p_notify")
	private String systemName;
	@ApiModelProperty(value = "待办类型：1待办，2待阅；如第一和第二预警传2(待阅)，第三次传1(待办)")
	private String notifyType;
	@ApiModelProperty(value = "合同编号，ekp根据此合同id找用印文档并跳转展示")
	private String contractNo;
}
