package org.springblade.contract.util;

import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class AsposeWordToPdfUtils {


    /**
     * 判断是否有授权文件 如果没有则会认为是试用版，转换的文件会有水印
     *@return
     */
    public static boolean getLicense() {
        boolean result = false;
        try {
            InputStream is = AsposeWordToPdfUtils.class.getClassLoader().getResourceAsStream("license.xml");
            License aposeLic = new License();
            aposeLic.setLicense(is);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Word转PDF操作
     *@param sourcerFile 源文件
     *@param targetFile 目标文件
     */
    public static void doc2pdf(String sourcerFile,String targetFile) {
		// 验证License 若不验证则转化出的pdf文档会有水印产生
        if (!getLicense()) {
            return;
        }
        try {
			//新建一个空白pdf文档
            File file = new File(targetFile);
            FileOutputStream os = new FileOutputStream(file);
			//sourcerFile是将要被转化的word文档
            Document doc = new Document(sourcerFile);
			//全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换
            doc.save(os, SaveFormat.PDF);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
