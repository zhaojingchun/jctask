package com.jc.service.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

/**
 * Created by jingchun on 2015/5/28.
 */
public class SocketTest {
    public static void main(String [] args){
        catchBaidu();
//        urlTest();
//        inetAdderss();

    }

    public static void catchBaidu(){
        try {
            URL url = new URL("http://www.hao123.com/");
            InputStream in = url.openStream();
            InputStreamReader inR = new InputStreamReader(in,"utf-8");
            BufferedReader bfR = new BufferedReader(inR);
            char[] readArr = new char[1024];
            int n=0;
            while((n=bfR.read(readArr))!=-1){
                System.out.print(readArr);
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void urlTest(){
        try {
            URL pUrl = new URL("http://www.imooc.com");
            URL url  = new URL(pUrl,"/index.html?name=zhao&cc=bb#user");
            System.out.println("协议 :"+url.getProtocol());
            System.out.println("路径 :"+url.getPath());
            System.out.println("端口 :"+url.getPort());
            System.out.println("默认端口 :"+url.getDefaultPort());
            System.out.println("域名 :"+url.getHost());
            System.out.println("参数 :"+url.getQuery());
            System.out.println("相对路径 :"+url.getRef());
            System.out.println("文件名 :" + url.getFile());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static void inetAdderss(){
        try {
            InetAddress address = InetAddress.getLocalHost();
            System.out.println("主机名 ："+address.getHostName());
            System.out.println("ip ：" + address.getHostAddress());
            address.getAddress();
            System.out.println("all ：" + address);
            System.out.println("================================");
            InetAddress address1 = InetAddress.getByName(address.getHostName());
            System.out.println("主机名 1 ："+address1.getHostName());
            System.out.println("ip 1 ：" + address1.getHostAddress());
            System.out.println("all 1 ：" + address1);
            System.out.println("================================");
            InetAddress address2 = InetAddress.getByName(address.getHostAddress());
            System.out.println("主机名 1 ："+address2.getHostName());
            System.out.println("ip 1 ：" + address2.getHostAddress());
            System.out.println("all 1 ：" + address2);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
