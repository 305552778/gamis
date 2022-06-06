package com.xi.gamis.entity;

import java.math.BigDecimal;
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
public class WbLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 维保单位
     */
    private String wbdw;

    /**
     * 维保类型
     */
    private String wblx;

    /**
     * 设备名称
     */
    private String sbmc;

    /**
     * 维保日期
     */
    private String wbrq;

    private String timestamp;

    private Integer bmbh;

    private Integer wbid;

    private Integer jlbh;

    private String img;

    private String zcbh;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer writerId;

    private BigDecimal cost;

    private String title;

    private String wbssr;

    private String wbbh;

    private String writer;

    private String costDiscription;

    private String body;


}
