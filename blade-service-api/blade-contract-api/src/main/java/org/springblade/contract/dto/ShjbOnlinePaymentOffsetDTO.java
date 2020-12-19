package org.springblade.contract.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springblade.contract.entity.ShjbOnlinePaymentOffsetEntity;
import java.util.Date;

/**
 * 售货机类：0428修-（仅运营）UP售货机运营维护服务合同（线上线上款相抵付款条款，20191014） 模型DTO
 *
 * @author 售货机类：0428修-（仅运营）UP售货机运营维护服务合同（线上线上款相抵付款条款，20191014）
 * @date : 2020-12-18 16:04:41
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class ShjbOnlinePaymentOffsetDTO extends ShjbOnlinePaymentOffsetEntity {

	private static final long serialVersionUID = 1L;

}
