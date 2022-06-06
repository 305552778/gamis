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
 * @since 2021-08-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("WZ_gys")
public class WzGys implements Serializable {

    private static final long serialVersionUID = 1L;

    private String gysqm;

    private String htsj;

    private String khyh;

    private String gxsj;

    private String sdfs;

    @TableId(value = "gysbh", type = IdType.AUTO)
    private Integer gysbh;

    private String yhzh;

    private Integer djdw;

    private String lxr;

    private String ghjl;

    private String lxdh;

    private String dxhm;

    private String email;

    private String gys;

    private String czr;

    private String pyjm;

    private String czhm;

    private Boolean locked;


}
