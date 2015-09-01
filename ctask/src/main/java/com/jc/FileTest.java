package com.jc;

import org.apache.commons.lang3.StringUtils;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: zhaojingchun
 * Date: 15-8-3
 * Time: 下午4:23
 * To change this template use File | Settings | File Templates.
 */
public class FileTest {
    //读取文件位置
    private static final String readFilePath = "D:"+ File.separator+"test"+File.separator+"yidong.csv";
    //输出文件位置
    private static final String outFilePath = "D:"+ File.separator+"test"+File.separator;
    private static BufferedWriter bw = null;
    //每页大小
    private static final long pageSize = 100000;
    //文件填充值起始值
    private static int  fileNo = 0;
    //文件前缀
    private static final String filePreStr = "xiuyan";
    //文件后缀
    private static final String fileEndStr = ".csv" ;
    //文件编码
    private static String encodeStr = "GBK" ;
    //文件头
    private static String headStr = "\"手机号码\",\"县区\",\"用户状态\",\"入网渠道编码\",\"实名制类型\",\"用户姓名\",\"证件类型\",\"证件号码\",\"地址\",\"超一证5号\"";
    //区分关键字序号
    private static final int keyNo = 1;
    //区分关键字符串
    private static final String keyStr = "\"岫岩县\"";
    //分隔符
    private static final String splitStr =",";
    public static void main(String[] args){
        try {
            readFile(readFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取文件
     * @param readFilePath
     * @throws IOException
     */
    public static void readFile(String readFilePath) throws IOException {
        FileInputStream fis=new FileInputStream(readFilePath);
        InputStreamReader isr=new InputStreamReader(fis, encodeStr);
        BufferedReader br = new BufferedReader(isr);
        String line="";
        String[] arrs=null;
        bw =  getBufferedWriter(filePreStr+fileNo+fileEndStr);
        writeLine(headStr);
        long lineNo = 1;
        while ((line=br.readLine())!=null) {
            if(StringUtils.isEmpty(line))continue;
            arrs=line.split(splitStr);
            if(keyStr.equals(arrs[keyNo])){
                if(lineNo%pageSize==0){
                    closeBufferedWriter(bw);
                    fileNo++ ;
                    bw =  getBufferedWriter(filePreStr+fileNo+fileEndStr);
                    writeLine(headStr);
                }
                ++lineNo;
                writeLine(line);
            }
        }
        closeBufferedWriter(bw);
        br.close();
        isr.close();
        fis.close();
    }

    /**
     * 往输出文件中输出一行数据
     * @param line
     * @throws IOException
     */
    public static void writeLine(String line) throws IOException {
        if(StringUtils.isEmpty(line)) return ;
        bw.write(line+"\t\n");
    }
    /**
     * 获得写出流
     * @param fileName
     * @return
     * @throws IOException
     */
    public static BufferedWriter getBufferedWriter(String fileName) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(new File(outFilePath,fileName)), encodeStr));
       return bw;
    }

    /**
     * 关闭写出流
     * @param bw
     * @throws IOException
     */
    public static void closeBufferedWriter(BufferedWriter bw) throws IOException {
        if(bw!=null){
            bw.flush();
            bw.close();
        }
    }

}
