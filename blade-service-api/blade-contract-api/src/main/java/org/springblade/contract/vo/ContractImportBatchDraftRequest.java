package org.springblade.contract.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springblade.contract.excel.importbatchdraft.ContractImportBatchDraftExcel;
import org.springblade.contract.excel.importbatchdraft.util.ContractImportBatchDraftUtil;
import org.springblade.core.tool.utils.Func;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@ApiModel(description = "批量合同导入请求对象")
public class ContractImportBatchDraftRequest implements Serializable {

	@ApiModelProperty(value = "模板json")
	private String json;

	@ApiModelProperty(value = "模板名称")
	private String contractListName;

	@JsonSerialize(using = ToStringSerializer.class)
	@ApiModelProperty(value = "模板标识")
	private Long contractTemplateId;

	@ApiModelProperty(value = "大类")
	private String contractBigCategory;

	@ApiModelProperty(value = "小类")
	private String contractSmallCategory;

	@ApiModelProperty(value = "导入文件")
	private MultipartFile file;

	@ApiModelProperty(value = "合同来源")
	private String contractSoure;


	public List<ContractImportBatchDraftExcel> getContractExcelList(){
		if(Func.isNotEmpty(this.file)){
			List<ContractImportBatchDraftExcel>contractImportBatchDraftExcels = ContractImportBatchDraftUtil.excelToContractExcelList(this.file);
			contractImportBatchDraftExcels.forEach(contractImportBatchDraftExcel -> {
				contractImportBatchDraftExcel.setJson(this.json);
				contractImportBatchDraftExcel.setContractBigCategory(this.contractBigCategory);
				contractImportBatchDraftExcel.setContractSmallCategory(this.contractSmallCategory);
				contractImportBatchDraftExcel.setContractTemplateId(this.contractTemplateId);
				contractImportBatchDraftExcel.setContractSoure(this.contractSoure);
				contractImportBatchDraftExcel.setContractListName(this.contractListName);
			});
			return contractImportBatchDraftExcels;
		}
		return new ArrayList<>();
	}

}
