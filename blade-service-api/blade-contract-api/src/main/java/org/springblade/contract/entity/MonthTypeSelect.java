package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springblade.core.mp.base.BaseEntity;

/**
 * @author xhbbo
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "T附表", description = "附表")
public class MonthTypeSelect extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 月份
     */
    @TableField(exist = false)
    private Integer mouth;
    /**
     * 一年内每月合同數量信息
     */
    @TableField(exist = false)
    private String count;
    /**
     * 統計查詢類型(公司)
     */
    @ApiModelProperty(value="統計查詢類型(公司)")
    @TableField(exist = false)
    private String company;
}
