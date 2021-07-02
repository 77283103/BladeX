package org.springblade.contract.excel.importbatchdraft.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.metadata.ReadSheet;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.springblade.contract.excel.importbatchdraft.*;
import org.springblade.contract.excel.importbatchdraft.constant.ContractImportBatchDraftSheet;
import org.springblade.core.excel.util.ExcelUtil;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContractImportBatchDraftUtil {


	public static List<ContractImportBatchDraftExcel> excelToContractExcelList(MultipartFile multipartFile){
		Map<String,Integer>sheetMap = excelToSheetMap(multipartFile);
		//解析合同主体
		List<ContractImportBatchDraftExcel> contractImportBatchDraftExcels = ExcelUtil.read(multipartFile, sheetMap.get(ContractImportBatchDraftSheet.CONTRACT),1, ContractImportBatchDraftExcel.class);
		//解析相对方
		List<ContractCounterpartImportBatchDraftExcel> contractCounterpartImportBatchDraftExcels = ExcelUtil.read(multipartFile, sheetMap.get(ContractImportBatchDraftSheet.OPPOSITE_PARTY),1, ContractCounterpartImportBatchDraftExcel.class);
		//解析签呈依据
		List<ContractAccordingImportBatchDraftExcel> contractAccordingImportBatchDraftExcels = ExcelUtil.read(multipartFile, sheetMap.get(ContractImportBatchDraftSheet.BASIS),1, ContractAccordingImportBatchDraftExcel.class);
		//解析履约-保证金
		List<ContractBondImportBatchDraftExcel> contractBondImportBatchDraftExcels = ExcelUtil.read(multipartFile, sheetMap.get(ContractImportBatchDraftSheet.BOND),1, ContractBondImportBatchDraftExcel.class);
		//解析履约-计划收付款
		List<PerCollectPayImportBatchDraftExcel> perCollectPayImportBatchDraftExcels = ExcelUtil.read(multipartFile, sheetMap.get(ContractImportBatchDraftSheet.PAY),1, PerCollectPayImportBatchDraftExcel.class);
		//合同主体关联相关集合
		contractImportBatchDraftExcels.forEach(contractImportBatchDraftExcel -> {
			contractImportBatchDraftExcel.relationAccordingList(contractAccordingImportBatchDraftExcels);
			contractImportBatchDraftExcel.relationBondList(contractBondImportBatchDraftExcels);
			contractImportBatchDraftExcel.relationCounterpartList(contractCounterpartImportBatchDraftExcels);
			contractImportBatchDraftExcel.relationPerCollectPayList(perCollectPayImportBatchDraftExcels);
		});
		return contractImportBatchDraftExcels;
	}


	public static Map<String,Integer> excelToSheetMap(File file){
		ExcelReaderBuilder excelReaderBuilder = EasyExcel.read(file);
		ExcelReader excelReader = excelReaderBuilder.build();
		List<ReadSheet> sheets = excelReader.excelExecutor().sheetList();
		Map<String,Integer> sheetsMap = new HashMap<>();
		sheets.forEach(readSheet -> {
			sheetsMap.put(readSheet.getSheetName(),readSheet.getSheetNo());
		});
		return sheetsMap;
	}

	public static Map<String,Integer> excelToSheetMap(MultipartFile multipartFile){
		File file = null;
		try {
			file = multipartFileToFile(multipartFile);
		} catch (Exception e) {
			return null;
		}
		return excelToSheetMap(file);
	}

	public static File multipartFileToFile(MultipartFile file) throws Exception {

		File toFile = null;
		if (file.equals("") || file.getSize() <= 0) {
			file = null;
		} else {
			InputStream ins = null;
			ins = file.getInputStream();
			toFile = new File(file.getOriginalFilename());
			inputStreamToFile(ins, toFile);
			ins.close();
		}
		return toFile;
	}

	private static void inputStreamToFile(InputStream ins, File file) {
		try {
			OutputStream os = new FileOutputStream(file);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			os.close();
			ins.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static MultipartFile fileToMultipartFile(File file) {
		FileItem fileItem = createFileItem(file);
		MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
		return multipartFile;
	}

	private static FileItem createFileItem(File file) {
		FileItemFactory factory = new DiskFileItemFactory(16, null);
		FileItem item = factory.createItem("textField", "text/plain", true, file.getName());
		int bytesRead = 0;
		byte[] buffer = new byte[8192];
		try {
			FileInputStream fis = new FileInputStream(file);
			OutputStream os = item.getOutputStream();
			while ((bytesRead = fis.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			os.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return item;
	}
}
