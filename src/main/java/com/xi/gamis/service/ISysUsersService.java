package com.xi.gamis.service;

import com.xi.gamis.entity.SysUsers;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author GongQiang
 * @since 2021-07-22
 */
public interface ISysUsersService extends IService<SysUsers> {
    public List<Map<String,Object>> getUserVO(String loginName,String pwd,int comapnyId);
}
