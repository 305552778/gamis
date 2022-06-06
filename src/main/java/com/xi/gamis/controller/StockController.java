package com.xi.gamis.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xi.gamis.application.StockService;
import com.xi.gamis.dto.CommonResult;
import com.xi.gamis.dto.Department;
import com.xi.gamis.dto.UserInfo;
import com.xi.gamis.entity.SysUsers;
import com.xi.gamis.entity.WzQxsz;
import com.xi.gamis.infrastructure.AESUtil;
import com.xi.gamis.infrastructure.DDApi;
import com.xi.gamis.service.impl.*;
import lombok.var;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/stock")
public class StockController {
    @Autowired
    StockService srvStock;
    @Autowired
    WzKcqkServiceImpl srvKcqk;
    @Autowired
    SysUsersServiceImpl srvUser;
    @Autowired
    SysBmlbServiceImpl srvDepartment;
    @Autowired
    WzLlsqServiceImpl srvLlsq;

    @RequestMapping("/search")
    public CommonResult GetStock(HttpServletRequest request, Integer splbbh, String name)
    {
        CommonResult cr=new CommonResult();
        HttpSession session = request.getSession();
        UserInfo user = (UserInfo) session.getAttribute("user");
        if(user.getCurrentInstitute().getInstituteID()==22)
        {
            cr.setCode(0);
            cr.setMsg("当前单位不支持该操作请登陆电脑端");
            cr.setData("");
            return  cr;
        }
        if(user!=null)
        {
            if(!srvUser.checkPopdeom(user.getUserID(),90))
            {
                cr.setCode(0);
                cr.setMsg("未开通操作权限请联系管理员");
                cr.setData("");
                return  cr;
            }
            return  srvKcqk.GetStock(user.getCurrentInstitute().getInstituteID(),user.getDepartment().getDepartmentID(),splbbh,name);
        }
        else
        {
            return new CommonResult(0,"","登陆超时请重新登陆");
        }

    }

    @RequestMapping("/test")
    public CommonResult test()
    {
        CommonResult cr=new CommonResult();
        List<Map<String, Object>> data=srvStock.GetApplications(1,1,1);
        data.add(new HashMap<>());
        HashMap<String, Object> dp=new HashMap<>();
        dp.put("dps","[{id:1,text:'sss'}]");
        data.add(dp) ;
        cr.setData(data);
        return cr;
       /* String x="213FBA8A06A395DA644B809CFCB3856F342FF6A95D65026D844BB5AB1AEBD5C06871958C055DED24234C1BD9DB7DBA75E95326DA1F0B152D9A7B1CED1FC9E87C1A089B73E4F680AB6887E886588FB34179A105592E402DD4E0BA9995FCE77C54142588EDAA3DBF407DAE57B765CD7179345B0C5ABD06CE01723ADD85E26E1143C74A4F47A09C40366F4CC274B92FBC77A2DE80ABE761B5852014B4C487F43C0268D8DC9BA0FB748A1ABE31F5840B7ABD7A138D095B75BF65257D8CBFA51A192B711ECBAF9D06C2DF374DBC02C8ABB347B5BAB68FCCF264BB7B0DE1B05F6729837B75424D6E15F26054F0F07FE16EE0489D10B79BB828A57AF5F83A31A7A440FA3AA8AD38920145DCA4A3C6D09A92F2FD1295F23C9DA85ADB9200525AA6D21DF7B93934DBB0467C1ED8670BA1BE39CB8E08093D0DD0AB8807FF5A18DE5DC6F3E2A2C0FA1C8047B5A4D004AA882A40BBD1";
        int code=1;
        String c=new String();
        String msg=new String();
        try
        {
            c=new String(AESUtil.Decrypt(x), "utf-8");
            //jsonObj = JSONObject.parseObject(c);//转JSONObject对象
        }
        catch (Exception e) {
            code=0;
            msg="数据格式错误";
        }
        JSONObject jsonObj=JSONObject.parseObject(c);
        jsonObj.getInteger("kcsl");
        CommonResult cr=new CommonResult();
        cr.setCode(code);
        cr.setData(JSONObject.parseObject(c));
        cr.setMsg(msg);
        return  cr;*/
    }
    @RequestMapping("/approval")
    public CommonResult Approval(HttpServletRequest request,int id,int result,String remark)
    {
        HttpSession session = request.getSession();
        UserInfo user = (UserInfo) session.getAttribute("user");
        CommonResult cr=new CommonResult();
        try {
            int newState=-1;
            srvStock.ApprovalApplicationAndUpdateDDTaskStatus( id, newState, result, user.getUserName(), remark,user.getDDUserid());
            cr.setCode(1);
        }
        catch (Exception e)
        {
            cr.setCode(0);
        }
        return cr;
    }
    @RequestMapping("/applications")
    public CommonResult GetApplications(HttpServletRequest request,int qtype,int dpID)
    {
        HttpSession session = request.getSession();
        UserInfo user = (UserInfo) session.getAttribute("user");
        CommonResult cr=new CommonResult();
        if(user==null)
        {
            cr.setCode(0);
            cr.setMsg("请登录");
            cr.setData("");
            return  cr;
        }
        if(qtype==1&&!srvUser.checkPopdeom(user.getUserID(),91))
        {
            cr.setCode(0);
            cr.setMsg("未开通操作权限请联系管理员");
            cr.setData("");
            return  cr;
        }
        dpID=dpID==0?user.getDepartment().getDepartmentID():dpID;
        //List<Map<String, Object>> data=srvStock.GetApplications(user.getCurrentInstitute().getInstituteID(),dpID,status);
        cr.setCode(1);
        cr.setOptionalData(JSONObject.toJSON(user.getPopedomOfApproval()));
        cr.setData(srvLlsq.GetStockUseApply(qtype,user.getCurrentInstitute().getInstituteID(),dpID,user.getUserName()));
        if(qtype==3)
        {
            cr.setOptionalData(user.getDepartment().getDepartmentName());
        }
        if(qtype==1)
        {
            cr.setOptionalData(JSONObject.toJSON(user.getPopedomOfApproval()));
        }

        return cr;
    }
    @RequestMapping("/submitapp")
    public CommonResult SubmitApplication(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserInfo user = (UserInfo) session.getAttribute("user");
        CommonResult cr=new CommonResult();
        if(user==null)
        {
            cr.setCode(0);
            cr.setMsg("请登录");
            cr.setData("");
            return  cr;
        }
        String lsh= UUID.randomUUID().toString();
        try {

            srvStock.UpdateApplicationStatus( user.getCurrentInstitute().getInstituteID(), user.getDepartment().getDepartmentID(), 0, 1, user.getUserName(),user.getUserID(),lsh);
            SysUsers leader= srvDepartment.GetLeader(user.getDepartment().getDepartmentID());
            if(leader!=null&&leader.getDdcode()!=null)
            {
                DDApi.CreateWorkRecoed(user.getDDUserid(),(new Date()).getTime(),"资产管理平台领用申请","http://test.gingkoc.edu.cn:8080/home.html","http://test.gingkoc.edu.cn:8080/home.html","领用申请",user.getUserName()+"的领用申请("+LocalDateTime.now().toLocalDate().toString()+")",user.getDDUserid(),"全域资产管理系统",lsh);
            }
            cr.setCode(1);
        }
        catch (Exception e)
        {
            cr.setMsg(e.getCause().getMessage());
            cr.setCode(0);
        }
        return cr;
    }
    @RequestMapping("/delitem")
    public CommonResult Delitem(HttpServletRequest request,int id)
    {
        HttpSession session = request.getSession();
        UserInfo user = (UserInfo) session.getAttribute("user");
        CommonResult cr=new CommonResult();
        if(user==null)
        {
            cr.setCode(0);
            cr.setMsg("请登录");
            cr.setData("");
            return  cr;
        }
        try {
            srvStock.DeleteItem(id);
            cr.setCode(1);
        }
        catch (Exception e)
        {
            cr.setMsg(e.getCause().getMessage());
            cr.setCode(0);
        }
        return cr;
    }

    /////待部门主管审批数量
    @RequestMapping("/count")
    public CommonResult GetUnfinishedApplyCount(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserInfo user = (UserInfo) session.getAttribute("user");
        CommonResult cr=new CommonResult();
        List<Integer> dpList=new ArrayList<Integer>();
        if(srvUser.checkPopdeom(user.getUserID(),91))
        {
            for (Department item:user.getPopedomOfApproval()
                 ) {
                dpList.add(item.getDepartmentID());
            }
            int r=srvStock.GetUnfinishedApp(dpList);
            cr.setCode(1);
            cr.setData(r);
        }
        else
        {
            cr.setCode(0);
            cr.setData(0);
        }
        return cr;
    }
    /////待部门主管审批数量
    @RequestMapping("/unsubmittedcount")
    public CommonResult GetUnsubmittedCount(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserInfo user = (UserInfo) session.getAttribute("user");
        CommonResult cr=new CommonResult();
        if(srvUser.checkPopdeom(user.getUserID(),90))
        {
            int r=srvStock.GetUnsubmittedApp(user.getUserName());
            cr.setCode(1);
            cr.setData(r);
        }
        else
        {
            cr.setCode(0);
            cr.setData(0);
        }
        return cr;
    }
    @RequestMapping("/apply")
    public CommonResult Apply(HttpServletRequest request, String n, String cfdd, double llsl, String bz)
    {
        HttpSession session = request.getSession();
        UserInfo user = (UserInfo) session.getAttribute("user");
        CommonResult cr=new CommonResult();
        String x=new String();
        JSONObject data=null;
        try
        {
             x=new String(AESUtil.Decrypt(n), "utf-8");
            data=JSONObject.parseObject(x);
        }
        catch (Exception e)
        {
            cr.setCode(0);
            cr.setData("");
            cr.setMsg("数据格式错误");
            return cr;
        }
        if(user!=null)
        {
            if(data!=null)
            {
                //{"ggxh":"","img":null,"kfmc":"宜宾校区","spmcbh":14320,"splbbh":2,"kcsl":10,"tpdz":null,"splb":"用品类","spmc":"方油隔","dj":30.5,"sybm":0,"yllsl":null,"xssl":null,"kcsl2":null,"kfbh":22,"dw":"把","kysl":10,"kclx":"正常","kclxbh":0,"bz":null,"pyjm":"FYG","jlbh":1,"xq":"","dw2":null}
                if(data.getInteger("sybm")>0&&data.getInteger("sybm")!=user.getDepartment().getDepartmentID())
                {
                    cr.setCode(0);
                    cr.setData("");
                    cr.setMsg("此库存商品限定使用");
                    return cr;
                }
                cr=srvKcqk.StockApply( user.getCurrentInstitute().getInstituteID(), user.getDepartment().getDepartmentID(),data.getInteger("spmcbh"),data.getInteger("kclxbh"),data.getInteger("kfbh"),llsl,bz,user.getUserName(),data.getString("dw"),data.getDouble("dj"),cfdd,data.getString("ggxh"),data.getString("xq") );
            }
            else
            {
                cr.setCode(0);
                cr.setData(x);
                cr.setMsg("数据格式错误");

            }
            return cr;
        }
        else
        {
            cr.setCode(0);
            cr.setData(x);
            cr.setMsg("获取登陆信息失败");
            return cr;
        }

    }
}
