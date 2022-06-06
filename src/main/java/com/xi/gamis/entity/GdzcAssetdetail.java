package com.xi.gamis.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("gdzc_AssetDetail")
public class GdzcAssetdetail implements Serializable {

    private static final long serialVersionUID = 1L;

    private String czr;

    private String glzc;

    private Integer ssbm;

    @TableField("ID")
    private String id;

    private String fjbz;

    private String gg;

    private BigDecimal dj;

    private String img;

    private String zcbh;

    private String scs;

    private Integer glzt;

    private String syr;

    private String bz;

    @TableId(value = "bh", type = IdType.AUTO)
    private Integer bh;

    private String cwlb;

    private String rkzrr;

    private Integer spmcbh;

    private String gxsj;

    private String zcly;

    private String xq;

    private String gmrq;

    private String zczrr;

    private String gbm;

    private Integer printTimes;

    private String cfdd;

    private String sys;

    private String syfs;

    private String syfx;

    private String cwbh;

    private Integer zczt;

    private Integer sl;

    private String gys;

    private String htbh;

    private Long ckmxbh;

    private BigDecimal dj2;

    private Integer ssjl;


}
