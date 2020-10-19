package org.springblade.contract.vo;

import lombok.*;
import org.springblade.contract.entity.ContractAccordingEntity;
import io.swagger.annotations.ApiModel;
import org.springblade.resource.vo.FileVO;

import java.util.List;

/**
 * 合同依据管理 返回模型VO
 *
 * @author XHB
 * @date : 2020-09-24 14:20:35
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel(description = "合同依据管理返回对象")
@EqualsAndHashCode(callSuper = true)
public class ContractAccordingResponseVO extends ContractAccordingEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 依据附件列表
	 */
	private List<FileVO> accordingFilesVOList;

}
