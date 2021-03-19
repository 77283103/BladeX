package org.springblade.contract.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.*;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class AsposeWordToPdfUtils {
	//正式
	/*private static final String tokenUrl="https://unici.pec.com.cn/common/user/access";
	private static final String tokenSY="https://unici.pec.com.cn/common/file/addWatermark";
	private static final String tokenXZ="https://unici.pec.com.cn/common/file/downloadWaterMarkDoc/";
	private static  final String username="admin_cont";
	private static  final String password="148a0bed39597ef88f3f8b53134993a5";*/
	//测试
	private static final String tokenUrl="http://sa.pec.com.cn:9080/common/user/access";
	private static final String tokenSY="http://sa.pec.com.cn:9080/common/file/addWatermark";
	private static  final String tokenXZ="http://sa.pec.com.cn:9080/common/file/downloadWaterMarkDoc/";
	private static  final String username="admin_ekp";
	private static  final String password="c5c85e7ef7747ce3f1649f44feb8b3bf";
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
            //File file = new File(targetFile);
			File file=ResourceUtils.getFile(targetFile);
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

	/**
	 * doc 转 docx
	 *@param sourcerFile 源文件
	 *@param targetFile 目标文件
	 */
	public static void doc2Docx(String sourcerFile,String targetFile) {
		// 验证License 若不验证则转化出的pdf文档会有水印产生
		if (!getLicense()) {
			return;
		}
		try {
			//新建一个空白pdf文档
			//File file = new File(targetFile);
			File file=ResourceUtils.getFile(targetFile);
			FileOutputStream os = new FileOutputStream(file);
			//sourcerFile是将要被转化的word文档
			Document docx = new Document(sourcerFile);
			//全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换
			docx.removeMacros();
			docx.save(os, SaveFormat.DOCX);
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * pdf转word操作
	 *@param sourcerFile 源文件
	 *@param targetFile 目标文件
	 */
	public static void pdf2doc(String sourcerFile,String targetFile) {
		// 验证License 若不验证则转化出的pdf文档会有水印产生
		if (!getLicense()) {
			return;
		}
		try {
			File file=ResourceUtils.getFile(targetFile);
			FileOutputStream os = new FileOutputStream(file);
			Document doc = new Document(sourcerFile);
			//全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换
			doc.save(os, SaveFormat.DOCX);
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	//获取链接地址文件的byte数据
	public static byte[] getUrlFileData(String fileUrl)
	{
		//连不上就会一直不走
		URL url = null;
		HttpURLConnection httpConn=null;
		byte[] fileData =null;
		try {
			url = new URL(fileUrl);
			httpConn = (HttpURLConnection) url.openConnection();
			httpConn.connect();
			InputStream cin = httpConn.getInputStream();
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = cin.read(buffer)) != -1) {
				outStream.write(buffer, 0, len);
			}
			cin.close();
			fileData = outStream.toByteArray();
			outStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileData;
	}

	public static String addWaterMak(InputStream in, String waterMarkText, String fileName, String paramfileId){

		HttpPost method = null;
		CloseableHttpClient client = null;
		CloseableHttpResponse response = null;
		HttpPost method_1 = null;
		CloseableHttpClient client_1 = null;
		CloseableHttpResponse response_1 = null;
		try{
			if(null==waterMarkText|| waterMarkText.length()>32) {
				throw new IllegalArgumentException();
			}
			Map<String, Object> tokenMap = new HashMap<String, Object>();
			tokenMap.put("username", username);
			tokenMap.put("password", password);
			JSONObject tokenMapJson = new JSONObject(tokenMap);
			//获取token
			client_1 = HttpClients.createDefault();
			method_1 = new HttpPost(tokenUrl);
			RequestConfig rc_1 = RequestConfig.custom().setConnectTimeout(60000).setConnectionRequestTimeout(60000).setSocketTimeout(60000).build();
			method_1.setConfig(rc_1);
			method_1.addHeader("Content-Type", "application/json;charset=UTF-8");
			StringEntity entity_1 = new StringEntity(tokenMapJson.toString(), ContentType.APPLICATION_JSON);
			method_1.setEntity(entity_1);
			response_1 = client_1.execute(method_1);
			String tokenResult = EntityUtils.toString(response_1.getEntity(), "utf-8");
			JSONObject tokenJson = JSON.parseObject(tokenResult);
			String token = tokenJson.getString("token");
			if (null!=token) {
				//添加水印
				client = HttpClients.createDefault();
				method = new HttpPost(tokenSY);
				RequestConfig rc = RequestConfig.custom().setConnectTimeout(350000).setConnectionRequestTimeout(350000).setSocketTimeout(200000).build();
				method.setConfig(rc);
				MultipartEntityBuilder builder = MultipartEntityBuilder.create();
				builder.setCharset(Charset.forName(Consts.UTF_8.name()));
				builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
				builder.addBinaryBody("file", in, ContentType.MULTIPART_FORM_DATA, fileName);
				builder.addTextBody("watermark", waterMarkText, ContentType.create("text/plain", Consts.UTF_8.name()));
				if(null!=paramfileId) {
					builder.addTextBody("fileId", paramfileId, ContentType.create("text/plain", Consts.UTF_8.name()));
				}
				HttpEntity entity = builder.build();
				method.setEntity(entity);
				method.addHeader("token", token);
				response = client.execute(method);
				if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					JSONObject jsonObject = null;
					return (jsonObject = JSON.parseObject(EntityUtils.toString(response.getEntity(), Consts.UTF_8.name()))).getString("fileId");
				}
			}else{
				return "获取token失败";
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if (response != null) {
					response.close();
				}
				if (response_1 != null) {
					response_1.close();
				}
				if (method_1 != null) {
					method_1.releaseConnection();
				}
				if (client_1 != null) {
					client_1.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "失败";
	}

	public static String downloadFile(String fileId) throws Exception {
		HttpGet method = null;
		CloseableHttpClient client = null;
		CloseableHttpResponse response = null;
		HttpPost method_1 = null;
		CloseableHttpClient client_1 = null;
		CloseableHttpResponse response_1 = null;
		try{
			Map<String, Object> tokenMap = new HashMap<String, Object>();
			tokenMap.put("username", username);
			tokenMap.put("password", password);
			JSONObject tokenMapJson = new JSONObject(tokenMap);
			//获取token
			client_1 = HttpClients.createDefault();
			method_1 = new HttpPost(tokenUrl);
			RequestConfig rc_1 = RequestConfig.custom().setConnectTimeout(35000).setConnectionRequestTimeout(35000).setSocketTimeout(20000).build();
			method_1.setConfig(rc_1);
			method_1.addHeader("Content-Type", "application/json;charset=UTF-8");
			StringEntity entity_1 = new StringEntity(tokenMapJson.toString(), ContentType.APPLICATION_JSON);
			method_1.setEntity(entity_1);
			response_1 = client_1.execute(method_1);
			String tokenResult = EntityUtils.toString(response_1.getEntity(), "utf-8");
			JSONObject tokenJson = JSON.parseObject(tokenResult);
			String token = tokenJson.getString("token");
			if (null!=token) {
				client = HttpClients.createDefault();
				method = new HttpGet( tokenXZ +fileId);
				RequestConfig rc = RequestConfig.custom().setConnectTimeout(35000).setConnectionRequestTimeout(35000).setSocketTimeout(20000).build();
				method.setConfig(rc);
				method.addHeader("token", token);
				response = client.execute(method);
				String linkResult = EntityUtils.toString(response.getEntity(), "utf-8");
				JSONObject linkJson = JSON.parseObject(linkResult);
				//下载文件
				if ("0".equals(linkJson.getString("code"))) {
					JSONObject linkDataJson = linkJson.getJSONObject("data");
					return linkDataJson.getString("downloadUrl");
				}else{
					return linkResult;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if (response != null) {
				response.close();
			}
			if (method != null) {
				method.releaseConnection();
			}
			if (client != null) {
				client.close();
			}

			if (response_1 != null) {
				response_1.close();
			}
			if (method_1 != null) {
				method_1.releaseConnection();
			}
			if (client_1 != null) {
				client_1.close();
			}
		}
		return "获取token失败";
	}

	public static void addWaterMark(String srcFile, String destFile, String text) throws Exception {
		// 待加水印的文件
		PdfReader reader = new PdfReader(srcFile);
		// 加完水印的文件
		PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(destFile));
		PdfContentByte content;
		// 设置透明度
		PdfGState gs = new PdfGState();
		gs.setFillOpacity(1f);
		// 设置字体
		BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
		// 循环对每页插入水印
		// 水印的起始
		content = stamper.getOverContent(1);
		content.setGState(gs);
		content.setFontAndSize(base, 10);
		// 开始
		content.beginText();
		// 设置颜色 默认为黑色
		content.setColorFill(BaseColor.BLACK);
		// 开始写入水印
		content.showTextAligned(Element.ALIGN_MIDDLE, text, 450,818, 0);
		content.endText();
		stamper.close();
		reader.close();
		/*
		 * 删除当前目录下的文件
		 */
		File file = new File(srcFile);
		if(file.exists()) {
			file.delete();
			System.out.println("删除成功");
		}
	}
}
