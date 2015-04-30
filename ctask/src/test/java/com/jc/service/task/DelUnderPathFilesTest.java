package com.jc.service.task;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: zhaojingchun
 * Date: 15-4-30
 * Time: 下午3:57
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml" })
public class DelUnderPathFilesTest {
    @Resource
    private DelUnderPathFiles delUnderPathFiles ;
    @Test
    public void  delAllPathFiles(){
        delUnderPathFiles.delAllPathFiles();
    }

    public DelUnderPathFiles getDelUnderPathFiles() {
        return delUnderPathFiles;
    }

    public void setDelUnderPathFiles(DelUnderPathFiles delUnderPathFiles) {
        this.delUnderPathFiles = delUnderPathFiles;
    }
}
