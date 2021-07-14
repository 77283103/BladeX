package org.springblade.contract.enums;

public enum ContractStatusEnum {

	DRAFT("草稿", 10),
	APPROVAL("送审", 20),
	NOPRINTING("未用印",40),
	APPROVED("审批通过",30),

	/*合同解除状态*/
	RELIEVE_SAVE("解除暂存",88),
	RELIEVE_SUBMIT("解除已送审",89),
	RELIEVE_PASS("解除通过",90);

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
