package com.xi.gamis.infrastructure;

import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.*;
import com.dingtalk.api.response.*;
import com.taobao.api.ApiException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DDApi {
    /////龚强userID：09086663341290976
    /*private static String Appkey = "dingqiutbxtaju0ymkql";
    private static String AppSecret = "Xr1zNjf-QQtgtBSZcX_Ssp8jHJlIEj2DUX1KDypNHS8-RZfse7GfsC1_4BfHGlsY";
    private static String access_token ="";
    private static String agentId = "1256498579";
    private static String CorpId = "ding93a8f2c2230a245dee0f45d8e4f7c288";*/
    private static String Appkey = "dingxfqohvko37o5bwsc";
    private static String AppSecret = "0C2VbZMUIIA7ZK2cwW_QOVsMO9LGqhhZO5xOQTfEIbjxRS-MjwjdHvJCLQl8c2Go";
    private static String access_token ="";
    private static String agentId = "1317077191";
    private static String CorpId = "ding04f690dca0ea211a24f2f5cc6abecb85";


   public static OapiProcessinstanceGetResponse.ProcessInstanceTopVo GetInstance(String id) throws ApiException {
       DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/processinstance/get");
       OapiProcessinstanceGetRequest req = new OapiProcessinstanceGetRequest();
       req.setProcessInstanceId(id);
       OapiProcessinstanceGetResponse rsp = client.execute(req, GetToken());
       return rsp.getProcessInstance();
   }
     //钉钉创建待办任务
    //返回任务ID，可用于后续业务更新待办状态或删除
public static String CreateWorkRecoed(String userid, long create_time, String title, String url, String pcUrl,String formTitle,String formContent,String originator_user_id,String source_name,String biz_id )
{
    try {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/workrecord/add");
        OapiWorkrecordAddRequest req = new OapiWorkrecordAddRequest();
        req.setUserid(userid);
        req.setCreateTime(create_time);
        req.setTitle(title);
        req.setUrl(url);
        req.setPcUrl(pcUrl);
        List<OapiWorkrecordAddRequest.FormItemVo> list2 = new ArrayList<OapiWorkrecordAddRequest.FormItemVo>();
        OapiWorkrecordAddRequest.FormItemVo obj3 = new OapiWorkrecordAddRequest.FormItemVo();
        obj3.setTitle(formTitle);
        obj3.setContent(formContent);
        list2.add(obj3);

        req.setFormItemList(list2);
        req.setOriginatorUserId(originator_user_id);
        req.setSourceName(source_name);
        req.setPcOpenType(2L);//待办的PC打开方式:2：在PC端打开4：在浏览器打开
        req.setBizId(biz_id);
        OapiWorkrecordAddResponse rsp = client.execute(req, GetToken());
        return rsp.getRecordId();
        //System.out.println(rsp.getBody());
    }
    catch (ApiException e)
    {
        //e.printStackTrace();
        return e.getErrMsg();
    }
}
///bizID可以是业务ID，也可以是recordID
public static boolean UpdateWorkRecordState(String userid,String bizID)
{
    try {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/workrecord/update");
        OapiWorkrecordUpdateRequest req = new OapiWorkrecordUpdateRequest();
        req.setUserid(userid);
        req.setRecordId(bizID);
        OapiWorkrecordUpdateResponse rsp = client.execute(req, GetToken());
       return rsp.getResult();
    } catch (ApiException e) {
        //e.printStackTrace();
        return false;
    }
}
//获取待办任务列表，status：0未完成，1已完成
public static String GetWorkRecod(String userid,long offset,long limit,long status)
{
    try {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/workrecord/getbyuserid");
        OapiWorkrecordGetbyuseridRequest req = new OapiWorkrecordGetbyuseridRequest();
        req.setUserid(userid);
        req.setOffset(offset);
        req.setLimit(limit);
        req.setStatus(status);
        OapiWorkrecordGetbyuseridResponse rsp = client.execute(req, GetToken());
        return rsp.getBody();
    } catch (ApiException e) {
        //e.printStackTrace();
        return "";
    }

}
public static boolean SendLinkMsg(String useidlist,boolean to_all_user,String imgUrl,String msgUrl,String text,String title)
{
    try {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/message/corpconversation/asyncsend_v2");
        OapiMessageCorpconversationAsyncsendV2Request req = new OapiMessageCorpconversationAsyncsendV2Request();
        req.setAgentId(1256498579L);
        req.setUseridList(useidlist);//"09086663341290976,09086663341290976"接收者的userid列表，最大用户列表长度100。
        OapiMessageCorpconversationAsyncsendV2Request.Msg obj1 = new OapiMessageCorpconversationAsyncsendV2Request.Msg();
        obj1.setMsgtype("link");
        OapiMessageCorpconversationAsyncsendV2Request.Link obj2 = new OapiMessageCorpconversationAsyncsendV2Request.Link();
        obj2.setPicUrl(imgUrl);//消息中图片的链接地址
        obj2.setMessageUrl(msgUrl);//点击消息跳转的地址
        obj2.setText(text);
        obj2.setTitle(title);
        obj1.setLink(obj2);
        req.setMsg(obj1);
        OapiMessageCorpconversationAsyncsendV2Response rsp = client.execute(req, GetToken());
        return rsp.getErrcode()==0;
    } catch (ApiException e) {
        return false;
    }
}
public static String GetToken()
{
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
        OapiGettokenResponse response;
        OapiGettokenRequest request = new OapiGettokenRequest();
        request.setAppkey(Appkey);
        request.setAppsecret(AppSecret);
        request.setHttpMethod("GET");
        try {
            response = client.execute(request);
            int i=0;
            while (response.getAccessToken()==null)
            {

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
                if(i>20)
                    break;
            }
        } catch (ApiException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            return null;
        }
        //access_token=response.getAccessToken();
    return response.getAccessToken();//access_token;//
}
public static String GetDDUserId(String requestAuthCode) {
    // 获取用户信息
    DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/getuserinfo");
    OapiUserGetuserinfoRequest request = new OapiUserGetuserinfoRequest();
    request.setCode(requestAuthCode);
    request.setHttpMethod("GET");
    OapiUserGetuserinfoResponse response;
    String id="";
    int i=0;
    try {
        response = client.execute(request, GetToken());

        while (response.getUserid()==null)
        {

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
            if(i>20)
                break;
        }
        id=response.getUserid();
        //if(response.getErrcode()!=0)
        //{
            //id=response.getErrmsg()+"##"+requestAuthCode;
        //}
    } catch (ApiException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    return id;
}
public static JSONObject GetUserInfo(String userID)
{
    try {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/get");
        OapiUserGetRequest req = new OapiUserGetRequest();
        req.setUserid(userID);
        req.setHttpMethod("GET");
        OapiUserGetResponse rsp = client.execute(req, GetToken());
        int i=0;
        while (rsp.getBody()==null)
        {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
            if(i>20)
                break;
        }
        JSONObject jsonObj=JSONObject.parseObject(rsp.getBody());
        return jsonObj;
    } catch (ApiException e) {
        //e.printStackTrace();
        return null;
    }
}

}
