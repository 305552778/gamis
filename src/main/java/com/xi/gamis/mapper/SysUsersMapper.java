package com.xi.gamis.mapper;

import com.xi.gamis.entity.SysBmlb;
import com.xi.gamis.entity.SysUsers;
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
public interface SysUsersMapper extends BaseMapper<SysUsers> {

   @Select("select * from V所有用户 where yhm=#{yhm} and mm=#{mm}")
   public List<Map<String,Object>> getUserVO(String yhm,String mm);
   public  SysUsers getUser();
   @Select("select a.bh,a.xm,c.bm,a.yhm from sys_users a inner join rsgz_sgzl b on\n" +
           "       a.bh=b.ygbh inner join sys_bmlb c on b.bmbh=c.bmbh  where a.zt='在职'")
   public List<Map<String,Object>> getUserVO2();
   @Select("select c.* from Sys_users a inner join rsgz_sgzl b on  a.bh=b.ygbh inner join sys_bmlb c on b.bmbh=c.bmbh  where a.bh=#{userID} and a.zt='在职'")
   public SysBmlb getDepartmentOfUser(int userID);

}
