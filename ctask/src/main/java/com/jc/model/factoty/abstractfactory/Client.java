package com.jc.model.factoty.abstractfactory;

/**
 * 我感觉工厂模式就是把类的实现推迟到后面实现，
 * 而抽象工厂模式就是把工厂的实现也推迟了，可以说是工厂的工厂。
 * 这也就不难理解抽象工厂的二维结构了。至于等级的扩展，我感觉只要再加一维，
 * 抽象类也用工厂来实现可以满足要球，当然这也大大增加了复杂度。
 * User: zhaojingchun
 * Date: 15-5-14
 * Time: 下午3:05
 */
public class Client {
    public static void main(String[] args){
        AbstractFactory factory = null;
        factory = new  ConcreteFactory1();
        factory.createProductionA();
    }
}

abstract class AbstractFactory{
    abstract public AbstractProductionA createProductionA();
    abstract public AbstractProductionB createProductionB();
}

class ConcreteFactory1 extends AbstractFactory{

    @Override
    public AbstractProductionA createProductionA() {
        return new ProcudtonA1();
    }

    @Override
    public AbstractProductionB createProductionB() {
        return new ProcudtonB1();
    }
}

class ConcreteFactory2 extends AbstractFactory{

    @Override
    public AbstractProductionA createProductionA() {
        return new ProcudtonA2();
    }

    @Override
    public AbstractProductionB createProductionB() {
        return new ProcudtonB2();
    }
}

abstract class AbstractProductionA{

}

class ProcudtonA1 extends AbstractProductionA{

}

class ProcudtonA2 extends AbstractProductionA{

}

abstract class AbstractProductionB{

}

class ProcudtonB1 extends AbstractProductionB{

}

class ProcudtonB2 extends AbstractProductionB{

}