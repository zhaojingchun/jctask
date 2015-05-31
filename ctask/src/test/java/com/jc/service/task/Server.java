package com.jc.service.task;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by jingchun on 2015/5/29.
 */
public class Server {
    public static void main(String[] args){
        try {
            //1、创建serversocket
            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.print("服务器即将启动");
            //2、 监听
            Socket socket = serverSocket.accept();
            //3、 获取输入流
            InputStream in = socket.getInputStream();
            InputStreamReader inr = new InputStreamReader(in);
            BufferedReader bfr = new BufferedReader(inr);
            String data = null;
            while((data = bfr.readLine())!=null){
                System.out.println("客户端说： "+data);
            }
            socket.shutdownInput();
            //获取输出流
            OutputStream out = socket.getOutputStream();
            PrintWriter pr = new PrintWriter(out);
            pr.write("欢迎您");
            pr.flush();
            //关闭资源
            pr.close();
            out.close();
            bfr.close();
            inr.close();
            in.close();
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
