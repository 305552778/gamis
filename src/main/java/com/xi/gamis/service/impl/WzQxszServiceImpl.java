package com.xi.gamis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xi.gamis.dto.Department;
import com.xi.gamis.entity.SysBmlb;
import com.xi.gamis.entity.SysUsers;
import com.xi.gamis.entity.WzQxsz;
import com.xi.gamis.mapper.WzQxszMapper;
import com.xi.gamis.service.IWzQxszService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 权限设置 服务实现类
 * </p>
 *
 * @author GongQiang
 * @since 2021-09-13
 */
@Service
public class WzQxszServiceImpl extends ServiceImpl<WzQxszMapper, WzQxsz> implements IWzQxszService {
    @Autowired
    WzQxszMapper wzQxszMapper;
    @Autowired
    SysBmlbServiceImpl sysBmlbService;
public List<Department> GetApprovePopedom(int userID,int insID)
{
    List<Department> pop=new ArrayList<Department>();
    QueryWrapper<WzQxsz> qw=new QueryWrapper<>();
    qw.eq("jlbh",insID).eq("ygbh", userID);
    WzQxsz data=wzQxszMapper.selectOne(qw);
    if(data==null)
    {
        return  null;
    }
    String dps=data.getLlspqx().replace("mb",",").replace("m",",").replace("b","");
    //返回可登陆单位
    List<SysBmlb> dp=sysBmlbService.listByIds(Arrays.asList(dps.split(",")));
    for (SysBmlb item:dp )
    {
        pop.add(new Department(item.getBmbh(),item.getBm()));
    }
    return pop;
}
}
