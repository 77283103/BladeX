package org.springblade.contract.enums;

/**
 * @author xhbbo
 */

public enum ContractErorrCodeEnum {

	QUERY_COMPANY_INFO("电子签章查询结果", 200),
	INDE_DRAFT_SAVE("独立起草暂存成功", 200),
	INDE_DRAFT_APPROVAL("独立起草送审成功", 200),
	INDE_DRAFT_APPROVAL_FAIL("独立起草送审失败", 400),
	CHANGE_INDE_DRAFT_SAVE("独立起草变更暂存成功", 200),
	CHANGE_INDE_DRAFT_APPROVAL("独立起草变更送审成功", 200),
	MULTI_PARTY_DRAFT_SAVE("多方起草暂存成功", 200),
	MULTI_PARTY_DRAFT_APPROVAL("多方起草送审成功", 200),
	MULTI_PARTY_DRAFT_APPROVAL_FAIL("多方起草送审失败", 400),
	CHANGE_MULTI_PARTY_DRAFT_SAVE("多方起草变更暂存成功", 200),
	CHANGE_MULTI_PARTY_DRAFT_APPROVAL("多方起草变更送审成功", 200),
	TEMPLATE_PARTY_DRAFT_SAVE("范本起草暂存成功", 200),
	TEMPLATE_PARTY_DRAFT_APPROVAL("范本起草送审成功", 200),
	TEMPLATE_PARTY_DRAFT_APPROVAL_FAIL("范本起草送审失败", 400),
	CHANGE_TEMPLATE_PARTY_DRAFT_SAVE("范本起草变更暂存成功", 200),
	CHANGE_TEMPLATE_PARTY_DRAFT_APPROVAL("范本起草变更送审成功", 200);

	// 成员变量
	private String value;
	private Integer key;

	ContractErorrCodeEnum(String value, Integer key) {
		this.value = value;
		this.key = key;
	}

	public static String getValue(Integer key) {
		for (ContractErorrCodeEnum c : ContractErorrCodeEnum.values()) {
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
