package com.jc;

import com.jc.common.LogHelper;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.IOException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: zhaojingchun
 * Date: 15-7-1
 * Time: 下午4:59
 * To change this template use File | Settings | File Templates.
 */
public class ttt {
    public static void main(String[] args){
        int [] aa = new int[]{1,2,4};
        final List list1 = createArrayList(1,2,34,5);
        Map map = getQueryObj(new HashMap(){{put("aa",list1);}},"aa","bb","cc");
        String json = obj2Json(map);
        String json2 = obj2Json(getRange(getMap("get",100),"teamPrice"));
        System.out.print("");
        obj2Json(getTerm(getMap("ss",aa)));
    }
    public static Map<String,Object> getRange(Map<String,Object> map ,String key){
         return getQueryObj(map,key,"range");
    }
    public static Map<String,Object> getTerm(Map<String,Object> map){
        return getQueryObj(map,"term");
    }
    public static Map<String,Object>getTerms(Map<String,Object> map){
        return getQueryObj(map,"terms");
    }
    public static String obj2Json(Object obj) {
        if (obj != null) {
            ObjectMapper om = new ObjectMapper();
            try {
                //过滤值为null的属性
                om.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
                String partnerStr = om.writeValueAsString(obj);
                return partnerStr;
            } catch (IOException e) {
                LogHelper.log.error("Method:FindPartnerByPartnerIdResponseMessage.toString----> exception!", e);
            }
        }
        return "";
    }
    public static Map<String,Object> getQueryObj(Map<String,Object> map,String ... elements){
        Map<String,Object> retMap = map;
        for(int i=elements.length-1;i>-1;i--){
            Map tmpMap = new HashMap();
            tmpMap.put(elements[i],retMap);
            retMap =  tmpMap;
        }
        return retMap;
    }
    public static Map<String,Object> getMap(String key ,Object val){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put(key,val);
        return map;
    }
    public static <T> ArrayList<T> createArrayList(T ... elements) {
        ArrayList<T> list = new ArrayList<T>();
        for (T element : elements) {
            list.add(element);
        }
        return list;
    }
}
