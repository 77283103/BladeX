package org.springblade.contract.util;

import com.spire.doc.Document;
import com.spire.doc.DocumentObject;
import com.spire.doc.FileFormat;
import com.spire.doc.Section;
import com.spire.doc.documents.Paragraph;
import com.spire.doc.fields.DocPicture;
import lombok.AllArgsConstructor;
import org.springblade.core.tool.api.R;
import org.springblade.resource.feign.IFileClient;
import org.springblade.resource.vo.FileVO;

import java.util.List;
import java.util.Map;

/**
 * @author xhbbo
 * Java 使用新图片替换 Word 文档中的现有图片
 */
@AllArgsConstructor
public class ReplaceImages {
    private IFileClient fileClient;

    /**
     * @param annexFileDoc 原文档名称路径
     * @param dataModel    获取保存在数据库中的图片id从而获取新图片路径
     * @return
     */
    public String replaceImage(String annexFileDoc, Map dataModel) {
        //加载Word文档
        Document doc = new Document();
        doc.loadFromFile(annexFileDoc);
        //获取文档中的第一个节
        Section section = doc.getSections().get(0);
        //遍历该节中的所有段落
        for (Paragraph para : (Iterable<Paragraph>) section.getParagraphs()) {
            //遍历每个段落中的子元素
            for (DocumentObject obj : (Iterable<DocumentObject>) para.getChildObjects()) {
                //使用新图片替换文档中的现有图片
                if (obj instanceof DocPicture) {
                    DocPicture pic = (DocPicture) obj;
                    //将上传的图片替换掉文本原有的图片
                    R<List<FileVO>> result = fileClient.getByIds(String.valueOf(dataModel.get("image")));
                    result.getData().forEach(file -> {
                        pic.loadImage(file.getLink());
                    });
                }
            }
        }
        //保存结果文档
        doc.saveToFile(annexFileDoc, FileFormat.Docx_2013);
        return annexFileDoc;
    }
}
