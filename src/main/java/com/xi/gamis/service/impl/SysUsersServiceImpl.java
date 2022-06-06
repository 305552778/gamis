package com.xi.gamis.service.impl;

import com.xi.gamis.dto.Department;
import com.xi.gamis.entity.SysBmlb;
import com.xi.gamis.entity.SysUsers;
import com.xi.gamis.mapper.SysUsersMapper;
import com.xi.gamis.service.ISysUsersService;
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
public class SysUsersServiceImpl extends ServiceImpl<SysUsersMapper, SysUsers> implements ISysUsersService {
    @Autowired
    private  SysUsersMapper sysUsersMapper;
    public List<Map<String,Object>> getUserVO(String loginName,String pwd,int comapnyId)
    {
      return   sysUsersMapper.getUserVO(loginName,pwd);
    }
    public SysBmlb getDepartmentOfUser(int userID)
    {
        return   sysUsersMapper.getDepartmentOfUser(userID);
    }
    public boolean checkPopdeom(int userID,int popID){
        SysUsers u=sysUsersMapper.selectById(userID);
        return u.getQx().contains("s"+popID+"e");
    }

}
