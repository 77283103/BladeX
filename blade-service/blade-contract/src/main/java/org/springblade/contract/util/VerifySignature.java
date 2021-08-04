package org.springblade.contract.util;

import com.spire.pdf.PdfDocument;
import com.spire.pdf.security.PdfSignature;
import com.spire.pdf.widget.PdfFormFieldWidgetCollection;
import com.spire.pdf.widget.PdfFormWidget;
import com.spire.pdf.widget.PdfSignatureFieldWidget;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xhbbo  Java 验证 PDF 数字签名的有效性/Java 获取 PDF 中的数字签名信息
 */
public class VerifySignature {
	public boolean verifySignature(String url) {
		//创建PdfDocument实例
		PdfDocument doc = new PdfDocument();
		//加载含有签名的PDF文件
		doc.loadFromFile("SimpleSign.pdf");
		//获取域集合
		PdfFormWidget pdfFormWidget = (PdfFormWidget) doc.getForm();
		PdfFormFieldWidgetCollection pdfFormFieldWidgetCollection = pdfFormWidget.getFieldsWidget();
		//遍历域
		for (int i = 0; i < pdfFormFieldWidgetCollection.getCount(); i++) {
			//判定是否为签名域
			if (pdfFormFieldWidgetCollection.get(i) instanceof PdfSignatureFieldWidget) {
				//获取签名域
				PdfSignatureFieldWidget signatureFieldWidget = (PdfSignatureFieldWidget) pdfFormFieldWidgetCollection.get(i);
				//获取签名
				PdfSignature signature = signatureFieldWidget.getSignature();
				//判定签名是否有效
				boolean result = signature.verifySignature();
				if (result) {
					System.out.println("有效签名");
				} else {
					System.out.println("无效签名");
				}
			}
		}
		return true;
	}


	public Map<String ,String> GetSignature(String  url){
		Map<String ,String> list= new ConcurrentHashMap<>();
		//创建PdfDocument实例
		PdfDocument pdf = new PdfDocument();
		//加载含有签名的PDF文件
		pdf.loadFromFile("AddSignature.pdf");

		//获取域集合
		PdfFormWidget pdfFormWidget = (PdfFormWidget) pdf.getForm();
		PdfFormFieldWidgetCollection pdfFormFieldWidgetCollection = pdfFormWidget.getFieldsWidget();

		//遍历域
		for (int i = 0; i < pdfFormFieldWidgetCollection.getCount(); i++) {
			//判定是否为签名域
			if (pdfFormFieldWidgetCollection.get(i) instanceof PdfSignatureFieldWidget) {
				//获取签名域
				PdfSignatureFieldWidget signatureFieldWidget = (PdfSignatureFieldWidget) pdfFormFieldWidgetCollection.get(i);
				//获取签名
				PdfSignature signature = signatureFieldWidget.getSignature();
				String location = signature.getLocationInfo();
				String reason = signature.getReason();
				String data = signature.getDate().toString();
				String name = signature.getSignatureName();
				list.put("location",location);
				list.put("reason",reason);
				list.put("data",data);
				list.put("name",name);
				System.out.println("签名位置信息："+ location +"\n"+
					"签名原因：" + reason +"\n"+
					"签名日期："+ data +"\n"+
					"签名人："+ name +"\n"+
					"文档中的签名坐标：X = "+ signatureFieldWidget.getLocation().getX()+ "  Y = "+ signatureFieldWidget.getLocation().getY());
			}
		}
		return list;
	}
}
