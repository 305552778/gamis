package com.xi.gamis.dto;

import com.alibaba.fastjson.annotation.JSONField;

public class Department {
    @JSONField(name="value")
    private Integer departmentID;
    @JSONField(name="label")
    private String departmentName;

    public Department(Integer departmentID, String departmentName) {
        this.departmentID = departmentID;
        this.departmentName = departmentName;
    }

    public Integer getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(Integer departmentID) {
        this.departmentID = departmentID;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
