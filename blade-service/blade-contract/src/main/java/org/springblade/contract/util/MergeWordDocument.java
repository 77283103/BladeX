package org.springblade.contract.util;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import lombok.AllArgsConstructor;
import org.springblade.core.tool.api.R;
import org.springblade.resource.feign.IFileClient;
import org.springblade.resource.vo.FileVO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 处理文档WOrd拼接
 *
 * @author xhbbo
 */
@AllArgsConstructor
@Component
public class MergeWordDocument {
    private IFileClient fileClient;

    /**
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
}
