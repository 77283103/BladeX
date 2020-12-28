package org.springblade.contract.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springblade.abutment.feign.IAbutmentClient;
import org.springblade.contract.vo.ContractFormInfoResponseVO;
import org.springblade.core.tool.utils.Charsets;
import org.springblade.core.tool.utils.CollectionUtil;
import org.springblade.resource.feign.IFileClient;
import org.springblade.system.feign.IDictBizClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xhbbo
 */
@Component
@AllArgsConstructor
public class exportTargetDataResultStatisticsUtils {
    private IDictBizClient bizClient;
    private IAbutmentClient abutmentClient;
    private IFileClient fileClient;
    /**
     * 导出excel
     * @param formInfoEntityList
     * @param response
     */
    @PostMapping("/exportTargetDataResultStatisticsUtils")
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "导出", notes = "")
    public void getExportStatisticsUtils(@RequestBody List<ContractFormInfoResponseVO> formInfoEntityList, List<String > list, HttpServletResponse response) {
        if(CollectionUtil.isNotEmpty(formInfoEntityList)){
            /* 导出文件名称 */
            String  fileName = "合同统计分析信息导出";
            WriteSheet sheet1 = new WriteSheet();
            /* 导出的sheet的名称 */
            sheet1.setSheetName("合同统计分析信息导出");
            sheet1.setSheetNo(0);
            /* 需要存入的数据 */
            List<List<Object>> data = new ArrayList<>();
            /* formInfoEntityList 表示要写入的数据 因为是前台显示列表 由前台进行传值，后期可以根据自己的需求进行改变 */
            for(ContractFormInfoResponseVO contractFormInfoEntity:formInfoEntityList){
				/* 属性 cloumns 表示一行，cloumns包含的数据是一行的数据
				  要将一行的每个值 作为list的一个属性存进到list里 ，数据要和展示的excel表头一致*/
                List<Object> cloumns = new ArrayList<Object>();
                /*合同类别*/
                cloumns.add(contractFormInfoEntity.getContractBigCategory());
                /*合同金额*/
                cloumns.add(contractFormInfoEntity.getContractAmount());
                /*签订数量*/
                cloumns.add(contractFormInfoEntity.getSigningCount());
                /*占比金额比例*/
                cloumns.add(contractFormInfoEntity.getAmountRatio());
                /*收支类型*/
                cloumns.add(contractFormInfoEntity.getColPayType());
                /*合同状态*/
                cloumns.add(bizClient.getValue("contract_status",contractFormInfoEntity.getContractStatus()).getData());
                /*签订单位*/
                cloumns.add(contractFormInfoEntity.getSigningEntity().getManageUnit());
                data.add(cloumns);
            }
            /* 表头名称，excel的表头 一个list对象为一行里的一个表头名称 */
            List<List<String>> headList = new ArrayList<List<String>>();
            /* 此处表头为一行要显示的所有表头，要和数据的顺序对应上  需要转换为list */
            List<String> head = Arrays.asList("合同类别","合同金额","签订数量","占比金额比例","收支类型","合同状态","签订单位");
            /* 为了生成一个独立的list对象，所进行的初始化 */
            List<String>  head2 =null;
            for( String head1:head){
                head2 = new ArrayList<>();
                /* 将表头的数据赋值进入list对象 */
                head2.add(head1);
                /* 将数据赋值进入最终要输出的表头 */
                headList.add(head2);
            }
            try {
                response.setContentType("application/vnd.ms-excel");
                response.setCharacterEncoding(Charsets.UTF_8.name());
                fileName = URLEncoder.encode(fileName, Charsets.UTF_8.name());
                response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
                EasyExcel.write(response.getOutputStream()).head(headList).sheet().doWrite(data);
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
