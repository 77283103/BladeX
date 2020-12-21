package org.springblade.abutment.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * KEP接口传输的入参
 */
@Data
public class PushEkpEntity implements Serializable {
    private String fdTemplateId;
    private DocCreatorEntity docCreator;
    private String docSubject;
    private String token;
    private FormValuesEntity formValues;
}
