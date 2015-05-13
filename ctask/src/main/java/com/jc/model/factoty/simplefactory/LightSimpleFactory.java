package com.jc.model.factoty.simplefactory;

import org.apache.commons.lang3.StringUtils;

import java.io.Console;

/**
 * Created with IntelliJ IDEA.
 * User: zhaojingchun
 * Date: 15-5-13
 * Time: 下午8:40
 * To change this template use File | Settings | File Templates.
 */
public class LightSimpleFactory {

    public static void main(String[] args){
        LightSimpleFactory factory = new LightSimpleFactory();
        Light light1 = factory.create("bul");
        light1.off();
        Light light2 = factory.create("tub");
        light2.off();

    }

    public Light create(String type){
        if(StringUtils.equals(type,"bul")){
            return new BulbLight();
        }else if(StringUtils.equals(type,"tub")){
            return new TubeLight();
        }
        return null;
    }
}

abstract class Light {
    public abstract void on();
    public abstract void off();
}

class TubeLight extends Light {
    @Override
    public void on() {
        System.out.println(TubeLight.class.getSimpleName()+" : no");
    }

    @Override
    public void off() {
        System.out.println(TubeLight.class.getSimpleName()+" : off");
    }
}

class BulbLight extends Light {
    @Override
    public void on() {
        System.out.println(BulbLight.class.getSimpleName()+" : no");
    }

    @Override
    public void off() {
        System.out.println(BulbLight.class.getSimpleName()+" : off");
    }
}


