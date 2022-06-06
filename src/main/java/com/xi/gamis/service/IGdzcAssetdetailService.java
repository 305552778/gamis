package com.xi.gamis.service;

import com.xi.gamis.entity.GdzcAssetdetail;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author GongQiang
 * @since 2021-09-17
 */
public interface IGdzcAssetdetailService extends IService<GdzcAssetdetail> {
List<String> GetStorageLocationByDepartment(int dpID);
}
