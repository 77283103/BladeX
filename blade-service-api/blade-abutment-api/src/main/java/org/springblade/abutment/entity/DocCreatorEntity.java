package org.springblade.abutment.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class DocCreatorEntity implements Serializable {
    private String emplno;

	public DocCreatorEntity(){}

    public DocCreatorEntity(String emplno){
    	this.emplno = emplno;
	}
}
