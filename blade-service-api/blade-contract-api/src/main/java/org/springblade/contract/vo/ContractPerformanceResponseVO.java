package org.springblade.contract.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.contract.entity.ContractPerformanceEntity;
import io.swagger.annotations.ApiModel;

/**
 * 合同履约计划 返回模型VO
 *
 * @author liyj
 * @date : 2020-09-23 19:26:29
 */
@Getter
@Setter
@ApiModel(description = "合同履约计划返回对象")
@EqualsAndHashCode(callSuper = true)
public class ContractPerformanceResponseVO extends ContractPerformanceEntity {

	private static final long serialVersionUID = 1L;

}
