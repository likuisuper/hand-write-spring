package com.cxylk.springframework.test.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author likui
 * @date 2021/11/18 3:50 下午
 **/
public class UserDao {
    private static Map<String,String> map=new HashMap<>();

    static {
        map.put("1000","cxylk1");
        map.put("1001","cxylk2");
        map.put("1002","cxylk3");
    }

    public String queryByName(String uId){
        return map.get(uId);
    }

}
