package org.springblade.abutment.enums;

public enum EkpTemplateEnum {

	RELIEVE_TEMPLATE_ID("合同解除-送审模板", "17a98a9b314e18332cba4a8420abb37a");

	// 成员变量
	private String value;
	private String key;

	EkpTemplateEnum(String value, String key) {
		this.value = value;
		this.key = key;
	}

	public static String getValue(String key) {
		for (EkpTemplateEnum c : EkpTemplateEnum.values()) {
				if (c.getKey().equals(key)) {
					return c.value;
				}
			}
			return null;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}
	}
