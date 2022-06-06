package com.xi.gamis.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserInfo implements Serializable {
    private int userID;
    private String userName;
    //当前单位
    private Institute currentInstitute;
    //可登录单位
    private List<Institute> allowedInstitute;
    //所在部门
    private Department department;
    //领料审批权限（可审批部门）
    private List<Department> popedomOfApproval;
    private String approvalPopedom;
    private String DDUserid;
    private String password;

    public String getDDUserid() {
        return DDUserid;
    }

    public void setDDUserid(String DDUserid) {
        this.DDUserid = DDUserid;
    }

    public String getApprovalPopedom() {
        return approvalPopedom;
    }

    public void setApprovalPopedom(String approvalPopedom) {
        this.approvalPopedom = approvalPopedom;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Institute getCurrentInstitute() {
        return currentInstitute;
    }

    public void setCurrentInstitute(Institute currentInstitute) {
        this.currentInstitute = currentInstitute;
    }

    public List<Institute> getAllowedInstitute() {
        return allowedInstitute;
    }

    public void setAllowedInstitute(List<Institute> allowedInstitute) {
        this.allowedInstitute = allowedInstitute;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Department> getPopedomOfApproval() {
        return popedomOfApproval;
    }

    public void setPopedomOfApproval(List<Department> popedomOfApproval) {
        this.popedomOfApproval = popedomOfApproval;
    }

}
