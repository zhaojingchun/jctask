package com.jc.service.task.impl;

import com.jc.service.task.DelUnderPathFiles;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;

/**
 * Created by jingchun on 2015/4/27.
 */
@Service("delUnderPathFiles")
public class DelUnderPathFilesImpl implements DelUnderPathFiles {
    @Resource
    private String delPath;

    public void delAllPathFiles(){
        File filePath = new File (delPath);
        System.out.println("DelUnderPathFilesImpl.delAllPathFiles execute....................");
    }

    public String getDelPath() {
        return delPath;
    }

    public void setDelPath(String delPath) {
        this.delPath = delPath;
    }
}
