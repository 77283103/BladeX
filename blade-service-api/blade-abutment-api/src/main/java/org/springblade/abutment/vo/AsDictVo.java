package org.springblade.abutment.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springblade.abutment.entity.AsDict;

import java.util.List;

/**
 * @author xhbbo
 */
@Data
public class AsDictVo {
	@ApiModelProperty(value = "编号")
	private List<AsDict> asDicts;
}
