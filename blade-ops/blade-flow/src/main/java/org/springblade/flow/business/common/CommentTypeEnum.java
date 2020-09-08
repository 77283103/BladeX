package org.springblade.flow.business.common;

/**
 * <功能><定义流程提交操作，用于审批意见中展示>
 *
 * @author 田爱华
 * @date 2020-8-26
 */
public enum CommentTypeEnum {
	/**
	 * 过程意见类型
	 */
	TJ("提交"),
	SP("审批"),
	TH("退回"),
	ZB("转办"),
	WP("委派"),
	WPFH("委派返回");

	/**
	 * 名称
	 */
	private String name;

	public static String getEnumMsgByType(String type) {
		for (CommentTypeEnum e : CommentTypeEnum.values()) {
			if (e.toString().equals(type)) {
				return e.name;
			}
		}
		return "";
	}

	CommentTypeEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
