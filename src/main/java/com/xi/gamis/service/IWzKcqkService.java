package com.xi.gamis.service;

import com.xi.gamis.dto.CommonResult;
import com.xi.gamis.entity.WzKcqk;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 库存情况 服务类
 * </p>
 *
 * @author GongQiang
 * @since 2021-08-28
 */
public interface IWzKcqkService extends IService<WzKcqk> {
    public CommonResult GetStock(Integer jlbh, Integer bmbh, Integer splbbh, String name);
    public CommonResult StockApply(Integer jlbh, Integer bmbh,Integer spmcbh,Integer kclxbh,Integer kfbh,Double sl,String bz,String ygxm,String lldw,Double dj,String cfdd,String ggxh,String xq );
}
