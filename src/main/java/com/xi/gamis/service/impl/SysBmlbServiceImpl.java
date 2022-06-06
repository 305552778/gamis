package com.xi.gamis.service.impl;

import com.xi.gamis.entity.SysBmlb;
import com.xi.gamis.entity.SysUsers;
import com.xi.gamis.mapper.SysBmlbMapper;
import com.xi.gamis.mapper.SysUsersMapper;
import com.xi.gamis.service.ISysBmlbService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author GongQiang
 * @since 2021-07-22
 */
@Service
public class SysBmlbServiceImpl extends ServiceImpl<SysBmlbMapper, SysBmlb> implements ISysBmlbService {
    @Autowired
    SysBmlbMapper dpMapper;
    @Autowired
    SysUsersMapper usersMapper;
    public SysUsers GetLeader(int dpid)
    {
        SysBmlb dp= dpMapper.selectById(dpid);
        //return usersMapper.selectById(dp.getLeader());
        return null;
    }
}
