package com.example.demo.utility;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;

public class WxUtil {

    private static final Logger logger=  LoggerFactory.getLogger(WxUtil.class);
    private static final String openIdUrl="";


    public static String getOpenId(String appId,String secret,String code){
        try(CloseableHttpClient httpClient= HttpClientBuilder.create().build()){
    String requestUrl=String.format(openIdUrl,appId,secret,code);
            HttpGet httpGet=new HttpGet(requestUrl);
            HttpEntity responseEntity=httpClient.execute(httpGet).getEntity();
            if (responseEntity!=null){
                String responseStr= EntityUtils.toString(responseEntity);
                if (responseStr.contains("openid")){
                    WxResponse wxResponse=JsonUtil.toJsonObject(responseStr,WxResponse.class);
                    return wxResponse.getOpenid();
                }

            }}catch(IOException e){
                logger.error(e.getMessage(),e);
            }
        return null;
}

}
