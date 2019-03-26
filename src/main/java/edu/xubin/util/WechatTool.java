package edu.xubin.util;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Consts;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WechatTool {

    //0是get
    //1是post
    public static JsonNode getOrPostTool(String url, int getOrPost, Map<String,Object> map){
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = null;
        if (getOrPost == 0){
            try {
                jsonNode = mapper.readTree(Request.Get(url).execute().returnContent().asString(Consts.UTF_8));
                return jsonNode;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (getOrPost == 1){
            try {
                jsonNode = mapper.readTree(Request.Post(url).bodyString(JSON.toJSONString(map),ContentType.APPLICATION_JSON)
                        .execute().returnContent().asString(Consts.UTF_8));
                return jsonNode;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //获取Jsapi_ticket
    public static String getJsapi_ticket(){
        String url = "https://qyapi.weixin.qq.com/cgi-bin/get_jsapi_ticket?access_token="+ PublicVariable.ACCESS_TOKEN;
        JsonNode jsonNode = WechatTool.getOrPostTool(url, 0, null);
        return jsonNode.get("ticket").asText();
    }

    public static void wechatMsg(String touser, String toparty, String totag, String msgtype, Map<String,Object> textMap,String safe){
        Map<String, Object> map = new HashMap<>();
        map.put("touser", touser);
        map.put("toparty", toparty);
        map.put("totag", totag);
        map.put("msgtype", msgtype);
        map.put("agentid",PublicVariable.AGENGID);
        map.put(msgtype,textMap);
        if (safe!=null){
            map.put("safe",safe);
        }
        try {
            String result = EntityUtils.toString(Request.Post("https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token="+PublicVariable.ACCESS_TOKEN)
                    .bodyString(JSON.toJSONString(map), ContentType.APPLICATION_JSON).execute().returnResponse().getEntity(),"UTF-8");
            System.out.printf(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //0是文本消息
    //1是卡片消息
    public static Map<String,Object> wechatContentText(int msgType, String content){
        Map<String, Object> map = new HashMap<>();
        if (msgType == 0){
            map.put("content",content);
        }
            return map;
    }

    //0是文本消息
    //1是卡片消息
    public static Map<String,Object> wechatContentText(int msgType, String title, String description, String url, String btntxt){
        Map<String, Object> map = new HashMap<>();
       if(msgType == 1){
            map.put("title",title);
            map.put("description", description);
            map.put("url",url);
            map.put("btntxt",btntxt);
        }
        return map;
    }

    //获取最新的access_token
    public static void setAccessToken(){
        String accessTokenJSON = null;
        try {
            accessTokenJSON = Request.Get("https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid="+ PublicVariable.CORPID+"&corpsecret="+PublicVariable.CORPSECRET)
                    .execute().returnContent().asString(Consts.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String,Object> map=JSON.parseObject(accessTokenJSON,Map.class);
        PublicVariable.ACCESS_TOKEN = (String)map.get("access_token");
    }

    public static String getWechatUseridByCode(String code){
        setAccessToken();
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(Request.Get("https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token="+ PublicVariable.ACCESS_TOKEN +"&code="+code).execute().returnContent().asString(Consts.UTF_8));
            String usercode = node.get("UserId").asText();
            return usercode;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getWechatCodeFormat(String url){
        return "https://open.weixin.qq.com/connect/oauth2/authorize?appid=ww8c2f2da594c7306c&redirect_uri="
                + PublicVariable.url + url +"&response_type=code&scope=snsapi_base&state=#wechat_redirect";
    }

    public static String getWechatDescriptionFormat(String time, String row1, String row2, String row3){
        return "<div class=\"gray\">"+time+"</div>"
                + "<div class=\"normal\">"+row1+"</div>"
                + "<div class=\"highlight\">"+row2+"</div>"
                +"<div class=\"highlight\">"+row3+"</div>";
    }
}
