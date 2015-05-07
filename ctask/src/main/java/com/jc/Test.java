package com.jc;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by jingchun on 2015/5/7.
 */
public class Test {
    public static void main(String [] args){
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
