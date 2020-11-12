package org.springblade.contract.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springblade.contract.entity.ContractArchiveNotEntity;
import java.util.Date;

/**
 * 未归档原因 模型DTO
 *
 * @author 未归档原因
 * @date : 2020-11-09 15:19:17
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class ContractArchiveNotDTO extends ContractArchiveNotEntity {

	private static final long serialVersionUID = 1L;

}
