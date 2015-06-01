package com.jc.model.strategy;

import com.jc.common.LogHelper;

/**
 * 策略模式
 * 策略模式属于对象的行为模式。其用意是针对一组算法，
 * 将每一个算法封装到具有共同接口的独立的类中，从而使
 * 得它们可以相互替换。策略模式使得算法可以在不影响到
 * 客户端的情况下发生变化。
 * User: zhaojingchun
 * Date: 15-5-15
 * Time: 下午4:49
 * To change this template use File | Settings | File Templates.
 */

/**
 * 策略模式聚焦的是对相同请求更换解决方案的灵活性；而命令模式聚焦的是
 * 对多请求变化的封装以及对相同请求不同的请求形式解决方法的可复用性
 */
public class Client {
    public static void main(String[] args){
        int i=2;
        Price price = new Price(strategyCreate(i));
        price.quote(300);
    }

    public static MemberStrategy strategyCreate(int i){
        switch (i) {
            case 1 : return new PrimaryMemberStrategy();
            case 2 : return new IntermediateMemberStrategy();
            case 3 : return new AdvancedMemberStrategy();
            default:
                return new PrimaryMemberStrategy();
        }
    }
}

class Price{
    private MemberStrategy strategy;
    public Price(MemberStrategy strategy){
        this.strategy = strategy;
    }
    public double quote(double price){
       return strategy.calcPrice(price);
    }
}

interface MemberStrategy{
    public double calcPrice(double price);
}

class PrimaryMemberStrategy implements MemberStrategy{
    public double calcPrice(double price) {
        LogHelper.log.info("初级会员没有折扣");
        return price;
    }
}

class IntermediateMemberStrategy implements MemberStrategy{
    public double calcPrice(double price) {
        System.out.println("对于中级会员的折扣为10%");
        return price * 0.9;
    }
}

class AdvancedMemberStrategy implements MemberStrategy{
    public double calcPrice(double price) {
        System.out.println("对于高级会员的折扣为20%");
        return price * 0.8;
    }
}
