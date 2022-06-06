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
public class SysOplog implements Serializable {

    private static final long serialVersionUID = 1L;

    private String method;

    private String optype;

    private String createTime;

    private String rspParam;

    private String operIp;

    private String reqParam;

    private String userid;

    private String module;

    private String uri;

    private String desciption;

    private String username;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;


}
