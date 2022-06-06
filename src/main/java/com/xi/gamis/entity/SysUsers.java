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
@TableName("SYS_users")
public class SysUsers implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编号
     */
    @TableId(value = "bh", type = IdType.AUTO)
    private Integer bh;

    /**
     * 登录名
     */
    private String yhm;

    /**
     * 真实姓名
     */
    private String xm;

    /**
     * 密码（需要与Membership的同步）
     */
    private String mm;

    /**
     * 可用酒楼（用在两个地方 1 库房 2 权限认证的时候）
     */
    private String kyjl;

    /**
     * 用户等级（0级标识系统管理员）
     */
    private Integer yhdj;

    /**
     * 特殊页使用权限（主要针对有些功能只能给人开权限但自己又不能用，默认为不能用）
     */
    private Integer tsysyqx;

    /**
     * 系统权限字符串，用IPermission认证
     */
    private String qx;

    /**
     * 最后一次操作人
     */
    private String czr;

    /**
     * 更新时间（操作时间）
     */
    private String gxsj;

    private String zt;

    private String email;

    private String gzcxmm;

    private String ddcode;

    @TableField("openID")
    private String openID;


}
