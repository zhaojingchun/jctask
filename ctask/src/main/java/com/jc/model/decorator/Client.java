package com.jc.model.decorator;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 你在一个地方写装饰，大家就知道这是在增加功能，你写代理，大家就知道是在限制，
 * User: zhaojingchun
 * Date: 15-6-1
 * Time: 上午11:16
 * To change this template use File | Settings | File Templates.
 */
public class Client {
    public static void main(){
        try {
            FileInputStream input = new FileInputStream("");
            BufferedInputStream bf = new BufferedInputStream(input);
            bf.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}


