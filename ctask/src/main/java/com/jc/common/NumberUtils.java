package com.jc.common;

import org.apache.commons.lang3.StringUtils;

/**
 * Created with IntelliJ IDEA.
 * User: zhaojingchun
 * Date: 15-5-13
 * Time: 下午5:36
 * To change this template use File | Settings | File Templates.
 */
public class NumberUtils {
    public static int conventInt(String str){
        return conventInt(str,0);
    }

    /**
     * 字符串转换成int
     * @param str  带转化字符串
     * @param defaultNo 默认值
     * @return
     */
    public static int conventInt(String str,int defaultNo){
        int retNo=defaultNo;
        if(StringUtils.isNotBlank(str)){
            try{
                retNo = Integer.parseInt(str);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return  retNo;
    }

    /**
     * 获取指定位数的随机数
     * @param digit 位数
     * @return
     */
    public static long getRandom(int digit){
        if(digit<1)return 0;
        long m = Math.round(Math.pow(10,(digit-1)));
        long n = m*10-1-m;
        return Math.round(Math.random()*n+m);
    }
}
