package org.springblade.contract.vo;

import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.contract.entity.CglAdvertisementProductionEntity;

/**
 * 采购类：广告制作安装合同模板 返回模型VO
 *
 * @author 采购类：广告制作安装合同模板
 * @date : 2021-01-12 14:02:07
 */
@Getter
@Setter
@ApiModel(description = "采购类：广告制作安装合同模板返回对象")
@EqualsAndHashCode(callSuper = true)
public class CglAdvertisementProductionResponseVO extends CglAdvertisementProductionEntity {

	private static final long serialVersionUID = 1L;

}
