package com.xi.gamis.infrastructure.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xi.gamis.dto.CommonResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseUtil {
    public static void out(HttpServletResponse response, CommonResult r) throws IOException {
//        ObjectMapper objectMapper=new ObjectMapper();
//        response.setStatus(HttpStatus.OK.value());
//        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
//        objectMapper.writeValue(response.getWriter(),r.getMsg());


        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream out = response.getOutputStream();
        ObjectMapper objectMapper = new ObjectMapper();
        out.write(objectMapper.writeValueAsString(r.getMsg()).getBytes("UTF-8"));
        out.flush();
        out.close();

    }
}
