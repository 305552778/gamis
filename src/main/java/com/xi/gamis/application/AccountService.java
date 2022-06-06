package com.xi.gamis.application;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.xi.gamis.dto.Department;
import com.xi.gamis.dto.Institute;
import com.xi.gamis.dto.UserInfo;
import com.xi.gamis.entity.SysBmlb;
import com.xi.gamis.entity.SysJllb;
import com.xi.gamis.entity.SysUsers;
import com.xi.gamis.service.impl.SysBmlbServiceImpl;
import com.xi.gamis.service.impl.SysJllbServiceImpl;
import com.xi.gamis.service.impl.SysUsersServiceImpl;
import com.xi.gamis.service.impl.VUserAndPopdeomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class AccountService {
    @Autowired
    SysUsersServiceImpl usersService;
    @Autowired
    SysJllbServiceImpl sysJllbService;
    @Autowired
    SysBmlbServiceImpl sysBmlbService;
    @Autowired
    VUserAndPopdeomServiceImpl vUserAndPopdeomService;



    public UserInfo login(String userName,String pwd,String ddcode)
    {
        UserInfo u_dto=new UserInfo();
        QueryWrapper<SysUsers>qw=new QueryWrapper<>();
        qw.eq("yhm",userName).eq("mm", pwd.toUpperCase());
        SysUsers u=usersService.getOne(qw);
         if(u!=null)
        {
            //首次验证通过高新钉钉客户码
            //////////////////////////////////////////
            UpdateWrapper<SysUsers> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("bh",u.getBh()).set("ddcode", ddcode);
            usersService.update(updateWrapper);
            //返回可登陆单位
            List<Institute> ins=new ArrayList<Institute>();
            List<SysJllb> jl=sysJllbService.listByIds(Arrays.asList(u.getKyjl().split(",")));
            for (SysJllb item:jl )
            {
                ins.add(new Institute(item.getJlbh(),item.getJl()));
            }
            u_dto.setUserName(u.getXm());
            u_dto.setUserID(u.getBh());
            u_dto.setAllowedInstitute(ins);
            u_dto.setDDUserid(u.getDdcode());
            SysBmlb dp=usersService.getDepartmentOfUser(u.getBh());
            u_dto.setDepartment(new Department(dp.getBmbh(),dp.getBm()));
        }
        return u_dto;
    }
    public UserInfo auth(String DDCode)
    {
        QueryWrapper<SysUsers>qw=new QueryWrapper<>();
        qw.eq("ddcode",DDCode);
        SysUsers u=usersService.getOne(qw);
        UserInfo u_dto = new UserInfo();
        if(u!=null) {
            List<Institute> ins = new ArrayList<>();
            List<SysJllb> jl = sysJllbService.listByIds(Arrays.asList(u.getKyjl().split(",")));
            for (SysJllb item : jl) {
                ins.add(new Institute(item.getJlbh(), item.getJl()));
            }
            u_dto.setUserName(u.getXm());
            u_dto.setUserID(u.getBh());
            u_dto.setAllowedInstitute(ins);
            u_dto.setDDUserid(u.getDdcode());
            SysBmlb dp=usersService.getDepartmentOfUser(u.getBh());
            u_dto.setDepartment(new Department(dp.getBmbh(),dp.getBm()));
        }
        //else
        //{
            //UpdateWrapper<SysUsers> updateWrapper = new UpdateWrapper<>();
            //updateWrapper.eq("bh",662).set("ddcode", DDCode);
            //usersService.update(updateWrapper);
        //}
        return u_dto;
    }
    public UserInfo Test()
    {
        UserInfo u_dto = new UserInfo();
        /*QueryWrapper<SysUsers>qw=new QueryWrapper<>();
        qw.eq("yhm","gq").eq("mm", "3E987AE1693B44CB2DEA65805BA5F95C");
        SysUsers u=usersService.getOne(qw);
        if(u!=null) {
            List<Institute> ins = new ArrayList<>();
            List<SysJllb> jl = sysJllbService.listByIds(Arrays.asList(u.getKyjl().split(",")));
            for (SysJllb item : jl) {
                ins.add(new Institute(item.getJlbh(), item.getJl()));
            }
            u_dto.setUserName(u.getXm());
            u_dto.setUserID(u.getBh());
            u_dto.setAllowedInstitute(ins);
        }*/
        return u_dto;

    }

}
