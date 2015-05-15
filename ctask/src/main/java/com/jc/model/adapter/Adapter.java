package com.jc.model.adapter;

/**
 * 适配器说通俗点就是把一个东西包装一下变成另外一个东西
 * 对象适配器
 * User: zhaojingchun
 * Date: 15-5-13
 * Time: 下午2:39
 * To change this template use File | Settings | File Templates.
 */
public class Adapter implements Target{
    //此处可以用工厂方法
    private Adaptee adapteet=null;
    public void request(){
        adapteet.specificRequest();
    }
}

/**
 * 目标接口
 */
interface Target{
    public void request();
}

/**
 * 被适配器对象
 */
class Adaptee{
    public void specificRequest(){
        System.out.println("这是原始标准接口！");
    }
}

/**
 * 类适配器
 */
class ClassAdapter extends Adaptee implements Target{
    @Override
    public void request() {
        specificRequest();
    }
}