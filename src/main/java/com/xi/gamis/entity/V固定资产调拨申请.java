package com.xi.gamis.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("V固定资产_调拨申请")
public class V固定资产调拨申请 implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer dfjl;

    private Integer spmcbh;

    private String sqcfdd;

    private String zczt;

    private String sqbmmc;

    private Integer bh;

    private String cfdd;

    private Integer dqbm;

    private Integer sl;

    private String bz;

    private Integer ssbm;

    private BigDecimal dj;

    private String glzc;

    private String sqr;

    private Integer ztbh;

    private String spr;

    private Integer splbbh;

    private String gys;

    @TableField("Expr1")
    private Integer Expr1;

    private Integer dqjl;

    private String dw;

    private String dbyy;

    private Integer dfbm;

    private String jl;

    private Integer zt;

    private String splb;

    private String dfSpr;

    private String sqsj;

    private String scs;

    private Integer glbh;

    private String bm;

    private String spmc;

    private String dfSpsj;

    private String zcbh;

    private String zczrr;

    private String fjbz;

    private Integer ssjl;


}
