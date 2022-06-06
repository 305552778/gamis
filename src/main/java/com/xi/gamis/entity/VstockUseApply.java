package com.xi.gamis.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 2
 * </p>
 *
 * @author GongQiang
 * @since 2022-01-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("Vstock_use_apply")
public class VstockUseApply implements Serializable {

    private static final long serialVersionUID = 1L;

    private String spr;

    private BigDecimal je;

    private String kfmc;

    private Integer ruleid;

    private String bz;

    private String kclx;

    private Long bh;

    private String ggxh;

    private String thr;

    private String bm;

    private BigDecimal sl;

    private String xq;

    private Integer spmcbh;

    private String spmc;

    private String zcLsh;

    private Integer bs;

    private BigDecimal dj;

    private String sqsj;

    private Integer jlbh;

    private Integer splbbh;

    private String lldw;

    private String syfx;

    private String spsj;

    private Integer bmbh;

    private String splb;

    private String zt;

    private String cfdd;

    private String thbz;

    private String jl;

    private String sqr;


}
