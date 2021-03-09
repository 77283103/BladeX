package org.springblade.contract.util;

import com.alibaba.fastjson.JSONObject;
import feign.form.ContentType;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springblade.contract.entity.ContractFormInfoEntity;
import org.springblade.contract.enums.TemplateExporterEnum;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.resource.feign.IFileClient;
import org.springblade.resource.vo.FileVO;
import org.springblade.system.vo.TemplateRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class TemplateExportUntil {

	/*private static  String ftlPath;
	@Value("${api.file.ftlPath}")
	public  void setFtlPath(String ftlPath) {
		TemplateExportUntil.ftlPath = ftlPath;
	}*/
	private static String ftlPath = "D:/ftl/";
	//建一个静态的本类
	private static TemplateExportUntil templateExportUntil;
	private static ReplaceImages replaceImages;
	private static MagerUtils magerUtils;
	@Autowired
	private IFileClient fileClient;

	//初始化
	@PostConstruct
	public void init() {
		templateExportUntil = this;
	}

	public FileVO templateSave(ContractFormInfoEntity contractFormInfoEntity, TemplateRequestVO templateVO, String json, JSONObject j) {
		String pdfId = "";
		//第一步：创建一个Configuration对象，直接new一个对象。构造方法的参数就是freemarker对于的版本号。
		Configuration configuration = new Configuration();
		// 第二步：设置模板文件所在的路径。
		try {
			configuration.setDirectoryForTemplateLoading(ResourceUtils.getFile(ftlPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//第三步：设置模板文件使用的字符集。一般就是utf-8.
		configuration.setDefaultEncoding("utf-8");
		// 第四步：加载一个模板，创建一个模板对象。
		Template template = null;
		try {
			template = configuration.getTemplate(templateVO.getTemplateCode() + ".ftl");
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 第五步：创建一个模板使用的数据集，可以是pojo也可以是map。一般是Map。
		Map dataModel = new HashMap();
		// 第六步 定义向数据集中添加数据
		//--------------从这里开始取所需要的数据 start 通过范本类型来执行对应方法
		dataModel = TemplateExporterEnum.fromValue(templateVO.getTemplateCode()).setScheduler(contractFormInfoEntity, templateVO, json, j);
		//--------------从这里开始取所需要的数据 end
		// 设置生成doc的文档名称
		//设置日期格式
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = df.format(new Date());
		//String newFileDoc="D:/ftl/"+templateVO.getTemplateCode()+date+".doc";
		String newFileDoc = ftlPath + templateVO.getTemplateCode() + date + ".doc";
		// 设置生成pdf的文档名称
		//String newFilePdf="D:/ftl/"+templateVO.getTemplateCode()+date+".pdf";
		String newFilePdf = ftlPath + templateVO.getTemplateCode() + date + ".pdf";
		//File outFile = new File(newFileDoc);
		File outFile = null;
		try {
			outFile = ResourceUtils.getFile(newFileDoc);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Writer out = null;
		FileOutputStream fos = null;
		FileVO files = null;
		// 第七步：调用模板对象的process方法输出文件。
		try {
			//out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), StandardCharsets.UTF_8));
			fos = new FileOutputStream(outFile);
			OutputStreamWriter oWriter = new OutputStreamWriter(fos, "UTF-8");
			out = new BufferedWriter(oWriter);
			assert template != null;
			template.process(dataModel, out);
			// 第八步：关闭流。
			oWriter.close();
			fos.close();
			out.close();

			// 测试代码 需要拼接的子文档
			String fileName1 = "D:/VMware/附件一.docx";
			String fileName2 = "D:/VMware/附件二.docx";
			String contractFile = "D:/VMware/转类型文件.docx";
			String outContractFile = "D:/VMware/拼接后输出文件.docx";
			String newFilePDF = "D:/VMware/拼接后文档docx转.pdf";
			// office转wps,处理兼容问题
			AsposeWordToPdfUtils.doc2Docx(newFileDoc, contractFile);
			// wood拼接后文档路径
			List<String> filepathsTest = new ArrayList<>();
			filepathsTest.add(0, outContractFile);
			filepathsTest.add(1, newFilePDF);
			filepathsTest.add(2, contractFile);
			filepathsTest.add(3, fileName1);
			filepathsTest.add(4, fileName2);
			try {
				//MagerUtils.mergeDoc(filepathsTest);
			} catch (Exception e) {
				e.printStackTrace();
			}
			new MergeWordDocument().wordToPdf(newFileDoc,newFilePDF);
			//判断是否存在需要拼接的附件
			if (!Func.isNull(dataModel.get("annex")) && Func.isNoneBlank(dataModel.get("annex").toString())) {
				//初始化文件   1.拼接后docx文档  2.拼接后pdf文档  3.转类型doc转docx文档
				String mergeFileDocx = ftlPath + templateVO.getTemplateCode() + date + ".docx";
				String newFileDocx = ftlPath + templateVO.getTemplateCode() + date + ".docx";
				// office转wps,处理兼容问题
				AsposeWordToPdfUtils.doc2Docx(newFileDoc, newFileDocx);
				//拼接文件名字数组
				List<String> filepaths = new ArrayList<>();
				filepaths.add(0, mergeFileDocx);
				filepaths.add(1, newFilePdf);
				filepaths.add(2, newFileDocx);
				//获取模板中的附件
				List<FileVO> result = fileClient.getByIds(String.valueOf(dataModel.get("annex"))).getData();
				result.forEach(file -> {
					//新建空文件
					int index = file.getName().lastIndexOf(".");
					String pathname=ftlPath + file.getName().substring(0, index) + date + ".docx";
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
					filepaths.add(pathname);
				});
				try {
					//拼接文档
					MagerUtils.mergeDoc(filepaths);
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					for (int i = 2; i <filepaths.size()-1 ; i++) {
						File file=new File(filepaths.get(i));
						file.delete();
					}
				}
			} else {
				//doc转为pdf
				AsposeWordToPdfUtils.doc2pdf(newFileDoc, newFilePdf);
			}
			//判断是否有需要添加或覆盖的图片
			if (!Func.isNull(dataModel.get("image")) && Func.isNoneBlank(dataModel.get("image").toString())) {
				newFileDoc = replaceImages.replaceImage(newFileDoc, dataModel);
			}
			File fileDoc = new File(newFileDoc);
			File filePDF = ResourceUtils.getFile(newFilePdf);
			MultipartFile multipartFile = new MockMultipartFile("file", filePDF.getName(),
				ContentType.MULTIPART.toString(), new FileInputStream(filePDF));
			/* 上传文件 */
			R<FileVO> fileVO = templateExportUntil.fileClient.save(multipartFile);
			if (fileDoc.exists()) {
				fileDoc.delete();
				System.out.println("删除成功");
			}
			/*if(filePDF.exists()) {
				filePDF.delete();
				System.out.println("删除成功");
			}*/
			files = fileVO.getData();
			files.setDomain(newFilePdf);
		} catch (TemplateException | IOException e) {
			e.printStackTrace();
		}
		return files;
	}
}
