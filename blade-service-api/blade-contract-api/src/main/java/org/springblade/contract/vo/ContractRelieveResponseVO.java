package org.springblade.contract.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.ContractRelieveEntity;
import io.swagger.annotations.ApiModel;
import org.springblade.system.vo.UserDepartVO;

import java.util.List;

/**
 *  返回模型VO
 *
 * @author szw
 * @date : 2020-09-23 20:10:29
 */
@Data
@ApiModel(description = "返回对象")
@EqualsAndHashCode(callSuper = true)
public class ContractRelieveResponseVO extends ContractRelieveEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 人员身份信息
	 */
	private List<UserDepartVO> userDepartList;
}
