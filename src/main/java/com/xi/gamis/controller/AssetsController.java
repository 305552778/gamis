package com.xi.gamis.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xi.gamis.dto.CommonResult;
import com.xi.gamis.dto.Department;
import com.xi.gamis.dto.UserInfo;
import com.xi.gamis.entity.V固定资产调拨申请;
import com.xi.gamis.service.impl.GdzcAssetdetailServiceImpl;
import com.xi.gamis.service.impl.SysUsersServiceImpl;
import com.xi.gamis.service.impl.V固定资产调拨申请ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/assets")
public class AssetsController {
    @Autowired
    GdzcAssetdetailServiceImpl sv;
    @Autowired
    V固定资产调拨申请ServiceImpl svAssetsTransfer;
    @Autowired
    SysUsersServiceImpl srvUser;
    @RequestMapping("/location/my")
    public List<Map<String,Object>> GetStorageLocationByDepartment(HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("user");
        if(user==null)
        {
            return null;
        }
        List<String> r=sv.GetStorageLocationByDepartment(user.getDepartment().getDepartmentID());
        ArrayList<Map<String,Object>> p=new ArrayList<Map<String,Object>>();
        for (String item:r)
        {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("label",item);
            map.put("value",item);
            p.add(map);
        }
        return p;
    }
    @RequestMapping("/transfer/approval")
    public CommonResult Approval(HttpServletRequest request, int id, int result, String remark,int current)
    {
        HttpSession session = request.getSession();
        UserInfo user = (UserInfo) session.getAttribute("user");
        return svAssetsTransfer.Approve(id,current,result,user.getUserName());
    }
    @RequestMapping("/transfer/my")
    public CommonResult<Map<String,List<V固定资产调拨申请>>> GetAssetsTransferView(HttpSession session,long r)
    {
        CommonResult cr=new CommonResult();
        UserInfo user = (UserInfo) session.getAttribute("user");
        if(user==null)
        {
            cr.setCode(0);
            cr.setMsg("请登陆");
            cr.setData(null);
            return  cr;
        }
        if(!srvUser.checkPopdeom(user.getUserID(),341))
        {
            cr.setCode(0);
            cr.setMsg("未开通权限");
            cr.setData(null);
            return  cr;
        }
        List<Integer> dpList=new ArrayList<Integer>();
        for (Department item:user.getPopedomOfApproval()
                ) {
            dpList.add(item.getDepartmentID());
        }
        HashMap<String,List<V固定资产调拨申请>> x=new HashMap<String, List<V固定资产调拨申请>>();

        QueryWrapper<V固定资产调拨申请> qw1=new QueryWrapper<>();
        qw1.eq("dqjl",user.getCurrentInstitute().getInstituteID()).in("dqbm",dpList).eq("zt",0);

        QueryWrapper<V固定资产调拨申请> qw2=new QueryWrapper<>();
        qw2.eq("dfjl",user.getCurrentInstitute().getInstituteID()).in("dfbm",dpList).eq("zt",1);
        svAssetsTransfer.list(qw2);
        x.put("drsp",svAssetsTransfer.list(qw1));
        x.put("dcsp",svAssetsTransfer.list(qw2));
        cr.setCode(1);
        cr.setMsg("ok");
        cr.setData(x);
        return cr;
    }
    @RequestMapping("/location/test")
    public String GetStorageLocationByDepartment() {
        String data="b89m";
        String dp=data.replace("mb",",").replace("m","").replace("b","");
        return dp;
    }

}
