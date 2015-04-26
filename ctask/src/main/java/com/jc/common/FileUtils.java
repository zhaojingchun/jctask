package com.jc.common;


import org.apache.log4j.Logger;

import java.io.*;

/**
 * Created by jingchun on 2015/4/25.
 */
public class FileUtils {
    private static final Logger log = Logger.getLogger(FileUtils.class);
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
}
