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
 * @since 2022-01-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SYS_bmlb")
public class SysBmlb implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 部门
     */
    private String bm;

    /**
     * 是否启用部门库存
     */
    private Integer bmkc;

    private String xq;

    private Integer bmlxbh;

    private String tpdz;

    private Integer pid;

    private String bz;

    @TableId(value = "bmbh", type = IdType.AUTO)
    private Integer bmbh;

    private String gxsj;

    private Double sort;


}
