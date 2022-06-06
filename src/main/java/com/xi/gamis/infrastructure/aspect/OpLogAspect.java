package com.xi.gamis.infrastructure.aspect;

import com.alibaba.fastjson.JSON;
import com.xi.gamis.dto.UserInfo;
import com.xi.gamis.entity.SysOplog;
import com.xi.gamis.infrastructure.annotation.OpLog;
import com.xi.gamis.service.impl.SysOplogServiceImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class OpLogAspect {
    @Autowired
    private HttpServletRequest request; // 直接注入
    @Autowired
    private SysOplogServiceImpl oplogService;

    @Pointcut("@annotation(com.xi.gamis.infrastructure.annotation.OpLog)")
    public  void opLogPoinCut()
    {

    }
    //@Pointcut("@execution()")
    //public void methodPosincut()
    //{

   // }


    @AfterReturning(value = "opLogPoinCut()", returning = "keys")
    public void addOplog(JoinPoint joinPoint,Object keys)
    {
        // 获取RequestAttributes
        //RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从获取RequestAttributes中获取HttpServletRequest的信息
        //HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);

        // 从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        HttpSession session=request.getSession(true);
        UserInfo user=(UserInfo)session.getAttribute("user");
        OpLog oplog= method.getAnnotation(OpLog.class);
        if(oplog!=null)
        {
            String mod=oplog.MOD();
            String desc=oplog.DESC();
            String opType=oplog.OPTYPE();
            String className = joinPoint.getTarget().getClass().getName();
            String methodName=className + "." +method.getName();
            Map<String, String> rtnMap = converMap(request.getParameterMap());
            String reqParams = JSON.toJSONString(rtnMap);
            String rspParams = JSON.toJSONString(keys);
            String userName=user.getUserName();
            int userId=user.getUserID();

            SysOplog sysOplog=new SysOplog();
            sysOplog.setModule(mod);
            sysOplog.setDesciption(desc);
            sysOplog.setOptype(opType);
            sysOplog.setMethod(methodName);
            sysOplog.setReqParam(reqParams);
            sysOplog.setRspParam(rspParams);
            sysOplog.setUsername(userName);
            //sysOplog.setUserid(userId.toString);
            oplogService.save(sysOplog);

        }
    }
    public Map<String, String> converMap(Map<String, String[]> paramMap) {
              Map<String, String> rtnMap = new HashMap<String, String>();
              for (String key : paramMap.keySet()) {
                        rtnMap.put(key, paramMap.get(key)[0]);
                   }
               return rtnMap;
            }
}
