package edu.xubin.util.WechatService;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import edu.xubin.bean.User;
import edu.xubin.util.PublicVariable;
import org.apache.http.Consts;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//访问企业微信的数据库
public class WechatUserService {


    //0是get
    //1是post
    public static JsonNode getOrPostWechatUser(String url, int getOrPost, Map<String,Object> map){
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = null;
        if (getOrPost == 0){
            try {
                jsonNode = mapper.readTree(Request.Get(PublicVariable.PreUrlWechatUser + url).execute().returnContent().asString(Consts.UTF_8));
                return jsonNode;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (getOrPost == 1){
            try {
                jsonNode = mapper.readTree(Request.Post(PublicVariable.PreUrlWechatUser + url).bodyString(JSON.toJSONString(map),ContentType.APPLICATION_JSON)
                        .execute().returnContent().asString(Consts.UTF_8));
                return jsonNode;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static User getUserByUserid(String userid){
        String url = "/get?access_token=" + PublicVariable.ACCESS_TOKEN + "&userid=" + userid;
        JsonNode jsonNode = getOrPostWechatUser(url, 0, null);
        User user = new User();
        user.setUserid(jsonNode.get("userid").asText());
        user.setName(jsonNode.get("name").asText());
        user.setEmail(jsonNode.get("email").asText());
        user.setMobile(jsonNode.get("mobile").asInt());
//        ArrayNode arrayNode = (ArrayNode)jsonNode.get("departmentid");
//        user.setDepartmentid(arrayNode.get(arrayNode.size()).asInt());
        return user;
    }

    public static List<User> getAllUser(){
        String url = "/list?access_token=" + PublicVariable.ACCESS_TOKEN + "&department_id=1" + "fetch_child=1";
        JsonNode jsonNode = getOrPostWechatUser(url, 0, null);
        ArrayNode arrayNode = (ArrayNode)jsonNode.get("userlist");
        List<User> userList = new ArrayList<>();
        for(JsonNode node : arrayNode){
            User user = new User();
            user.setUserid(jsonNode.get("UserID").asText());
            user.setName(jsonNode.get("name").asText());
            user.setEmail(jsonNode.get("email").asText());
            user.setMobile(jsonNode.get("mobile").asInt());
            userList.add(user);
        }
        return userList;
    }
}
