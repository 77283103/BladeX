package org.springblade.contract.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.SclLogisticsServiceEntity;
import io.swagger.annotations.ApiModel;
import java.util.Date;

/**
 * 生产类：物流服务合同（二段配送） 返回模型VO
 *
 * @author 张文武
 * @date : 2021-01-04 14:31:03
 */
@Getter
@Setter
@ToString
@ApiModel(description = "生产类：物流服务合同（二段配送）返回对象")
@EqualsAndHashCode(callSuper = true)
public class SclLogisticsServiceResponseVO extends SclLogisticsServiceEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
