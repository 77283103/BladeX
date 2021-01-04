package org.springblade.contract.util;

import com.alibaba.fastjson.JSONObject;
import feign.form.ContentType;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springblade.contract.entity.ContractFormInfoEntity;
import org.springblade.contract.enums.TemplateExporterEnum;
import org.springblade.core.tool.api.R;
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

	//private static String _PATH="D:/ftl/";//模板路径
	private static String _PATH="/ftl/";//模板路径
	//建一个静态的本类
	private static TemplateExportUntil templateExportUntil;
	@Autowired
	private IFileClient fileClient;
	//初始化
	@PostConstruct
	public void init() {
		templateExportUntil = this;
	}
	public FileVO templateSave(ContractFormInfoEntity contractFormInfoEntity,TemplateRequestVO templateVO,String json,JSONObject j) {
		String pdfId="";
		//第一步：创建一个Configuration对象，直接new一个对象。构造方法的参数就是freemarker对于的版本号。
		Configuration configuration = new Configuration();
		// 第二步：设置模板文件所在的路径。
		try {
			configuration.setDirectoryForTemplateLoading( ResourceUtils.getFile(_PATH));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//第三步：设置模板文件使用的字符集。一般就是utf-8.
		configuration.setDefaultEncoding("utf-8");
		// 第四步：加载一个模板，创建一个模板对象。
		Template template = null;
		try {
			template = configuration.getTemplate(templateVO.getTemplateCode()+".ftl");
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 第五步：创建一个模板使用的数据集，可以是pojo也可以是map。一般是Map。
		Map dataModel = new HashMap();
		// 第六步 定义向数据集中添加数据
		//--------------从这里开始取所需要的数据 start 通过范本类型来执行对应方法
		dataModel=TemplateExporterEnum.fromValue(templateVO.getTemplateCode()).setScheduler(contractFormInfoEntity, templateVO, json, j);
		//--------------从这里开始取所需要的数据 end
		// 设置生成doc的文档名称
		//设置日期格式
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = df.format(new Date());
		//String newFileDoc="D:/ftl/"+templateVO.getTemplateCode()+date+".doc";
		String newFileDoc=_PATH+templateVO.getTemplateCode()+date+".doc";
		// 设置生成pdf的文档名称
		//String newFilePdf="D:/ftl/"+templateVO.getTemplateCode()+date+".pdf";
		String newFilePdf=_PATH+templateVO.getTemplateCode()+date+".pdf";
		//File outFile = new File(newFileDoc);
		File outFile=null;
		try {
			outFile=ResourceUtils.getFile(newFileDoc);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Writer out = null;
		FileOutputStream fos=null;
		FileVO files=null;
		// 第七步：调用模板对象的process方法输出文件。
		try {
			//out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), StandardCharsets.UTF_8));
			fos = new FileOutputStream(outFile);
			OutputStreamWriter oWriter = new OutputStreamWriter(fos,"UTF-8");
			out = new BufferedWriter(oWriter);
			assert template != null;
			template.process(dataModel, out);
			// 第八步：关闭流。
			oWriter.close();
			fos.close();
			out.close();
			//doc转为pdf
			AsposeWordToPdfUtils.doc2pdf(newFileDoc,newFilePdf);
			//File filePDF = new File(newFilePdf);
			File filePDF=ResourceUtils.getFile(newFilePdf);
			MultipartFile multipartFile = new MockMultipartFile("file", filePDF.getName(),
				ContentType.MULTIPART.toString(), new FileInputStream(filePDF));
			/* 上传文件 */
			R<FileVO> fileVO=templateExportUntil.fileClient.save(multipartFile);
			files=fileVO.getData();
			files.setDomain(newFilePdf);
		} catch (TemplateException | IOException e) {
			e.printStackTrace();
		}

		return files;
	}
}
