package org.springblade.contract.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.io.Serializable;


/**
 * 相对方管理 Excel导入数据实体类
 *
 * @author XHB
 * @date : 2020-09-23 19:35:02
 */
@Data
@ColumnWidth(25)
@HeadRowHeight(20)
@ContentRowHeight(18)
public class ContractCounterpartExcel implements Serializable {

	private static final long serialVersionUID = 1L;
	@ExcelProperty(value = "单位类型") private String classification;
	@ExcelProperty(value = "相对方类型") private String counterpartCategory;
	@ExcelProperty(value = "相对方名称") private String name;
	@ExcelProperty(value = "相对方性质") private String natureCategory;
	@ExcelProperty(value = "注册地址") private String registeredAddress;
	@ExcelProperty(value = "法定代表人") private String legalRepresentative;
	@ExcelProperty(value = "姓名+身份证号码") private String idNumber;
	@ExcelProperty(value = "护照号码") private String passportId;
	@ExcelProperty(value = "成立日期") private String establishDate;
	@ExcelProperty(value = "经营期限") private String operatingPeriod;
	@ExcelProperty(value = "营业执照发证日期") private String dateIssuanceBusinessLicense;
	@ExcelProperty(value = "注册资本") private String registeredCapital;
	@ExcelProperty(value = "币种") private String currencyCategory;
	@ExcelProperty(value = "社会统一信用代码") private String unifiedSocialCreditCode;
	@ExcelProperty(value = "组织机构代码") private String organizationCode;
	@ExcelProperty(value = "电子签章序列号") private String electronicSealSerialId;
	@ExcelProperty(value = "相关联系人") private String contactPersonName;
	@ExcelProperty(value = "联系人电话") private String contactPersonPhone;
	@ExcelProperty(value = "联系人邮箱") private String contactPersonMail;
	@ExcelProperty(value = "开户银行") private String depositBank;
	@ExcelProperty(value = "开户地址") private String accountOpeningAddress;
	@ExcelProperty(value = "银行账号") private String bankAccount;
	@ExcelProperty(value = "付款方式") private String paymentMethod;
	@ExcelProperty(value = "企业相关证件附件") private String attachedFiles;
	@ExcelProperty(value = "存续状态") private String existenceStatus;
	@ExcelProperty(value = "黑名单标识") private String blacklistLogo;
	@ExcelProperty(value = "是否注销") private String cancellation;
	@ExcelProperty(value = "备注") private String remarks;
	@ExcelProperty(value = "更名每月检视") private String renameMonthlyReview;
	@ExcelProperty(value = "半角名称") private String halfWidthName;






}
