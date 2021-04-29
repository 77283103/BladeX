package org.springblade.contract.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springblade.contract.entity.PerBondEntity;
import java.util.Date;

/**
 * 履约计划保证金 模型DTO
 *
 * @author chenzy
 * @date : 2021-04-27 17:06:20
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class PerBondDTO extends PerBondEntity {

	private static final long serialVersionUID = 1L;

}
