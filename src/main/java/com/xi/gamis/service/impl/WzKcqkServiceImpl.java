package com.xi.gamis.service.impl;
import com.xi.gamis.dto.CommonResult;
import com.xi.gamis.entity.WzKcqk;
import com.xi.gamis.mapper.WzKcqkMapper;
import com.xi.gamis.service.IWzKcqkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 库存情况 服务实现类
 * </p>
 *
 * @author GongQiang
 * @since 2021-08-28
 */
@Service
public class WzKcqkServiceImpl extends ServiceImpl<WzKcqkMapper, WzKcqk> implements IWzKcqkService {
     @Autowired
    private WzKcqkMapper wzKcqkMapper;
    public CommonResult GetStock(Integer jlbh, Integer bmbh, Integer splbbh,String name)
    {
        CommonResult cr=new CommonResult();
        List<Map<String,Object>> r= wzKcqkMapper.GetStock(jlbh,bmbh,splbbh,name);
        int x=r.size()>50?50:r.size();
        cr.setCode(1);
        cr.setMsg("ok");
        cr.setData(r.subList(0,x));
        return  cr;
    }
    public CommonResult StockApply(Integer jlbh, Integer bmbh,Integer spmcbh,Integer kclxbh,Integer kfbh,Double sl,String bz,String ygxm,String lldw,Double dj,String cfdd,String ggxh,String xq )
    {
        CommonResult cr=new CommonResult();
        try
        {
            wzKcqkMapper.StockApply(jlbh,  bmbh, spmcbh, kclxbh, kfbh, sl, bz, ygxm, lldw, dj, cfdd, ggxh, xq );
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
