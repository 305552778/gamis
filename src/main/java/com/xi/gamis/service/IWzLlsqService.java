package com.xi.gamis.service;

import com.xi.gamis.entity.WzLlsq;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author GongQiang
 * @since 2021-07-22
 */
public interface IWzLlsqService extends IService<WzLlsq> {
    List<Map<String,Object>> GetApplicationByDepartmentId(int InsID, int dpID, int state);
}
