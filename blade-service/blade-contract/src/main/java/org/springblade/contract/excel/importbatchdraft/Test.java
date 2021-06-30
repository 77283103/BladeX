package org.springblade.contract.excel.importbatchdraft;

import org.springblade.contract.util.ContractImportBatchDraftUtil;
import org.springblade.core.tool.jackson.JsonUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		File file = new File("C://Users//woche//Desktop//ty//统一批量起草合同模板.xlsx");
		MultipartFile multipartFile = ContractImportBatchDraftUtil.fileToMultipartFile(file);
		List<ContractImportBatchDraftExcel>contractImportBatchDraftExcels = ContractImportBatchDraftUtil.excelToContractExcelList(multipartFile);
		System.out.println(JsonUtil.toJson(contractImportBatchDraftExcels));
	}

}
