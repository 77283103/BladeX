package org.springblade.contract.util;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.DocxRenderData;
import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.pdf.PdfDocument;
import com.spire.pdf.automaticfields.PdfCompositeField;
import com.spire.pdf.automaticfields.PdfPageCountField;
import com.spire.pdf.automaticfields.PdfPageNumberField;
import com.spire.pdf.graphics.*;
import lombok.SneakyThrows;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springblade.core.tool.api.R;
import org.springblade.resource.feign.IFileClient;
import org.springblade.resource.vo.FileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.geom.Dimension2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;

import static org.springblade.contract.util.AsposeWordToPdfUtils.doc2Docx;

/**
 * 处理文档WOrd拼接
 *
 * @author xhbbo
 */
@Component
public class MergeWordDocument {
	@Autowired
	private IFileClient fileClient;
	private static String ftlPath = "D:/ftl/";

	/**
	 * Spire
	 *
	 * @param oldFileDoc 原范本文件Word的文件路径名称
	 * @param dataModel  获取范本的附件的字段值，获取附件信息
	 * @return
	 */
	public String SplicingWord(String oldFileDoc, Map dataModel) {
		//加载第一个文档
		Document document = new Document();
		document.loadFromFile(oldFileDoc);
		//获取模板中的附件
		R<List<FileVO>> result = fileClient.getByIds(String.valueOf(dataModel.get("annex")));
		result.getData().forEach(file -> {
			//使用insertTextFromFile方法将子文档的内容依次插入到第一个文档
			document.insertTextFromFile(file.getLink(), FileFormat.Docx_2013);
		});
		//保存文档
		document.saveToFile(oldFileDoc, FileFormat.Docx_2013);
		return oldFileDoc;
	}

	/**
	 * 2003- 版本的word
	 */
	private static final String word2003L = ".doc";
	/**
	 * 2007+ 版本的word
	 */
	private final static String word2007U = ".docx";

	/**
	 * docx转doc
	 * @param inPath
	 * @param outPath
	 */
	public static boolean docxToPDF(String inPath, String outPath) throws IOException {
		// 是否需清除中间转换的docx文档
		Boolean isDelete = false;
		String fileType = inPath.substring(inPath.lastIndexOf(".")).toLowerCase();
		if (word2003L.equals(fileType)) {
			doc2Docx(inPath, inPath + "x");
			inPath = inPath + "x";
			isDelete = true;
		} else if (word2007U.equals(fileType)) {

		} else {
			return false;
		}
		FileInputStream fileInputStream = null;
		FileOutputStream fileOutputStream = null;
		try {
			fileInputStream = new FileInputStream(inPath);
			XWPFDocument xwpfDocument = new XWPFDocument(fileInputStream);
			PdfOptions pdfOptions = PdfOptions.create();
			fileOutputStream = new FileOutputStream(outPath);
			PdfConverter.getInstance().convert(xwpfDocument, fileOutputStream, pdfOptions);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			fileInputStream.close();
			fileOutputStream.close();
			if (isDelete) {
				deleteDocx(inPath);
			}
		}
		return true;
	}
	/**
	 * 清除临时转换docx
	 *
	 * @param path
	 */
	public static void deleteDocx(String path) {
		File file = new File(path);
		file.delete();
	}

	/**
	 * Spire
	 * 去除Evaluation Warning : The document was created with Spire.PDF for Java.
	 *
	 * @param path
	 * @param outPath
	 */
	public static void AddPdfPageNumbers(String path, String outPath) {
		//加载PDF文档
		PdfDocument pdf = new PdfDocument();
		pdf.loadFromFile(path);
		//添加一个空白页，目的为了删除jar包添加的水印，后面再移除这一页
		pdf.getPages().add();
		//创建字体
		PdfTrueTypeFont font = new PdfTrueTypeFont(new Font("宋体", Font.PLAIN, 10), true);
		//遍历文档中的页
		for (int i = 0; i < pdf.getPages().getCount(); i++) {
			Dimension2D pageSize = pdf.getPages().get(i).getSize();
			float y = (float) pageSize.getHeight() - 40;
			//初始化页码域
			PdfPageNumberField number = new PdfPageNumberField();
			//初始化总页数域
			PdfPageCountField count = new PdfPageCountField();
			//创建复合域
			PdfCompositeField compositeField = new PdfCompositeField(font, PdfBrushes.getBlack(), "第{0}页 共{1}页", number, count);
			//设置复合域内文字对齐方式
			compositeField.setStringFormat(new PdfStringFormat(PdfTextAlignment.Right, PdfVerticalAlignment.Top));
			//测量文字大小
			Dimension2D textSize = font.measureString(compositeField.getText());
			//设置复合域的在PDF页面上的位置及大小
			compositeField.setBounds(new Rectangle2D.Float(((float) pageSize.getWidth() - (float) textSize.getWidth()) / 2, y, (float) textSize.getWidth(), (float) textSize.getHeight()));
			//将复合域添加到PDF页面
			compositeField.draw(pdf.getPages().get(i).getCanvas());
		}
		//移除第一个页
		pdf.getPages().remove(pdf.getPages().get(pdf.getPages().getCount() - 1));
		//保存为另外一个文档
		pdf.saveToFile(outPath);
	}

	/**
	 * @param newFileDoc 合同范本doc
	 * @param newFilePdf 合同范本pdf
	 */
	public void wordToPdf(String newFileDoc, String newFilePdf) {
		//设置日期格式
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = df.format(new Date());
		//初始化文件   1.拼接后docx文档  2.拼接后pdf文档  3.转类型doc转docx文档
		String mergeFileDocx = ftlPath +  date + ".docx";
		String newFileDocx = ftlPath +  date + ".docx";
		String docxFileUrl="1369235409415720961,1369235690593472513";
		// office转wps,处理兼容问题
		AsposeWordToPdfUtils.doc2Docx(newFileDoc, newFileDocx);
		//拼接文件名字数组
		List<String> filepaths = new ArrayList<>();
		filepaths.add(0, mergeFileDocx);
		filepaths.add(1, newFilePdf);
		filepaths.add(2, newFileDocx);
		//获取模板中的附件
		List<FileVO> result = fileClient.getByIds(docxFileUrl).getData();
		result.forEach(file -> {
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

	}

	/**
	 * word拼接方法三
	 * @param filepaths 相关文档  1.合同范本源模板   附件   1.拼接后文档  2.pdf文档
 	 */
	@SneakyThrows
	private static void magerDocx(List<String> filepaths){
		XWPFTemplate template = XWPFTemplate.compile(filepaths.get(0)).render(
			new HashMap<String, Object>(){{
				for (int i = 3; i <filepaths.size()-1 ; i++) {
					put("docx_word"+i, new DocxRenderData(new File(filepaths.get(i))));
				}
			}});
		FileOutputStream out = new FileOutputStream(filepaths.get(1));
		template.write(out);
		out.flush();
		out.close();
		template.close();
		// 拼接后文档docx转pdf
		docxToPDF(filepaths.get(1), filepaths.get(2));
	}
}
