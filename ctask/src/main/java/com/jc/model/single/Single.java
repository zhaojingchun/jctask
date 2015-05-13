package com.jc.model.single;

/**
 * Created with IntelliJ IDEA.
 * User: zhaojingchun
 * Date: 15-5-12
 * Time: 下午4:44
 * To change this template use File | Settings | File Templates.
 */
public class Single {
    private static Single single ;
    private Single(){};
    public static Single getInstance(){
        if(null==single){
            synchronized (Single.class){
                /**防止多线程时创建多个实例**/
                if(null==single){
                    return single = new Single();
                }
            }
        }
        return single;
    }
}
