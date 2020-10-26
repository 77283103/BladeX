package org.springblade.contract.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springblade.contract.entity.*;
import org.springblade.core.mp.base.BaseEntity;

import java.math.BigDecimal;
import java.util.List;


/**
 *  实体类
 *
 * @author 史智伟
 * @date : 2020-09-23 18:04:37
 */
@Data
@ColumnWidth(25)
@HeadRowHeight(20)
@ContentRowHeight(18)
public class ContractFormInfoExcel extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 合同名称
	 */
	@ColumnWidth(15)
	@ExcelProperty("合同名称")
	private String contractName;
	/**
	 * 合同编号
	 */
	@ColumnWidth
	@ExcelProperty("合同编号")
	private String contractNumber;
	/**
	 * 合同文本
	 */
	@ColumnWidth
	@ExcelProperty("合同编号")
	private String textFile;
	/**
	 * 合同附件
	 */
	@ColumnWidth
	@ExcelProperty("合同编号")
	private String attachedFiles;
	/**
	 * 币种
	 */
	@ColumnWidth
	@ExcelProperty("合同编号")
	private String currencyCategory;
	/**
	 * 合同金额
	 */
	@ColumnWidth
	@ExcelProperty("合同编号")
	private BigDecimal contractAmount;
	/**
	 * 变更状态
	 */
	@ColumnWidth
	@ExcelProperty("合同编号")
	private String changeCategory;
	/**
	 * 变更合同ID
	 */
	@ColumnWidth
	@ExcelProperty("合同编号")
	private Long changeContractId;
	/**
	 * 合同节点状态
	 */
	@ColumnWidth
	@ExcelProperty("合同编号")
	private String contractStatus;
	/**
	 * 合同审核状态
	 */
	@ColumnWidth
	@ExcelProperty("合同编号")
	private String submitStatus;
	/**
	 * 合同来源
	 */
	@ColumnWidth
	@ExcelProperty("合同编号")
	private String contractSoure;
	/**
	 * 合同文件导出状态，是否导出
	 */
	@ColumnWidth
	@ExcelProperty("合同编号")
	private String fileExportCategory;
	/**
	 * 合同一级分类
	 */
	@ColumnWidth
	@ExcelProperty("合同编号")
	private String contractBigCategory;
	/**
	 * 合同二级分类
	 */
	@ColumnWidth
	@ExcelProperty("合同编号")
	private String contractSmallCategory;

	/**
	 * 关联相对方信息
	 */
	@ApiModelProperty(value="最高合同金额")
	@TableField(exist = false)
	private List<ContractCounterpartEntity> counterpartList;

	/**
	 * 关联依据信息
	 */
	@ApiModelProperty(value="最高合同金额")
	@TableField(exist = false)
	private List<ContractAccordingEntity> accordingList;

	/**
	 * 关联履约信息
	 */
	@ApiModelProperty(value="最高合同金额")
	@TableField(exist = false)
	private List<ContractPerformanceEntity> performanceList;


	/**
	 * 关联合同评估信息
	 */
	@ApiModelProperty(value="评估信息")
	@TableField(exist = false)
	private ContractAssessmentEntity assessmentEntity;

	/**
	 * 关联合同归档信息
	 */
	@ApiModelProperty(value="归档信息")
	@TableField(exist = false)
	private ContractArchiveEntity archiveEntity;

	/**
	 * 合同文件导出次数统计
	 */
	@ApiModelProperty(value = "合同文件导出次数统计")
	private Integer fileExportCount;

	/**
	 * 关联合同用印信息
	 */
	@ApiModelProperty(value = "用印信息")
	@TableField(exist = false)
	private ContractSealUsingInfoEntity sealInfoEntity;

	/**
	 * 关联合同签订信息
	 */
	@ApiModelProperty(value = "签订信息")
	@TableField(exist = false)
	private ContractSigningEntity signingEntity;
}
