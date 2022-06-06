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
@TableName("Rsgz_sgzl")
public class RsgzSgzl implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer zwbh;

    @TableId(value = "ygbh", type = IdType.AUTO)
    private Integer ygbh;

    private Integer jlbh;

    private Integer bmbh;


}
