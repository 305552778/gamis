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
@TableName("DD_Instance")
public class DdInstance implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("CreateTime")
    private String CreateTime;

    @TableField("ProcessID")
    private Integer ProcessID;

    @TableField("OriginatorUsername")
    private String OriginatorUsername;

    @TableField("Title")
    private String Title;

    @TableField("ApproverUserids")
    private String ApproverUserids;

    @TableId(value = "ID", type = IdType.AUTO)
    private String id;

    @TableField("Status")
    private String Status;

    @TableField("InsStatus")
    private String InsStatus;

    @TableField("InsJson")
    private String InsJson;

    @TableField("OriginatorDeptName")
    private String OriginatorDeptName;

    @TableField("AttachedProcessInstanceIds")
    private String AttachedProcessInstanceIds;

    @TableField("FinishTime")
    private String FinishTime;

    @TableField("InsImportTime")
    private String InsImportTime;

    @TableField("OriginatorUserid")
    private String OriginatorUserid;

    @TableField("BusinessId")
    private String BusinessId;

    @TableField("OriginatorDeptID")
    private String OriginatorDeptID;

    @TableField("Result")
    private String Result;


}
