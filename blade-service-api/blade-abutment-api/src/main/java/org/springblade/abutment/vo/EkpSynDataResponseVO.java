package org.springblade.abutment.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.abutment.entity.EkpSynDataEntity;
import io.swagger.annotations.ApiModel;
import java.util.Date;

/**
 * ekp同步数据 返回模型VO
 *
 * @author chenzy
 * @date : 2021-07-27 15:42:13
 */
@Getter
@Setter
@ToString
@ApiModel(description = "ekp同步数据返回对象")
@EqualsAndHashCode(callSuper = true)
public class EkpSynDataResponseVO extends EkpSynDataEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
}
