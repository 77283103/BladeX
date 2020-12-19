package org.springblade.contract.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.SclServiceEntity;
import io.swagger.annotations.ApiModel;
import java.util.Date;

/**
 * 生产类：物流服务合同（二段仓储+配送） 返回模型VO
 *
 * @author kx
 * @date : 2020-12-18 17:08:14
 */
@Getter
@Setter
@ToString
@ApiModel(description = "生产类：物流服务合同（二段仓储+配送）返回对象")
@EqualsAndHashCode(callSuper = true)
public class SclServiceResponseVO extends SclServiceEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
