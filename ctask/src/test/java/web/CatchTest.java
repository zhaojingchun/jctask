package web;

import com.jc.common.HttpClientUtil;
import com.jc.web.Catch;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by jingchun on 2015/4/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml" })
public class CatchTest {
    private Catch catch1;
    @Autowired
    private HttpClientUtil httpClientUtil;
    @Test
    public void createFileTest(){
        httpClientUtil.printStr();
    }
    @BeforeClass
    public static void init(){
//        ApplicationContext context = new ClassPathXmlApplicationContext();
    }

    public Catch getCatch1() {
        return catch1;
    }

    public void setCatch1(Catch catch1) {
        this.catch1 = catch1;
    }

    public HttpClientUtil getHttpClientUtil() {
        return httpClientUtil;
    }

    public void setHttpClientUtil(HttpClientUtil httpClientUtil) {
        this.httpClientUtil = httpClientUtil;
    }
}
