package org.springblade.abutment.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * KEP接口传输的入参
 */
@Data
public class PushEkpEntity implements Serializable {
    private String fdTemplateId;
    private DocCreatorEntity docCreator;
    private String docSubject;
    private String token;
	private List<Attachment> fd_attachment;
    private FormValuesEntity formValues;
    private BorrowAc borrowAc;
    private TemplateAc templateAc;
    private FormMultiEntity formMulti;
}
