package org.springblade.contract.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.ShjbPointOperationEntity;
import io.swagger.annotations.ApiModel;
import java.util.Date;

/**
 * 售货机类：0428修-（点位+运营）UP售货机合作合同（线上线下款相抵付款条款，20191014） 返回模型VO
 *
 * @author 售货机类：0428修-（点位+运营）UP售货机合作合同（线上线下款相抵付款条款，20191014）
 * @date : 2020-12-18 16:05:32
 */
@Getter
@Setter
@ToString
@ApiModel(description = "售货机类：0428修-（点位+运营）UP售货机合作合同（线上线下款相抵付款条款，20191014）返回对象")
@EqualsAndHashCode(callSuper = true)
public class ShjbPointOperationResponseVO extends ShjbPointOperationEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
