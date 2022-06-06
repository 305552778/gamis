package com.xi.gamis.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("WZ_llsq")
public class WzLlsq implements Serializable {

    private static final long serialVersionUID = 1L;

    private String thr;

    private String ggxh;

    private String txm;

    private Integer bmbh;

    private String xq;

    private String cfdd;

    private String xmmc;

    private Integer kfbh;

    private String zcLsh;

    private Integer bs;

    private String lldw;

    private Integer classid;

    private Long bh;

    private String sqsj;

    private Integer spmcbh;

    private BigDecimal sl2;

    private String spsj;

    private Integer kcbh;

    private Integer ruleid;

    private String sqr;

    private String thbz;

    private String syfx;

    private Integer jlbh;

    private String spr;

    private Integer kclxbh;

    private String xmbh;

    private BigDecimal dj;

    private String bz;

    private BigDecimal sl;

    private Integer sybm;


}
