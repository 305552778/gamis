package com.xi.gamis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xi.gamis.entity.VstockUseApply;
import com.xi.gamis.entity.WzLlsq;
import com.xi.gamis.mapper.VstockUseApplyMapper;
import com.xi.gamis.mapper.WzLlsqMapper;
import com.xi.gamis.service.IWzLlsqService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author GongQiang
 * @since 2021-07-22
 */
@Service
public class WzLlsqServiceImpl extends ServiceImpl<WzLlsqMapper, WzLlsq> implements IWzLlsqService {
    @Autowired
    WzLlsqMapper wzLlsqMapper;
    @Autowired
    VstockUseApplyMapper vstockUseApplyMapper;
    @Override
    public List<Map<String, Object>> GetApplicationByDepartmentId(int insID, int dpID, int state) {
        return wzLlsqMapper.GetApplicationByDepartmentId(insID,dpID,state);
    }
    public void ApprovalApplication(int id,int newState,int result,String userName,String remark)
    {
        wzLlsqMapper.Approval( id, newState, result, userName, remark);
    }
    public void UpdateStatus(int insID,int dpID,int oldStatus,int newStatus,String userName,int userID,String lsh)
    {
        wzLlsqMapper.UpdateStatus( insID, dpID, oldStatus, newStatus, userName, userID,lsh);
    }
    public List<VstockUseApply> GetStockUseApply(int qtype, int insID, int dpID, String userName) {
        QueryWrapper<VstockUseApply> qw=new QueryWrapper<>();
        if(qtype==0)
        {
            //返回未提交
            qw.eq("bs",0).eq("bmbh",dpID).eq("jlbh",insID).eq("sqr",userName);
        }
        if(qtype==1)
        {
            //返回等待部门主管审批
            qw.eq("bs",1).eq("bmbh",dpID).eq("jlbh",insID);
        }
        if(qtype==3)
        {
            //返回未下账
            qw.gt("bs",0).eq("bmbh",dpID).eq("jlbh",insID);
        }
        return vstockUseApplyMapper.selectList(qw);
    }
}
