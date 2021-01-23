package org.springblade.contract.vo;

import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.contract.entity.LaborDispatchEntity;

/**
 * 韩素娟劳务派遣合同模板(甲方有拼接附件） 返回模型VO
 *
 * @author wd
 * @date : 2021-01-22 15:16:22
 */
@Getter
@Setter
@ApiModel(description = "韩素娟劳务派遣合同模板(甲方有拼接附件）返回对象")
@EqualsAndHashCode(callSuper = true)
public class LaborDispatchResponseVO extends LaborDispatchEntity {

	private static final long serialVersionUID = 1L;

}
