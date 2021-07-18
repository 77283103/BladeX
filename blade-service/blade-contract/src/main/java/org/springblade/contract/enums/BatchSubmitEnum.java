package org.springblade.contract.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springblade.contract.entity.ContractFormInfoEntity;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author xhbbo
 */
@Getter
@AllArgsConstructor
@Slf4j
public enum BatchSubmitEnum {

	//物流服务合同（二段仓储+配送）
	BATCH_INDE("41") {
		@Override
		public String setScheduler(List<ContractFormInfoEntity> infoEntityList) {
			return null;
		}
	},
	BATCH_TEMPLATE("43") {
		@Override
		public String setScheduler(List<ContractFormInfoEntity> infoEntityList) {
			return null;
		}
	};
	public abstract String setScheduler(List<ContractFormInfoEntity> infoEntityList);

	@Getter
	public String type;

	/**
	 * 通过轮询来获得相应的方法
	 */
	public static BatchSubmitEnum fromValue(String type) {
		return Stream.of(BatchSubmitEnum.values()).filter(fileType ->
			StringUtils.equals(fileType.getType(), type)
		).findFirst().get();
	}
}
