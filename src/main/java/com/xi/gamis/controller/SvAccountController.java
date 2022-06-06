package com.xi.gamis.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONWriter;
import com.xi.gamis.application.AccountService;
import com.xi.gamis.dto.CommonResult;
import com.xi.gamis.dto.Institute;
import com.xi.gamis.dto.UserInfo;
import com.xi.gamis.entity.SysUsers;
import com.xi.gamis.infrastructure.DDApi;
import com.xi.gamis.infrastructure.annotation.OpLog;
import com.xi.gamis.service.impl.WzQxszServiceImpl;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/account")
public class SvAccountController {
    @Autowired
    AccountService accountService;
    @Autowired
    WzQxszServiceImpl qxszService;
    //钉钉客户码验证登陆
    @RequestMapping("/auth")
    public CommonResult<List<Institute>> Auth(HttpServletRequest req,String requestAuthCode)
    {
        CommonResult<List<Institute>> cr=new CommonResult<>();
        try{

            //UserInfo u= accountService.auth(requestAuthCode);
            String dduserID= DDApi.GetDDUserId(requestAuthCode);
            UserInfo u= accountService.auth(dduserID);
            //////////////////////////////////////////////////
            //////////////////////////////////////////////////
            if(u.getUserID()>0)
            {
                if(u.getAllowedInstitute()!=null)
                {
                    cr.setCode(1);
                    cr.setMsg("ok");
                    cr.setData(u.getAllowedInstitute());
                    HttpSession session= req.getSession();
                    session.setAttribute("user",u);
                }
                else
                {
                    cr.setCode(2);
                    cr.setMsg("未开通权限请联系管理员");
                    cr.setData(null);
                }
            }
            else
            {
                cr.setCode(0);
                cr.setMsg("登陆验证失败！请联系管理员");
                cr.setData(null);
            }
        }
        catch(Exception e){
            cr.setCode(0);
            cr.setMsg(e.getCause().getMessage());
        }

        return cr;
    }
    /*@RequestMapping("/test")
    public void test()
    {
        String dir = "C:\\bbbbbbb.txt";
        File file = new File(dir);
        //如果文件不存在，创建文件
        if (!file.exists()) {
            try {
                file.createNewFile();
                //创建FileWriter对象
                FileWriter writer = new FileWriter(file);
                //向文件中写入内容
                writer.write("测试创建文件");
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/
    @OpLog(MOD = "account",OPTYPE = "用户登录",DESC = "使用测试账号登录")
    @RequestMapping("/logingq")
    public CommonResult<UserInfo> logingq(HttpServletRequest req)
    {
        UserInfo u= accountService.login("gq","3E987AE1693B44CB2DEA65805BA5F95C","09086663341290976");
        u.setCurrentInstitute(new Institute(1,"银杏学院"));
        u.setPopedomOfApproval(qxszService.GetApprovePopedom(u.getUserID(),u.getCurrentInstitute().getInstituteID()));
        HttpSession session= req.getSession();
        session.setAttribute("user",u);
        CommonResult<UserInfo> cr=new CommonResult();
        cr.setMsg("ok");
        cr.setCode(1);
        cr.setData(u);
        return cr;

    }
    //首次登陆验证
    @RequestMapping("/login")
    public CommonResult<List<Institute>> Login(HttpServletRequest req,String userName,String pwd,String requestAuthCode) {
        ///////////////////////////////////////
        CommonResult<List<Institute>> cr=new CommonResult<>();
        String dduserID= DDApi.GetDDUserId(requestAuthCode);
        UserInfo u= accountService.login(userName,pwd,dduserID);
        //UserInfo u= accountService.login(userName,pwd,requestAuthCode);
        if(u.getUserID()>0)
        {
            if(u.getAllowedInstitute()!=null)
            {
                cr.setCode(1);
                cr.setMsg("ok");
                cr.setData(u.getAllowedInstitute());
                HttpSession session= req.getSession();
                session.setAttribute("user",u);
            }
            else
            {
                cr.setCode(2);
                cr.setMsg("未开通权限请联系管理员");
                cr.setData(null);
            }
        }
        else
        {
            cr.setCode(0);
            cr.setMsg("登陆失败！请核查用户名或密码");
            cr.setData(null);
            return cr;
        }

       /*catch(Exception e){
           cr.setCode(0);
           cr.setMsg(e.getMessage());
       }*/
        return cr;
    }
    //设置登陆单位
    @RequestMapping("/setins")
    public CommonResult Setins(HttpServletRequest request, Integer insID, HttpServletResponse response)
    {
        CommonResult cr=new CommonResult();
        HttpSession session = request.getSession();
        UserInfo user = (UserInfo) session.getAttribute("user");
        if(user!=null)
        {
            for (Institute item:user.getAllowedInstitute())
            {
                if(item.getInstituteID()==insID)
                {
                    user.setCurrentInstitute(new Institute(insID,item.getInstituteName()));
                }
            }
            if(user.getCurrentInstitute()!=null)
            {
               user.setPopedomOfApproval(qxszService.GetApprovePopedom(user.getUserID(),user.getCurrentInstitute().getInstituteID()));
               session.setAttribute("user",user);
                cr.setCode(1);
                cr.setMsg("ok");

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd日HH时mm分ss秒");
                //System.out.println(dtf.format(LocalDateTime.now()));
                //System.out.println(3600*24*30);
                Cookie c=new Cookie("lasttime", dtf.format(LocalDateTime.now()));
                c.setMaxAge(3600*24*30);
                c.setPath("/");
                response.addCookie(c);
            }
            else
            {
                cr.setCode(-1);
                cr.setMsg("操作失败请联系管理员");
            }

        }
        else
        {
            cr.setCode(0);
            cr.setMsg("请先登陆");
        }
        return  cr;
    }
    //设置登陆单位
    @RequestMapping("/userinfo")
    public CommonResult GetUserInfo(HttpServletRequest request)
    {
        CommonResult cr=new CommonResult();
        HttpSession session = request.getSession();
        UserInfo user = (UserInfo) session.getAttribute("user");
        Map<String,Object> item=new HashMap<>();
        if(user!=null)
        {
            item.put("name",user.getUserName());
            item.put("ins",user.getCurrentInstitute().getInstituteName());
            item.put("dpm",user.getDepartment().getDepartmentName());
            item.put("popDpm",user.getPopedomOfApproval().toString());
            JSONObject ddu=DDApi.GetUserInfo(user.getDDUserid());
            item.put("avatar",  ddu.getString("avatar"));
            item.put("phone",  ddu.getString("mobile"));
            cr.setCode(1);
            cr.setData(item);
        }
        else
        {
            cr.setCode(0);
            cr.setMsg("请登录");
        }
        return cr;
    }
}
