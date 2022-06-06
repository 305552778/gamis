package com.xi.gamis.mapper;

import com.xi.gamis.entity.V固定资产调拨申请;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author GongQiang
 * @since 2021-11-20
 */
public interface V固定资产调拨申请Mapper extends BaseMapper<V固定资产调拨申请> {
    @Select("exec gdzc_db_sp #{bh},#{current_bs},#{to_bs},#{spr}")
    public void Approve(@Param("bh") Integer bh, @Param("current_bs")Integer current_bs, @Param("to_bs")Integer to_bs, @Param("spr")String spr);
}
