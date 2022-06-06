package com.xi.gamis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 2
 * </p>
 *
 * @author GongQiang
 * @since 2021-11-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("VUserAndPopdeom")
public class VUserAndPopdeom implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer zwbh;

    private String xdfhqx;

    private Integer jlbh;

    private String llcxqx;

    private String xm;

    private String jglbqx;

    private Integer ygbh;

    private String zt;

    private Integer yhdj;

    private Boolean hhflTh;

    private Integer bh;

    private String kfqx;

    private Integer tsysyqx;

    private String hhqx;

    private String hhspfl;

    private String pmglqx;

    private String mm;

    private Integer ssjl;

    private Boolean hhflXh;

    private String ddcode;

    private String jl;

    @TableField("hhfl_Print")
    private Boolean hhflPrint;

    private String xdqx;

    private String yhm;

    private String bm;

    private String qx;

    private String xhcxqx;

    private String xdspqx;

    private String kyjl;

    private Integer bmbh;

    private Boolean hhflGh;

    private String llspqx;


}
