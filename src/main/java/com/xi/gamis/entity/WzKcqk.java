package com.xi.gamis.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 库存情况
 * </p>
 *
 * @author GongQiang
 * @since 2022-01-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("WZ_kcqk")
public class WzKcqk implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 酒楼编号
     */
    private Integer jlbh;

    /**
     * 商品单价
     */
    private BigDecimal dj;

    /**
     * 库存数量
     */
    private BigDecimal kcsl;

    /**
     * 库存类型
     */
    private Integer kclx;

    /**
     * 保质期到期时间
     */
    private String dqsj;

    /**
     * 积压标志
     */
    private Integer jybz;

    @TableId(value = "bh", type = IdType.AUTO)
    private Long bh;

    private String img;

    private Integer kfbh;

    private Integer gysbh;

    private String dw2;

    private Integer sybm;

    private Integer rkbh;

    private String scs;

    private Integer bzq;

    private String gbm;

    private String ggxh;

    private String bz;

    private String xq;

    private String gxsj;

    private String gmrq;

    private Integer spmcbh;

    private BigDecimal kcsl2;

    private String syfx;

    private String fjbz;


}
