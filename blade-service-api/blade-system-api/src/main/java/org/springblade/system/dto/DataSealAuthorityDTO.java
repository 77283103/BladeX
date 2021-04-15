package org.springblade.system.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import org.springblade.system.entity.DataSealAuthorityEntity;
import java.util.Date;

/**
 * DataSealAuthority 模型DTO
 *
 * @author xhb
 * @date : 2021-04-12 16:51:00
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class DataSealAuthorityDTO extends DataSealAuthorityEntity {

	private static final long serialVersionUID = 1L;

}
