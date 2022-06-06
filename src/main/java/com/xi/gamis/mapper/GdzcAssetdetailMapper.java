package com.xi.gamis.mapper;

import com.xi.gamis.entity.GdzcAssetdetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author GongQiang
 * @since 2021-09-17
 */
public interface GdzcAssetdetailMapper extends BaseMapper<GdzcAssetdetail> {
    @Select("select distinct cfdd as value from gdzc_AssetDetail where ssbm=#{dpID} order by cfdd")
    List<String> GetStorageLocationByDepartment(int dpID);
}
