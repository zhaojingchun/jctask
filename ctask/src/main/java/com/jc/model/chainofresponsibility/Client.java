package com.jc.model.chainofresponsibility;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * Created with IntelliJ IDEA.
 * User: zhaojingchun
 * Date: 15-5-13
 * Time: 下午4:25
 * To change this template use File | Settings | File Templates.
 */
public class Client {
    public static void main(String[] args){
        Request request = new Request();
        request.setId(50);
        ConcretHandler1 hander1 = new ConcretHandler1();
        ConcretHandler2 hander2 = new ConcretHandler2();
        hander1.setSucessor(hander2);
        hander1.handleRequest(request);
    }
}

abstract class Handler{
    protected Handler sucessor;
    public void handleRequest(Request request){
        if(canHandle(request)){
            myHandle(request);
        }else{
            if(null != sucessor){
                sucessor.handleRequest(request);
            }else{
                System.out.println("无处理");
            }
        }
    }
    /**
     * 判断是否可以处理
     * @param request
     * @return
     */
    abstract boolean canHandle(Request request);

    /**
     * 处理方法
     * @param request
     */
    abstract void myHandle(Request request);

    public Object getRequestProperty(Request request,String key){
        try {
            Object id = PropertyUtils.getSimpleProperty(request, key);
            return id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    Handler getSucessor() {
        return sucessor;
    }

    void setSucessor(Handler sucessor) {
        this.sucessor = sucessor;
    }
}

class ConcretHandler1 extends Handler{

    @Override
    boolean canHandle(Request request) {
        Object obj= getRequestProperty(request,"id");
        if(obj!=null){
            Integer no = (Integer) obj;
            if(no>100){
                return true;
            }
        }
        return false;
    }

    @Override
    void myHandle(Request request) {
        System.out.println("ConcretHandler1 处理");
    }
}

class ConcretHandler2 extends Handler{

    @Override
    boolean canHandle(Request request) {
        Object obj= getRequestProperty(request,"id");
        if(obj!=null){
            Integer no = (Integer) obj;
            if(no<100){
                return true;
            }
        }
        return false;
    }

    @Override
    void myHandle(Request request) {
        System.out.println("ConcretHandler2 处理");
    }
}