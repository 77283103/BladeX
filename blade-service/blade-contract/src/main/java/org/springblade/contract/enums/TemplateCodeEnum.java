package org.springblade.contract.enums;

public enum TemplateCodeEnum {

	MMHT_08("五金", "MMHT_08"),
	MMHT_10("原物料", "MMHT_10");

	// 成员变量
	private String value;
	private String key;

	TemplateCodeEnum(String value, String key) {
		this.value = value;
		this.key = key;
	}

	public static String getValue(String key) {
		for (TemplateCodeEnum c : TemplateCodeEnum.values()) {
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
