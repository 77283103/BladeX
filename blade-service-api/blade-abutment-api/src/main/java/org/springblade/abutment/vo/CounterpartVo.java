package org.springblade.abutment.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springblade.abutment.entity.CounterpartEntity;

import java.util.List;

/**
 * 相对方增量的数据
 * @author xhbbo
 */
@Data
@ApiModel(value = "查询相对方信息返回数据")
public class CounterpartVo {
	@ApiModelProperty(value = "新增")
	private List<CounterpartEntity> insert;
	@ApiModelProperty(value = "更新")
	private List<CounterpartEntity> update;
}
