package com.jc.service.task;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: zhaojingchun
 * Date: 15-6-8
 * Time: 下午8:37
 * To change this template use File | Settings | File Templates.
 */
public class Test {
    public static void main(String[] args) throws IOException {
//        String str ="http://jdtuan.manzuo.com/jdtuan/order/save,http://jdtuan.manzuo.com/jdtuan/teamsellcount,http://jdtuan.manzuo.com/jdtuan/order/refund,http://api.liketuan.com/jingdong/SendOrderRequest.php,http://www.hegou.cn/openapi/tg360buy/bin/sendOrderAPI.php";
        List<String> readList = readFile02();
        Set<String> hostSet = new HashSet<String>();
        for(String str : readList){
            hostSet.add(getHost(str)) ;
        }
        List list = new ArrayList();
        list.addAll(hostSet);
        try {
            writeFile02(list);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
//        Iterator<String> it =  hostSet.iterator();
//        System.out.println("====================================");
//       while (it.hasNext()){
//             String host = it.next();
//           System.out.println(host);
//       }
//        System.out.println("====================================");

    }

    public static String getHost(String url){
        Pattern p = Pattern.compile("(?<=//|)((\\w)+\\.)+\\w+(:?\\d*)");
        Matcher m = p.matcher(url);
        if(m.find()){
            return(m.group());
        }else{
            System.out.print(url);
            return "";
        }
    }

    /**
     * 一行一行读取文件，解决读取中文字符时出现乱码
     * 流的关闭顺序：先打开的后关，后打开的先关，
     * 否则有可能出现java.io.IOException: Stream closed异常
     * @throws IOException
     */
    public static List<String> readFile02() throws IOException {
        List<String> retList = new ArrayList<String>();
        FileInputStream fis=new FileInputStream("D:/2.csv");
        InputStreamReader isr=new InputStreamReader(fis, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        //简写如下
        //BufferedReader br = new BufferedReader(new InputStreamReader(
        //        new FileInputStream("E:/phsftp/evdokey/evdokey_201103221556.txt"), "UTF-8"));
        String line="";

        while ((line=br.readLine())!=null) {
            retList.add(line);
        }
        br.close();
        isr.close();
        fis.close();
        return retList;
    }

    /**
     * 一行一行写入文件，解决写入中文字符时出现乱码
     *
     * 流的关闭顺序：先打开的后关，后打开的先关，
     *       否则有可能出现java.io.IOException: Stream closed异常
     *
     * @throws IOException
     */
    public static void writeFile02(List<String> list) throws IOException {
//        String[] arrs={
//                "zhangsan,23,福建",
//                "lisi,30,上海",
//                "wangwu,43,北京",
//                "laolin,21,重庆",
//                "ximenqing,67,贵州"
//        };
        //写入中文字符时解决中文乱码问题
        FileOutputStream fos=new FileOutputStream(new File("D:/201103221557.txt"));
        OutputStreamWriter osw=new OutputStreamWriter(fos, "UTF-8");
        BufferedWriter  bw=new BufferedWriter(osw);
        //简写如下：
        //BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
        //        new FileOutputStream(new File("E:/phsftp/evdokey/evdokey_201103221556.txt")), "UTF-8"));

        for(String str:list){
            bw.write(str+"\t\n");
        }
        //注意关闭的先后顺序，先打开的后关闭，后打开的先关闭
        bw.close();
        osw.close();
        fos.close();
    }
}
