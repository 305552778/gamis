package com.xi.gamis.entity;

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
public class WbPlan implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 维保对象/物资名称
     */
    private String spmc;

    /**
     * 维保频率
     */
    private String wbpl;

    /**
     * 维保实施人
     */
    private String wbssr;

    /**
     * 轮转周期
     */
    private Integer lzzq;

    /**
     * 截止日期
     */
    private String jzrq;

    /**
     * 关注人
     */
    private String gzr;

    private String syzs;

    private String fzbm;

    private String lb;

    private String wbjh;

    private String bz;

    private Integer bmbh;

    private String jzwmc;

    private String syqk;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String fjmc;

    private String xq;

    private String wbbh;

    private String fzr;

    private String trsyrq;

    private String zt;

    private String jzwlh;

    private String wbbz;

    private String czr;

    private String wbje;

    private Integer jlbh;


}
