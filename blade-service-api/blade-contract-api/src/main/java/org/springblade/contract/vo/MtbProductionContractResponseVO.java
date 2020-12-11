package org.springblade.contract.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.MtbProductionContractEntity;
import io.swagger.annotations.ApiModel;
import java.util.Date;

/**
 * 媒体类：平面广告拍摄制作合同 返回模型VO
 *
 * @author 王策
 * @date : 2020-12-10 19:30:59
 */
@Getter
@Setter
@ToString
@ApiModel(description = "媒体类：平面广告拍摄制作合同返回对象")
@EqualsAndHashCode(callSuper = true)
public class MtbProductionContractResponseVO extends MtbProductionContractEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
