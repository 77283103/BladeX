package org.springblade.contract.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springblade.contract.entity.YwlShopRecruitmentEntity;
import java.util.Date;

/**
 * 业务类：14.店招合同 模型DTO
 *
 * @author szw
 * @date : 2020-12-04 19:04:55
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class YwlShopRecruitmentDTO extends YwlShopRecruitmentEntity {

	private static final long serialVersionUID = 1L;

}
