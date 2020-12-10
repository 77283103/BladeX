package org.springblade.contract.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springblade.contract.entity.YwlANewDisplayEntity;
import java.util.Date;

/**
 * 业务类：21.新陈列协议书 模型DTO
 *
 * @author szw
 * @date : 2020-12-07 15:37:41
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class YwlANewDisplayDTO extends YwlANewDisplayEntity {

	private static final long serialVersionUID = 1L;

}
