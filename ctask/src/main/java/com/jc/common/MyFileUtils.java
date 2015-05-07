package com.jc.common;


import org.apache.log4j.Logger;

import java.io.*;

/**
 * Created by jingchun on 2015/4/25.
 */
public class MyFileUtils {
    private static final Logger log = Logger.getLogger(MyFileUtils.class);
    public static void create(String pathStr,String fileName,String fileStr){
        File path = new File(pathStr);
        if(!path.exists()){
            path.mkdirs();
        }
        FileOutputStream outputStream = null;
        OutputStreamWriter writer = null;
        try {
            File file = new File(pathStr,fileName);
            if(!file.exists()){
                file.createNewFile();
            }
            outputStream = new FileOutputStream(file);
            writer = new OutputStreamWriter(outputStream,"utf-8");
            writer.write(fileStr);
            writer.flush();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally{
            try {
                outputStream.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public static void create(String pathStr,String fileName,byte[] bytes){
        File path = new File(pathStr);
        if(!path.exists()){
            path.mkdirs();
        }
        FileOutputStream outputStream = null;
        try {
            File file = new File(pathStr,fileName);
            if(!file.exists()){
                file.createNewFile();
            }
            outputStream = new FileOutputStream(file);
            outputStream.write(bytes);
            outputStream.flush();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally{
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * 创建文件或目录
     * @param path
     * @return
     */
    public static boolean creatFile(String path){
        File file = new File(path);
        if(file.isDirectory()&&(file.exists()==false)){
            file.mkdirs();
            return true;
        }else {
            File pf = file.getParentFile() ;
            if(pf!=null && (pf.exists()==false)){
                pf.mkdirs();
                try {
                    file.createNewFile();
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return true;
    }
}
