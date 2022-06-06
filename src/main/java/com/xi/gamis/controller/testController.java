package com.xi.gamis.controller;

import com.xi.gamis.entity.Order;
import com.xi.gamis.entity.OrderDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/test")
public class testController {
    @RequestMapping("/aa")
    public Map<String, Object> xxx() throws IllegalAccessException {
        Order order=new Order();
        order.setId(1);
        order.setName("来自龚强的订单");
        order.setUser("龚强");
        ArrayList<OrderDetails> items=new ArrayList<>();
        OrderDetails ss=new OrderDetails();
        ss.setKey("key1");
        ss.setValue("v1");
        ss.setOrderId(1);
        OrderDetails ff=new OrderDetails();
        ff.setKey("key2");
        ff.setValue("v2");
        ff.setOrderId(1);
        OrderDetails tt=new OrderDetails();
        tt.setKey("key3");
        tt.setValue("v3");
        tt.setOrderId(1);
        items.add(ss);
        items.add(ff);
        items.add(tt);
        order.setOrderDetails(items);
        Map<String, Object> result3 = items.stream().collect(
                Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
        // 合并
        Map<String, Object> maps = new HashMap<String, Object>();
        Class<?> cla = order.getClass();
        Field[] fields = cla.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String keyName = field.getName();
            Object value = field.get(order);
            if (value == null)
                value = "";
            if(!"orderDetails".equals(keyName))
            {
                maps.put(keyName, value);
            }
        }
        Map<String, Object> combineResultMap = new HashMap<String, Object>();
        combineResultMap.putAll(maps);
        combineResultMap.putAll(result3);
        return combineResultMap;

    }
    public static   Map beanToMap(Object object) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<String, Object>();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(object));
        }
        return map;
    }

}
