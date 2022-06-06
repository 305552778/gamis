package com.xi.gamis.entity;

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
@TableName("SYS_jllb")
public class SysJllb implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("wz_Xjxc")
    private Integer wzXjxc;

    private String jx;

    private String bz;

    private Integer cggysbh;

    @TableId(value = "jlbh", type = IdType.AUTO)
    private Integer jlbh;

    private String dz;

    private String jl;

    private String lxdh;

    private Integer zggysbh;


}
