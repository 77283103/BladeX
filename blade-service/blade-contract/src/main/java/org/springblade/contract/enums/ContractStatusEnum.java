package org.springblade.contract.enums;

public enum ContractStatusEnum {

	DRAFT("草稿", 10);

	// 成员变量
	private String value;
	private Integer key;

	ContractStatusEnum(String value, Integer key) {
		this.value = value;
		this.key = key;
	}

	public static String getValue(Integer key) {
		for (ContractStatusEnum c : ContractStatusEnum.values()) {
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

		public Integer getKey() {
			return key;
		}

		public void setKey(Integer key) {
			this.key = key;
		}
	}
