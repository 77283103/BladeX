package org.springblade.contract.constant;

public interface ContractFormInfoTemplateContract {

	//元件类型
	String COMPONENT_TYPE = "editList,relationList,formList";
	//二级联动组件类型
	String COMPONENT_TYPE_SELECT = "secondSelect";
	//组件小类型
	String COMPONENT_TYPE_SMALL = "select,cascader,radio";
	//字段验证规则
	String COMPONENT_TYPE_REQUORED = "true";
	//合同
	String CONTRACT_ACCORDING = "ContractAccording";
	//合同对应方
	String CONTRACT_COUNTERPART = "ContractCounterpart";
	//合同履行
	String CONTRACT_PERFORMANCE = "ContractPerformance";
	//合同履行费
	String CONTRACT_PERFORMANCE_COLPAY = "ContractPerformanceColPay";
	//合同—对应方
	String CONTRACT_COUNTERPART_SUB_COUNTERPART = "counterpart";
	//合同-保证金
	String CONTRACT_COUNTERPART_SUB_CONTRACTBOND = "contractBond";
	//合同-分类
	String CONTRACT_BIG_CATEGORY = "Classification";
	//合同-收付款方式
	String CONTRACT_COL_PAY = "ColPayType";
	//合同-收付款方式
	String CONTRACT_ID = "id";

}
