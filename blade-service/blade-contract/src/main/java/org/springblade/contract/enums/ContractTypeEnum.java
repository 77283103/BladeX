package org.springblade.contract.enums;

public enum ContractTypeEnum {

	BATCH("批量", 40);

	// 成员变量
	private String value;
	private Integer key;

	ContractTypeEnum(String value, Integer key) {
		this.value = value;
		this.key = key;
	}

	public static String getValue(Integer key) {
		for (ContractTypeEnum c : ContractTypeEnum.values()) {
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
