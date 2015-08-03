package com.jc;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jingchun on 2015/5/7.
 */
public class Test {
    public static void main(String [] args){
        test1("12345ss1234dd32342");
//        test1("12345");
    }
    public static  void test1(String str){
        Pattern pt = Pattern.compile("(\\d+).*?(\\d+)");
        Matcher mc = pt.matcher(str);
        int i=0;
        while(mc.find()){
            System.out.println(++i);
        }
        if(mc.matches()){
            mc.group();

            mc.group(2);
            mc.find();

        }
    }

    public static  void test2(){
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
            String ip = addr.getLocalHost().toString();
            String address1 =addr.getHostName().toString();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

}
