package com.example.demo.configuration.spring.security;

import com.example.demo.base.RestResponse;
import com.example.demo.base.SystemCode;
import com.example.demo.utility.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RestUtil {
    private static final Logger logger= LoggerFactory.getLogger(RestUtil.class);

    public static void response(HttpServletResponse response, SystemCode systemCode){
        response(response,systemCode.getCode(),systemCode.getMessage());
    }

    public static void response(HttpServletResponse response,int systemCode,String msg){
        response(response,systemCode,msg,null);
    }

    public static void response(HttpServletResponse response, int systemCode,String msg,Object content){
        try{
            RestResponse res=new RestResponse<>(systemCode,msg,content);
            String resStr= JsonUtil.toJsonStr(res);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(resStr);
        }catch (IOException e){
        logger.error(e.getMessage(),e);
    }

    }
}
