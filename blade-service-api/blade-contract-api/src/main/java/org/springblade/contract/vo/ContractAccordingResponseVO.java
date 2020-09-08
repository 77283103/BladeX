package org.springblade.contract.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.ContractAccordingEntity;
import io.swagger.annotations.ApiModel;
import org.springblade.resource.vo.FileVO;

import java.util.List;

/**
 *  返回模型VO
 *
 * @author feng
 */
@Data
@ApiModel(description = "返回对象")
@EqualsAndHashCode(callSuper = true)
public class ContractAccordingResponseVO extends ContractAccordingEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	private Long id;

	/**
	 * 附件列表
	 */
	private List<FileVO> fileVOList;
}
