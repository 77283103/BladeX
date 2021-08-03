package org.springblade.contract.util;

import com.alibaba.fastjson.JSONObject;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import feign.form.ContentType;
import org.springblade.contract.entity.ContractFormInfoEntity;
import org.springblade.contract.enums.TemplateExporterEnum;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.resource.feign.IFileClient;
import org.springblade.resource.vo.FileVO;
import org.springblade.system.vo.TemplateRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Component("TemplateExportUntil")
public class TemplateExportUntil {

	@Value("${api.file.ftlPath}")
	private String ftlPath;
	@Autowired
	private IFileClient fileClient;
	/**建一个静态的本类**/
	private static TemplateExportUntil templateExportUntil;

	//初始化
	@PostConstruct
	public void init() {
		templateExportUntil = this;
	}

	public FileVO templateSave(ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json, JSONObject j) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = df.format(new Date());
		List<String> filepaths = new ArrayList<>();
		String pdfId = "";
		// 第五步：创建一个模板使用的数据集，可以是pojo也可以是map。一般是Map。
		Map dataModel;
		FileVO files = null;
		// 第六步 定义向数据集中添加数据
		//--------------从这里开始取所需要的数据 start 通过范本类型来执行对应方法
		if (!Func.isNull(j.get("annex")) && Func.isNoneBlank(j.get("annex").toString())) {
			//拼接文件名字数组
			filepaths = new ArrayList<>();
			//获取模板中的附件
			List<FileVO> result = templateExportUntil.fileClient.getByIds(String.valueOf(j.get("annex"))).getData();
			for (FileVO file : result) {
				//新建空文件
				int index = file.getName().lastIndexOf(".");
				String suffix = file.getName().substring(index);
				//判断是否为pdf文件，pdf文件不需要转换
				String pathname = ftlPath + file.getName().substring(0, index) + df.format(new Date()) + suffix;
				//创建空docx文件
				File filePDF = new File(pathname);
				//建立输出字节流
				FileOutputStream fosx = null;
				try {
					fosx = new FileOutputStream(filePDF);
					//将根据URL获取到的数据流写到空docx文件
					fosx.write(AsposeWordToPdfUtils.getUrlFileData(file.getLink()));
					fosx.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(!".docx".equals(suffix)){
					String docx = ftlPath + file.getName().substring(0, index) + df.format(new Date()) + ".docx";
					if (".doc".equals(suffix)){
						AsposeWordToPdfUtils.doc2Docx(pathname,docx);
					}else if (".pdf".equals(suffix)){
						MergeWordDocument.pdf2docx(pathname,docx);
					}
					File filedoc = new File(pathname);
					filedoc.delete();
					pathname=docx;
				}
				filepaths.add(pathname);
			}
		}
		dataModel = TemplateExporterEnum.fromValue(templateVO.getTemplateCode()).setScheduler(filepaths, contractFormInfoEntity, templateVO, json, j);
		//模板地址
		String path = MergeWordDocument.getTemplateFTLFile(ftlPath, templateVO, contractFormInfoEntity.getContractTemplateId());
		//拼接后的地址
		String mergeFileDocx = ftlPath + templateVO.getTemplateCode() + "_M_" + date + ".docx";
		//转成pdf的地址
		String newFilePdf = ftlPath + templateVO.getBeanName() + date + ".pdf";
		//读取模板
		XWPFTemplate template;
		//判断config是否为空，不为空表示有关联表
		Configure config =(Configure)dataModel.get("config");
		if(Func.isEmpty(config)){
			template = XWPFTemplate.compile(path);
		}else{
			template = XWPFTemplate.compile(path,config);
		}
		template.render(dataModel.get("dataModel"));
		FileOutputStream out;
		File templateFile = new File(mergeFileDocx);
		try {
			out = new FileOutputStream(templateFile);
			template.write(out);
			out.flush();
			out.close();
			template.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			for (int i = 0; i < filepaths.size() - 1; i++) {
				File file = new File(filepaths.get(i));
				file.delete();
			}
		}
		//--------------从这里开始取所需要的数据 end
		//doc转为pdf
		AsposeWordToPdfUtils.doc2pdf(mergeFileDocx, newFilePdf);
		File filePDF = null;
		try {
			filePDF = ResourceUtils.getFile(newFilePdf);
			MultipartFile multipartFile = new MockMultipartFile("file", filePDF.getName(),
				ContentType.MULTIPART.toString(), new FileInputStream(filePDF));
			/* 上传文件 */
			R<FileVO> fileVO = templateExportUntil.fileClient.save(multipartFile);
			templateFile = new File(mergeFileDocx);
			if (templateFile.exists()) {
				templateFile.delete();
				System.out.println("删除成功");
			}
			files = fileVO.getData();
			files.setDomain(newFilePdf);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return files;
	}
}
