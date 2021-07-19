package org.springblade.contract.enums;

public enum ContractTypeEnum {

	BATCH_MODEL("批量-独立", 41),
	BATCH_SINGLE("批量-范本", 43),

	BATCH("批量", 40),
	MULTI("多方", 20),
	INDE("独立",10),
	TEMPLATE("范本",30),
	BATCH_INDE("批量独立",41),
	BATCH_TEMPLATE("批量范本",43),

	//合同形式
	ELECTRONIC_CONTRACT_WE("电子合同-我司用印",1),
	ENTITY_CONTRACT_WE("实体合同-我司电子印",2),
	ELECTRONIC_CONTRACT_OTHER("电子合同-对方平台",3),
	ENTITY_CONTRACT_OTHER("实体合同-我司不用电子印",4);

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
