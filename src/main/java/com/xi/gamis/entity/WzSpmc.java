package com.xi.gamis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author GongQiang
 * @since 2022-01-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("WZ_spmc")
public class WzSpmc implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品类别编号
     */
    private Integer splbbh;

    /**
     * 商品名称排序号
     */
    private String xh;

    /**
     * 商品名称
     */
    private String spmc;

    /**
     * 计量单位
     */
    private String dw;

    /**
     * 是否是鲜货
     */
    private Integer xhbs;

    /**
     * 拼音简码
     */
    private String pyjm;

    /**
     * 条形码
     */
    private String txm;

    /**
     * 更新时间
     */
    private String gxsj;

    /**
     * 操作人
     */
    private String czr;

    /**
     * 月购季购
     */
    private Integer ygjg;

    /**
     * 是否是后定价商品,=0:n,!0:y
     */
    private Integer hdjsp;

    /**
     * 下单方式
     */
    private Integer xdfs;

    /**
     * 贵重物品
     */
    private Integer gzwp;

    /**
     * 最小单位编号
     */
    private Integer hsdwbh;

    /**
     * 换算分倍率
     */
    private Double hsbl;

    /**
     * 是否纳入半成品管理
     */
    private Integer bcpgl;

    private String jxsxx;

    private String hhfs;

    private Integer bzq;

    private Double loss;

    @TableId(value = "spmcbh", type = IdType.AUTO)
    private Integer spmcbh;

    private String xddw;

    private String scsxx;


}
