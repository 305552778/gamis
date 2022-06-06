package com.xi.gamis.service.impl;

import com.xi.gamis.dto.CommonResult;
import com.xi.gamis.entity.V固定资产调拨申请;
import com.xi.gamis.mapper.V固定资产调拨申请Mapper;
import com.xi.gamis.service.IV固定资产调拨申请Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author GongQiang
 * @since 2021-11-20
 */
@Service
public class V固定资产调拨申请ServiceImpl extends ServiceImpl<V固定资产调拨申请Mapper, V固定资产调拨申请> implements IV固定资产调拨申请Service {
    @Autowired
   public V固定资产调拨申请Mapper dbMapper;
    public CommonResult Approve(Integer bh, Integer current_bs, Integer to_bs,String spr)
    {
        CommonResult cr=new CommonResult();
        try
        {
            dbMapper.Approve( bh,current_bs,to_bs,spr);
            cr.setCode(1);
            cr.setMsg("ok");
            cr.setData("");
        }
        catch(Exception e)
        {
            cr.setCode(0);
            cr.setMsg(e.getMessage());
            cr.setData("");
        }
        return  cr;
    }


}
