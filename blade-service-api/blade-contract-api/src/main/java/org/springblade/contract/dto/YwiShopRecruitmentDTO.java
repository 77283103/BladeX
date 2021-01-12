package org.springblade.contract.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springblade.contract.entity.YwiShopRecruitmentEntity;

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
public class YwiShopRecruitmentDTO extends YwiShopRecruitmentEntity {

	private static final long serialVersionUID = 1L;

}
