package com.zpstudio.tc;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.UUID;

/**
 * @Description 模拟客户端发起大量并发请求
 * 服务端判断是否使用了相同的request对象（根据传递参数判断）
 * @Author zhangpeng
 * @Date 2018/5/31 9:20
 **/
public class Test {
    public static void main(String[] args) {
        String prefix = UUID.randomUUID().toString().replaceAll("-","") + "::";
        for(int i = 0; i < 10000; i++){
            final String value = prefix + i;
            new Thread(){
              public void run(){
                  String url = "http://localhost:8080/ws/getRequestByParam?key=";
                  //"http://localhost:8080/ws/getRequestByParam?key=";
                  //"http://localhost:8080/ws/getRequestByAutowired?key=";
                  //"http://localhost:8080/ws/getRequestByParentAutowired?key=";
                  //"http://localhost:8080/ws/getRequestByContextHolder?key=";

                  //"http://localhost:8080/ws/getRequestByModelAttribute?key=";     //不安全
                  try{
                      CloseableHttpClient httpClient = HttpClients.createDefault();
                      HttpGet httpGet = new HttpGet(url + value);
                      httpClient.execute(httpGet);
                      httpClient.close();
                  }catch (IOException e){
                      e.printStackTrace();
                  }
              }
            }.start();
        }
    }
}