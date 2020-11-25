package org.springblade.contract.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.ContractCounterpartEntity;
import org.springblade.contract.entity.ContractFormInfoEntity;
import org.springblade.contract.entity.ContractPerformanceColPayEntity;
import io.swagger.annotations.ApiModel;
import java.util.Date;
import java.util.List;

/**
 * 收付款计划清单-收付款 返回模型VO
 *
 * @author szw
 * @date : 2020-11-05 17:07:03
 */
@Getter
@Setter
@ToString
@ApiModel(description = "收付款计划清单-收付款返回对象")
@EqualsAndHashCode(callSuper = true)
public class ContractPerformanceColPayResponseVO extends ContractPerformanceColPayEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;

	@ApiModelProperty(value = "履约规定结束时间")
	private String  planPayTimeEnd;

	@ApiModelProperty(value = "关联合同信息")
	private ContractFormInfoEntity contractFormInfoEntity;

	@ApiModelProperty(value = "合同相对方")
	private List<ContractCounterpartEntity> counterpartEntityList;
}
