package com.jc.model.observer;

import com.jc.common.LogHelper;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhaojingchun
 * Date: 15-5-15
 * Time: 下午6:11
 * To change this template use File | Settings | File Templates.
 */
public class Client {
    public static void main(String[] args){
        Subject subject = new ConcreteSubjcet("ssssss");
        ConcreteObserver ob1 = new ConcreteObserver();
        ConcreteObserver ob2 = new ConcreteObserver();
        subject.attach(ob1);
        subject.attach(ob2);
        subject.chang();
    }

}
interface Observer{
    public void update(Object state);
}

class ConcreteObserver implements Observer{
    private Object observerState;

    @Override
    public void update(Object state) {
        LogHelper.log.info("+++++"+state);
    }
}

abstract class Subject{
    List<Observer> observers = new ArrayList<Observer>();
    public void attach(Observer observer){
        observers.add(observer);

    }
    public void detach(Observer observer){
        observers.remove(observer);
    }
    public void notifyObserver(Object state) {
        if(CollectionUtils.isNotEmpty(observers)){
            for(int i=0,size = CollectionUtils.size(observers) ;i<size;i++){
                observers.get(i).update(state);
            }
        }
    }

    abstract public void chang();
}

class ConcreteSubjcet extends Subject{

    private Object state;

    public ConcreteSubjcet(Object state){
        this.state = state;
    }

    public Object getState() {
        return state;
    }

    @Override
    public void chang() {
        notifyObserver(state);
    }
}