package org.springblade.contract.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.contract.entity.LaborDispatchEntity;

/**
 * 韩素娟劳务派遣合同模板(甲方有拼接附件） 模型DTO
 *
 * @author wd
 * @date : 2021-01-22 15:16:09
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class LaborDispatchDTO extends LaborDispatchEntity {

	private static final long serialVersionUID = 1L;

}
