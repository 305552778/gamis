package com.xi.gamis.controller;

import com.dingtalk.api.response.OapiProcessinstanceGetResponse;
import com.taobao.api.ApiException;
import com.xi.gamis.infrastructure.DDApi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dd")
public class DDController {
    @RequestMapping("/ins")
    public List<OapiProcessinstanceGetResponse.ProcessInstanceTopVo> test(String key) throws ApiException {
        ArrayList<OapiProcessinstanceGetResponse.ProcessInstanceTopVo> items=new ArrayList<OapiProcessinstanceGetResponse.ProcessInstanceTopVo>();
        OapiProcessinstanceGetResponse.ProcessInstanceTopVo a= DDApi.GetInstance("02329203-18e9-4b80-9f34-d00a0f628f6f");
        OapiProcessinstanceGetResponse.ProcessInstanceTopVo b= DDApi.GetInstance("025e62a5-0922-449b-8911-6bbb257a6c3d");
        OapiProcessinstanceGetResponse.ProcessInstanceTopVo c= DDApi.GetInstance("02c4710e-2812-40b7-901a-a81259def791");
        items.add(a);items.add(b);items.add(c);
        return items.stream().filter(x->x.getFormComponentValues().stream().anyMatch(f->f.getValue().equals(key))).collect(Collectors.toList());
        //return items.stream().filter(x->"18608025203".equals(x.getFormComponentValues().stream().filter(ff->ff.getName().equals("申请人电话")))).collect(Collectors.toList());

    }
}
