package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.springblade.core.mp.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import java.time.LocalDateTime;


/**
 * 合同归档管理 实体类
 *
 * @author XHB
 * @date : 2020-09-23 18:32:12
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("contract_archive")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Archive对象", description = "合同归档管理")
public class ArchiveEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 关联合同
	 */
	@ApiModelProperty(value = "关联合同")
	private Long contractId;
	/**
	 * 归档说明
	 */
	@ApiModelProperty(value = "归档说明")
	private String archiveInstructions;
	/**
	 * 经办信息
	 */
	@ApiModelProperty(value = "经办信息")
	private String manageMessage;

}
