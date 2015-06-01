package com.jc.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: zhaojingchun
 * Date: 15-5-29
 * Time: 上午10:30
 * To change this template use File | Settings | File Templates.
 */
public class ChatServer {
    public static void main(){
        ServerSocket svSocket = null;
        Vector threads = new Vector();
        try {
            svSocket = new ServerSocket(8888);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        while(true){
            try {
                int nid =0;
                Socket socket = svSocket.accept();
                System.out.println("accept a client");
                ServerThread st = new ServerThread(socket,threads);
                st.setNid(nid++);
                threads.add(st);
                new Thread(st).start();
                for(int i=0;i<threads.size();i++){
                    ServerThread temp = (ServerThread)threads.elementAt(i);
                }
                System.out.print("Listen again...");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.print("server is down");
            }
        }
    }
}
class ServerThread implements Runnable{
    private Vector threads;
    private Socket socket;
    private DataInputStream in =null;
    private DataOutputStream out = null;
    private int nid;
    public ServerThread(Socket socket,Vector threads){
        this.socket = socket;
        this.threads = threads;
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        System.out.println("Thread is running");
        try{
            while(true){
                String receive = in.readUTF();
                if(receive ==null)return;
                //下线通知
                if(receive.contains("黯然下线了")){
                    for(int i=0;i < threads.size();i++){
                        ServerThread st = (ServerThread)threads.elementAt(i);
                        st.write("***"+receive+"***");
                    }
                }
                //上线通知
                else if(receive.contains("上线了")){
                    for(int i=0;i < threads.size();i++){
                        ServerThread st = (ServerThread)threads.elementAt(i);
                        st.write("<"+getNid()+">: "+receive);
                    }
                }
                //作为服务器监听中
                else if(receive.contains("监听中")){
                    for(int i=0;i < threads.size();i++){
                        ServerThread st = (ServerThread)threads.elementAt(i);
                        st.write("***"+receive+"*** ");
                    }
                }
                //发送消息
                else if(receive.contains("说")){
                    //发送广播
                    if(receive.contains("大家")){
                        for(int i=0;i<threads.size();i++){
                            ServerThread st = (ServerThread)threads.elementAt(i);
                            st.write("<"+getNid()+">:"+receive);
                        }
                    }  //发送指定xiaoxi
                    else{
                        int temp = receive.indexOf("<");
                        int temp1 = receive.indexOf(">");
                        //获取接收者ID号
                        String tempS = receive.substring(temp+1,temp1);
                        int i = Integer.parseInt(tempS);
                        //指定接受者输出
                        ServerThread st = (ServerThread)threads.elementAt(i);
                        st.write("<"+getNid()+">:"+receive);
                        //发送者自己也输出
                        st=(ServerThread)threads.elementAt(getNid());
                        st.write("<"+getNid()+">:"+receive);
                    }
                }else{
                    ServerThread st = (ServerThread)threads.elementAt(getNid());
                    st.write("*****"+receive+"*****");
                }
            }
        }catch (Exception e ){
            System.out.println(e.getStackTrace());
        }
        try{
            socket.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void write(String msg){
        synchronized (out){
            try{
                out.writeUTF(msg); ;
            }catch (Exception ex){

            }
        }
    }

    int getNid() {
        return nid;
    }

    void setNid(int nid) {
        this.nid = nid;
    }
}
