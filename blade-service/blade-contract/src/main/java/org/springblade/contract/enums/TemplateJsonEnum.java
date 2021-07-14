package org.springblade.contract.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.stream.Stream;

/**
 * @author xhbbo
 */
@Getter
@AllArgsConstructor
@Slf4j
public enum TemplateJsonEnum {

	//物流服务合同（二段仓储+配送）
	MMHT_10("MMHT_10") {
		@Override
		public String setScheduler(String  type) {
			return null;
		}
	};
	public abstract String setScheduler(String  type);

	@Getter
	public String type;

	/**
	 * 通过轮询来获得相应的方法
	 */
	public static TemplateJsonEnum fromValue(String type) {
		return Stream.of(TemplateJsonEnum.values()).filter(fileType ->
			StringUtils.equals(fileType.getType(), type)
		).findFirst().get();
	}
}
