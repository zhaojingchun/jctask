package com.jc.service.task;

import java.io.*;
import java.net.Socket;

/**
 * Created by jingchun on 2015/5/29.
 */
public class Client {
    public static void main(String[] args){
        try {
            //创建客户端sorcke
            Socket socket = new Socket("localhost",8888);
            //获取输出流
            OutputStream out = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(out);
            pw.write("用户名：zhao;密码：123");
            pw.flush();
            socket.shutdownOutput();
            //获取输入流
            InputStream in = socket.getInputStream();
            InputStreamReader inr = new InputStreamReader(in);
            BufferedReader bfr = new BufferedReader(inr);
            String data = null;
            while((data = bfr.readLine())!=null){
                System.out.println("服务端说： "+data);
            }
            //关闭输出流
            bfr.close();
            inr.close();
            in.close();
            pw.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
