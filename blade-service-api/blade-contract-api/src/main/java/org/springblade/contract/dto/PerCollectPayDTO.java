package org.springblade.contract.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springblade.contract.entity.PerCollectPayEntity;
import java.util.Date;

/**
 * 履约收付款 模型DTO
 *
 * @author chenzy
 * @date : 2021-04-25 10:32:28
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class PerCollectPayDTO extends PerCollectPayEntity {

	private static final long serialVersionUID = 1L;

}
