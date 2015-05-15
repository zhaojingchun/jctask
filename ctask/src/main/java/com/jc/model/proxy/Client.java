package com.jc.model.proxy;

import com.jc.common.LogHelper;

/**
 * 咱俩是通一个东西但是代理需要帮
 * 被代理的对象处理一些东西
 * User: zhaojingchun
 * Date: 15-5-15
 * Time: 上午11:29
 * To change this template use File | Settings | File Templates.
 */
public class Client {
    public static void main(String[] args){
        Subject subject = new Proxy(new RealSubject());
        subject.request();
    }
}

abstract class Subject{
    abstract public void request();
}

class Proxy extends Subject{
    RealSubject realSubject;

    public Proxy(RealSubject realSubject){
        this.realSubject = realSubject;
    }

    public void request() {
        LogHelper.log.info("Proxy.request");
        preRequest();
        realSubject.request();
        postRequest();
    }

    public void preRequest(){
        LogHelper.log.info("Proxy.preRequest");
    }

    public void postRequest(){
        LogHelper.log.info("Proxy.postRequest");
    }


}

class RealSubject extends Subject{

    public void request() {
        LogHelper.log.info("RealSubject.request");
    }
}

