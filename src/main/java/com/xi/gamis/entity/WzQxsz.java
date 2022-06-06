package com.xi.gamis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 权限设置
 * </p>
 *
 * @author GongQiang
 * @since 2022-01-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("WZ_qxsz")
public class WzQxsz implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "bh", type = IdType.AUTO)
    private Integer bh;

    /**
     * 员工编号
     */
    private Integer ygbh;

    /**
     * 商品价格类别管理权限
     */
    private String jglbqx;

    /**
     * 领料审批权限
     */
    private String llspqx;

    /**
     * 领料查询权限
     */
    private String llcxqx;

    /**
     * 鲜货查询权限
     */
    private String xhcxqx;

    /**
     * 更新时间
     */
    private String gxsj;

    private Boolean hhflTh;

    private String pmglqx;

    private String xdfhqx;

    private Integer jlbh;

    private String hhspfl;

    private String kfqx;

    private String hhqx;

    private String xdqx;

    private Boolean hhflGh;

    private Boolean hhflXh;

    private String xdspqx;

    @TableField("hhfl_Print")
    private Boolean hhflPrint;

    private String czr;


}
