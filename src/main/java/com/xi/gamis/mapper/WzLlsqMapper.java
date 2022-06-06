package com.xi.gamis.mapper;

import com.xi.gamis.entity.WzLlsq;
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
 * @since 2021-07-22
 */
public interface WzLlsqMapper extends BaseMapper<WzLlsq> {
@Select("exec wz_llsq_show #{InsID},#{dpID},#{state}")
List<Map<String,Object>> GetApplicationByDepartmentId(int InsID,int dpID,int state);
@Select("exec wz_llsq_sp #{id},#{newState},#{result},#{userName},#{remark}")
void Approval(int id,int newState,int result,String userName,String remark);
@Select("exec wz_llsq_updatebs #{insID},#{dpID},#{oldStatus},#{newStatus},#{userID},#{userName},#{lsh}")
void UpdateStatus(int insID,int dpID,int oldStatus,int newStatus,String userName,int userID,String lsh);
@Select("exec wz_llsq_spth #{id},#{remark},#{userID},#{userName}")
void Deny(int id,int userID,String remark,String userName);
}
