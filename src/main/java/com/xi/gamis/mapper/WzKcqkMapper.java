package com.xi.gamis.mapper;

import com.xi.gamis.entity.WzKcqk;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 库存情况 Mapper 接口
 * </p>
 *
 * @author GongQiang
 * @since 2021-08-28
 */
public interface WzKcqkMapper extends BaseMapper<WzKcqk> {
    @Select("exec wz_llsq_select_ByPrice #{jlbh},#{bmbh},#{splbbh},#{jm}")
    public List<Map<String,Object>> GetStock(@Param("jlbh") Integer jlbh, @Param("bmbh")Integer bmbh, @Param("splbbh")Integer splbbh, @Param("jm")String name);
    @Select("exec StockApply #{jlbh},#{bmbh},#{spmcbh},#{kclxbh},#{kfbh},#{sl},#{bz},#{ygxm},#{lldw},#{dj},#{cfdd},#{ggxh},#{xq}")
    public void StockApply(Integer jlbh, Integer bmbh,Integer spmcbh,Integer kclxbh,Integer kfbh,Double sl,String bz,String ygxm,String lldw,Double dj,String cfdd,String ggxh,String xq );

}
