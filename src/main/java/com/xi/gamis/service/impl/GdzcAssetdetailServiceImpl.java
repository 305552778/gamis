package com.xi.gamis.service.impl;

import com.xi.gamis.dto.UserInfo;
import com.xi.gamis.entity.GdzcAssetdetail;
import com.xi.gamis.mapper.GdzcAssetdetailMapper;
import com.xi.gamis.service.IGdzcAssetdetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author GongQiang
 * @since 2021-09-17
 */
@Service
public class GdzcAssetdetailServiceImpl extends ServiceImpl<GdzcAssetdetailMapper, GdzcAssetdetail> implements IGdzcAssetdetailService {

    @Autowired
    GdzcAssetdetailMapper gdzcAssetdetailMapper;
    @Override
    public List<String> GetStorageLocationByDepartment(int dpID) {
        return gdzcAssetdetailMapper.GetStorageLocationByDepartment(dpID);
    }
}
