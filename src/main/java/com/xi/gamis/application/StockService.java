package com.xi.gamis.application;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xi.gamis.dto.Department;
import com.xi.gamis.entity.SysUsers;
import com.xi.gamis.entity.VstockUseApply;
import com.xi.gamis.entity.WzKcqk;
import com.xi.gamis.entity.WzLlsq;
import com.xi.gamis.infrastructure.DDApi;
import com.xi.gamis.service.impl.VstockUseApplyServiceImpl;
import com.xi.gamis.service.impl.WzLlsqServiceImpl;
import com.xi.gamis.service.impl.WzQxszServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Component
public class StockService {
    @Autowired
    WzLlsqServiceImpl svcLlsq;
    @Autowired
    VstockUseApplyServiceImpl svcVstockUseApply;

    //未提交的申请数量
    public int GetUnsubmittedApp(String userName)
    {
        QueryWrapper<WzLlsq> qw=new QueryWrapper<>();
        qw.eq("sqr",userName).eq("bs",0);
        return svcLlsq.count(qw);
    }
    //待部门主管审批数量
    public int GetUnfinishedApp(List<Integer> dpIDs)
    {
        QueryWrapper<WzLlsq> qw=new QueryWrapper<>();
        qw.in("bmbh",dpIDs).eq("bs",1);
        return svcLlsq.count(qw);
    }
    public List<Map<String, Object>> GetApplications(int insID, int dpID,int state)
    {
       return svcLlsq.GetApplicationByDepartmentId(insID,dpID,state);
    }
    //移动端按单条编号审批并更新状态
    public void ApprovalApplicationAndUpdateDDTaskStatus(int id,int newState,int result,String userName,String remark,String ddUserID)
    {
        svcLlsq.ApprovalApplication( id, newState, result, userName, remark);
        ///////////////////////////////////////////////////////////
        //审批完成撤回钉钉待办任务
        QueryWrapper<WzLlsq> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("bh",id);
        WzLlsq current=svcLlsq.getOne(wrapper1);
        QueryWrapper<WzLlsq> wrapper2 = new QueryWrapper<>();
        wrapper2.eq("zc_lsh",current.getZcLsh());
        wrapper2.eq("bs",1);
        if(svcLlsq.list(wrapper2).isEmpty()&&current.getZcLsh()!=null)
        {
            DDApi.UpdateWorkRecordState(ddUserID,current.getZcLsh());
        }
        //////////////////////////////////////////////////////////
    }
    //按单位和部门批量更新状态,use case:未提交状态批量提交
    public void UpdateApplicationStatus(int insID,int dpID,int oldStatus,int newStatus,String userName,int userID,String lsh)
    {
        svcLlsq.UpdateStatus( insID, dpID, oldStatus, newStatus, userName, userID,lsh);
    }
    public boolean DeleteItem(int llbh)
    {
        QueryWrapper<WzLlsq> wrapper = new QueryWrapper<>();
        wrapper.eq("bh",llbh);
        return svcLlsq.remove(wrapper);
    }
}
