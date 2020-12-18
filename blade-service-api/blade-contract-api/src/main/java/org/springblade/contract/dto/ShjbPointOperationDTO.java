package org.springblade.contract.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springblade.contract.entity.ShjbPointOperationEntity;
import java.util.Date;

/**
 * 售货机类：0428修-（点位+运营）UP售货机合作合同（线上线下款相抵付款条款，20191014） 模型DTO
 *
 * @author 售货机类：0428修-（点位+运营）UP售货机合作合同（线上线下款相抵付款条款，20191014）
 * @date : 2020-12-18 16:05:24
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class ShjbPointOperationDTO extends ShjbPointOperationEntity {

	private static final long serialVersionUID = 1L;

}
