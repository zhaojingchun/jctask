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
    public static int conventInt(String str,int defaultNo){
        int retNo=defaultNo;
        if(StringUtils.isNotBlank(str)){
            try{
                retNo = Integer.parseInt(str);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return Math.abs(retNo)>defaultNo?retNo:defaultNo;
    }
}
