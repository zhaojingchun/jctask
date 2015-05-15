package com.jc.model.factoty.factoinmethod;

/**
 * Created with IntelliJ IDEA.
 * User: zhaojingchun
 * Date: 15-5-13
 * Time: 下午9:01
 * To change this template use File | Settings | File Templates.
 */
public class Client {
    public static void main(String[] args){
        Creator creator = new BlubCreator();
        Light light = creator.factory();
        light.trunOff();
    }
}

abstract class Creator{
    abstract public Light factory();
}

class BlubCreator extends Creator{
    @Override
    public Light factory() {
        return new BlubLight();
    }
}

class TubeCreator extends Creator{
    @Override
    public Light factory() {
        return new TubeLight();
    }
}

abstract class Light{
    abstract public void turnOn() ;
    abstract public void trunOff();
}

class BlubLight extends Light{
    public void turnOn(){
         System.out.println("BlubLight on");
    }
    public void trunOff(){
        System.out.println("BlubLight off");
    }
}

class TubeLight extends Light{
    public void turnOn(){
        System.out.println("TubeLight on");
    }
    public void trunOff(){
        System.out.println("TubeLight off");
    }
}