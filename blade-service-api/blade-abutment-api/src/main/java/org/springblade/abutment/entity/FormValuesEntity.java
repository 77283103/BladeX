package org.springblade.abutment.entity;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author battle
 */
@Data
public class FormValuesEntity implements Serializable {
	@ApiModelProperty(value = "签章关键字")
	private JSONObject fd_keyword;
	@ApiModelProperty(value = "是否多页合同")
	private String fd_multipage;
	@ApiModelProperty(value = "合同方对应关系")
	private String fd_onetoone;
	@ApiModelProperty(value = "单据内容:乙方电话(必填)")
	private String fd_b_number;
	@ApiModelProperty(value = "单据内容:乙方信用代码或税籍编号(必填)")
	private String fd_b_taxno;
	@ApiModelProperty(value = "单据内容:依据文档id(必填,ekp跳转到合同平台传递的依据文档id)")
	private String fd_accord_id;
	@ApiModelProperty(value = "单据内容:合同id(必填,合同平台生成的电子用印合同的id,可以用于合同查阅)")
	private String fd_contract_id;
	@ApiModelProperty(value = "单据内容:PDF文件id(必填,合同上传E签宝返回的附件id,可用于查阅E签宝签字合同附件)")
	private String fd_contract_name;
	@ApiModelProperty(value = "单据内容:合同文件名称(必填)")
	private String fd_attachment_id;
	@ApiModelProperty(value = "单据内容:合同查看链接")
	private String fd_contract_url;
	@ApiModelProperty(value = "单据内容:合同起草类型(独立起草|10 范本起草|20 范本起草-有其他约定|30) 多方起草|40")
	private String fd_contract_type;
	@ApiModelProperty(value = "单据内容:合同主旨(必填)")
	private String fd_main;
	@ApiModelProperty(value = "单据内容:合同大类(必填,采购合同|CG 销售合同|XS 广宣合同|GX 租赁合同|ZL 运输、仓储、保管合同|YB 维保合同（不含信息化）|WB建设工程合同|GC 保密、廉洁合同|BL财务合同（非银行）|CW 银行合同|YH 非主营业务|GY 加工承缆合同|JC 服务合同|SC 公用事业合同|PB 劳务合同|LC)")
	private String fd_broad;
	@ApiModelProperty(value = "单据内容:合同中类(范本的需要传)")
	private String fd_secondary;
	@ApiModelProperty(value = "单据内容:合同小类(必填 字典)")
	private String fd_small;
	@ApiModelProperty(value = "单据内容:申请用公章全称(必填)")
	private String fd_offical_seal;
	@ApiModelProperty(value = "单据内容:相对方名称(必填)")
	private String fd_full_name;
	@ApiModelProperty(value = "单据内容:合同负责人(合同负责人请给在职员工的员工编号，流程发起人)")
	private String fd_emplno;
	@ApiModelProperty(value = "单据内容:合同份数(必填)")
	private String fd_copies;
	@ApiModelProperty(value = "单据内容:合同期限(≤3年|1 ＞3年|2 未附终止期限|3)")
	private String fd_contract_period;
	@ApiModelProperty(value = "单据内容:合同时间起(合同起止时间，请传yyyy-MM-dd格式字符串(仅支持该格式))")
	private String fd_start_time;
	@ApiModelProperty(value = "单据内容:合同时间止(合同起止时间，请传yyyy-MM-dd格式字符串(仅支持该格式))")
	private String fd_lasttime;
	@ApiModelProperty(value = "单据内容:收付款(必填,付款|1 收款|2 合同无价款|3)")
	private String fd_payment;
	@ApiModelProperty(value = "单据内容:收款条件(必填,一次性付款-预付货款|1 一次性付款-货到付款|2 帐扣|3 票折|4 补货|5 分期付款|6 <--票期≥45天|7   删除--> )")
	private String fd_condition;
	@ApiModelProperty(value = "单据内容:付款条件(必填,款到发货|1 赊销|2 签约后收款|3)")
	private String fd_payee_condition;
	@ApiModelProperty(value = "单据内容:签约后收款|3  需要填写")
	private String fd_payee_days;
	@ApiModelProperty(value = "收款明细列表")
	private List<CollectionInde> fd_collection_inde;
	@ApiModelProperty(value = "单据内容:合同未税金额(yyyy-MM-dd文本格式)")
	private String fd_taxed_price;
	@ApiModelProperty(value = "单据内容:税率")
	private String fd_tax_rate;
	@ApiModelProperty(value = "单据内容:含税金额")
	private String fd_tax_include;
	@ApiModelProperty(value = "单据内容:币种(转成value值)")
	private String fd_currency;
	@ApiModelProperty(value = "单据内容:是否延期条款（是|1 否|2 不传值默认为“否”）")
	private String fd_automatic;
	@ApiModelProperty(value = "有无押金（关联表）")
	private String fd_cash_pledge;
	@ApiModelProperty(value = "押金（关联表）")
	private String fd_cash;
	@ApiModelProperty(value = "单据内容:缴交时间（关联表）")
	private String fd_pay_time;
	@ApiModelProperty(value = "单据内容:退回时间（关联表）")
	private String fd_back_time;
	@ApiModelProperty(value = "单据内容:保证金类别（关联表）")
	private String fd_deposit_type;
	@ApiModelProperty(value = "单据内容:保证金编号（关联表）")
	private String fd_number;
	@ApiModelProperty(value = "单据内容:合同形式（电子合同|1 实体合同|2）")
	private String fd_contract_no;
	@ApiModelProperty(value = "单据内容:相对方联系人（）")
	private String fd_linkman;
	@ApiModelProperty(value = "单据内容:相对方联系电话（）")
	private String fd_contact_number;
	@ApiModelProperty(value = "单据内容:相对方邮箱（）")
	private String fd_email;
	@ApiModelProperty(value = "单据内容:相对方联系地址（）")
	private String fd_address;
	@ApiModelProperty(value = "单据内容:银行信息-联行号")
	private String fd_bank_code;
	@ApiModelProperty(value = "单据内容:银行信息-帐户户号")
	private String fd_account_code;
	@ApiModelProperty(value = "单据内容:银行信息-银行帐户名称")
	private String fd_account_name;
	@ApiModelProperty(value = "单据内容:银行信息-开户行名称")
	private String fd_deposit_name;
	@ApiModelProperty(value = "子公司以及签章单位信息（关联表）")
	private List<MultiSa> fd_multise;
	@ApiModelProperty(value = "相对方企业信息（关联表）")
	private List<MultiCo> fd_multico;
	@ApiModelProperty(value = "相对方角色信息列表（关联表）")
	private List<MultiRo> fd_multiro;
	@ApiModelProperty(value = "多方收付款信息（关联表）")
	private List<MultiPay> fd_multipay;
	@ApiModelProperty(value = "多方保证金信息（关联表）")
	private List<MultiBon> fd_multibon;
	@ApiModelProperty(value = "单据内容:履约清单")
	private List<KeepList> fd_keep_list;
	@ApiModelProperty(value = "单据内容:收付款清单")
	private List<PayList> fd_pay_list;
	@ApiModelProperty(value = "单据内容:合同状态")
	private String fd_contract_status;
	@ApiModelProperty(value = "单据内容:合同编号")
	private String fd_contract_numb;
	@ApiModelProperty(value = "单据内容(已归档节点):合同附件扫面件")
	private String fd_archive_file;
	@ApiModelProperty(value = "单据内容：合同借阅实体")
	private BorrowAc borrow_ac;
	@ApiModelProperty(value = "(合同借阅实体):资料类型")
	private String fd_type;
	@ApiModelProperty(value = "(合同借阅实体):借阅周期起始时间")
	private String fd_begin;
	@ApiModelProperty(value = "(合同借阅实体):借阅周期结束时间")
	private String fd_end;
	@ApiModelProperty(value = "(合同借阅实体):申请编号")
	private String applicationId;
	@ApiModelProperty(value = "(合同借阅实体):申请人")
	private String applicant;
	@ApiModelProperty(value = "(合同借阅实体):申请部门")
	private String appDepartment;
	@ApiModelProperty(value = "(合同借阅实体):借阅方式")
	private String fd_method;
	@ApiModelProperty(value = "(合同借阅实体):资料名称")
	private String fd_name;
	@ApiModelProperty(value = "(合同借阅实体):事由说明")
	private String fd_reason;

	/*合同解除字段*/
	@ApiModelProperty(value = "合同金额")
	private String fd_amount;
	@ApiModelProperty(value = "解除日期")
	private String fd_relieve_date;
	@ApiModelProperty(value = "解除原因")
	private String fd_relieve_reason;
	@ApiModelProperty(value = "违约金金额")
	private String fd_liquidated;
	@ApiModelProperty(value = "赔偿款流向")
	private String fd_flow_direction;
	@ApiModelProperty(value = "解除说明")
	private String fd_remarks;

	@ApiModelProperty(value = "解除说明")
	private String fd_compensation;
	@ApiModelProperty(value = "解除说明")
	private String fd_flow_compensation;
	@ApiModelProperty(value = "解除说明")
	private String fd_organizer;
	@ApiModelProperty(value = "解除说明")
	private String fd_department;
	@ApiModelProperty(value = "解除说明")
	private String fd_undertaker;
	@ApiModelProperty(value = "解除说明")
	private String fd_time;

}
