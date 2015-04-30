package com.jc.service.task.impl;

import com.jc.common.JcApplicationContext;
import com.jc.service.task.DelUnderPathFiles;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by jingchun on 2015/4/27.
 */
@Service("delUnderPathFiles")
public class DelUnderPathFilesImpl implements DelUnderPathFiles {
    @Resource
    private String delPath;
    @Resource
    private JcApplicationContext  jcApplicationContext;

    public void delAllPathFiles(){
        System.out.println("....................DelUnderPathFilesImpl.delAllPathFiles execute....................");
        String realPath =jcApplicationContext.getRootPath()+delPath;
        File file = new File (realPath);
        deleteDir(file);
        System.out.println(".......................DelUnderPathFilesImpl.delAllPathFiles end......................");
    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     *                 If a deletion fails, the method stops attempting to
     *                 delete and returns "false".
     */
    private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

    public String getDelPath() {
        return delPath;
    }

    public void setDelPath(String delPath) {
        this.delPath = delPath;
    }
}
