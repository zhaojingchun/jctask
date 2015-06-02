package com.jc.service.qrcode;

import com.jc.common.ImageCompressUtil;
import com.swetake.util.Qrcode;
import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.data.QRCodeImage;
import jp.sourceforge.qrcode.exception.DecodingFailedException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by jingchun on 2015/5/31.
 */
public class QRcodeUtil {
    public static void main(String[] args){
        QRcodeUtil util = new QRcodeUtil();
        File upload = new File("D:\\22.jpg");
        ImageCompressUtil.zipImageFile(upload, 28, 28, 1f, "D:\\33.jpg");
        createQRCode("http://www.jd.com", "D:\\first.png","D:\\33.jpg");
//        util.encoderQRCode("http://www.jd.com", "D:\\first.png");
//        System.out.println(util.decoderQRCode("D:\\first.png"));
    }
    /**
     * 生成二维码(QRCode)图片
     * @param content 存储内容
     * @param imgPath 图片路径
     */
    public static void encoderQRCode(String content, String imgPath) {
        encoderQRCode(content, imgPath, "png", 7);
    }
    /**
     * 生成二维码(QRCode)图片
     * @param content 存储内容
     * @param imgPath 图片路径
     * @param imgType 图片类型
     * @param size 二维码尺寸
     */
    public static void encoderQRCode(String content, String imgPath, String imgType, int size) {
        try {
            BufferedImage bufImg = qRCodeCommon(content, size);
            File imgFile = new File(imgPath);
            // 生成二维码QRCode图片
            ImageIO.write(bufImg, imgType, imgFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void xx (){
//        Image img = ImageIO.read(new File(ccbPath));
//        //实例化一个Image对象。
//        gs.drawImage(img, 55, 55, null);
//        gs.dispose();
//        bufImg.flush();
//        //实例化一个Image对象。
//        gs.drawImage(img, 55, 55, null);
//        gs.dispose();
//        bufImg.flush();
//        // 生成二维码QRCode图片
//        File imgFile = new File(imgPath);
//        ImageIO.write(bufImg, "png", imgFile);
    }


    /**
     * 生成二维码(QRCode)图片的公共方法
     * @param content 存储内容
     * @param size 二维码尺寸
     * @return
     */
    private static BufferedImage qRCodeCommon(String content, int size) {
        BufferedImage bufImg = null;
        try {
            Qrcode qrcodeHandler = new Qrcode();
            // 设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小
            qrcodeHandler.setQrcodeErrorCorrect('M');
            qrcodeHandler.setQrcodeEncodeMode('B');
            // 设置设置二维码尺寸，取值范围1-40，值越大尺寸越大，可存储的信息越大
            qrcodeHandler.setQrcodeVersion(size);
            // 获得内容的字节数组，设置编码格式
            byte[] contentBytes = content.getBytes("utf-8");
            // 图片尺寸
            int imgSize = 67 + 12 * (size - 1);
            bufImg = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_RGB);
            Graphics2D gs = bufImg.createGraphics();
            // 设置背景颜色
            gs.setBackground(Color.WHITE);
            gs.clearRect(0, 0, imgSize, imgSize);

            // 设定图像颜色> BLACK
            gs.setColor(Color.BLACK);
            // 设置偏移量，不设置可能导致解析出错
            int pixoff = 2;
            // 输出内容> 二维码
            if (contentBytes.length > 0 && contentBytes.length < 800) {
                boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);
                for (int i = 0; i < codeOut.length; i++) {
                    for (int j = 0; j < codeOut.length; j++) {
                        if (codeOut[j][i]) {
                            gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
                        }
                    }
                }
            } else {
                throw new Exception("QRCode content bytes length = " + contentBytes.length + " not in [0, 800].");
            }
            Image img = ImageIO.read(new File("D:\\22.jpg"));
            //实例化一个Image对象。
            gs.drawImage(img, 42, 42, null);

            gs.dispose();
            bufImg.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bufImg;
    }

    /**
     * 解析二维码（QRCode）
     * @param imgPath 图片路径
     * @return
     */
    public static String decoderQRCode(String imgPath) {
        // QRCode 二维码图片的文件
        File imageFile = new File(imgPath);
        BufferedImage bufImg = null;
        String content = null;
        try {
            bufImg = ImageIO.read(imageFile);
            QRCodeDecoder decoder = new QRCodeDecoder();
            content = new String(decoder.decode(new MyQRCodeImage(bufImg)), "utf-8");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        } catch (DecodingFailedException dfe) {
            System.out.println("Error: " + dfe.getMessage());
            dfe.printStackTrace();
        }
        return content;
    }

    /**
     * 解析二维码（QRCode）
     * @param input
     * @return
     */
    public static String decoderQRCode(FileInputStream input) {
        // QRCode 二维码图片的文件
        BufferedImage bufImg = null;
        String content = null;
        try {
            bufImg = ImageIO.read(input);
            QRCodeDecoder decoder = new QRCodeDecoder();
            content = new String(decoder.decode(new MyQRCodeImage(bufImg)), "utf-8");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        } catch (DecodingFailedException dfe) {
            System.out.println("Error: " + dfe.getMessage());
            dfe.printStackTrace();
        }
        return content;
    }

    /**
     * 生成二维码(QRCode)图片
     * @param content 二维码图片的内容
     * @param imgPath 生成二维码图片完整的路径
     * @param ccbPath  二维码图片中间的logo路径
     */
    public static int createQRCode(String content, String imgPath,String ccbPath) {
        try {
            Qrcode qrcodeHandler = new Qrcode();
            qrcodeHandler.setQrcodeErrorCorrect('M');
            qrcodeHandler.setQrcodeEncodeMode('B');
            qrcodeHandler.setQrcodeVersion(7);

            // System.out.println(content);
            byte[] contentBytes = content.getBytes("gb2312");
            BufferedImage bufImg = new BufferedImage(140, 140, BufferedImage.TYPE_INT_RGB);
            Graphics2D gs = bufImg.createGraphics();

            gs.setBackground(Color.WHITE);
            gs.clearRect(0, 0, 140, 140);

            // 设定图像颜色 > BLACK
            gs.setColor(Color.BLACK);

            // 设置偏移量 不设置可能导致解析出错
            int pixoff = 2;
            // 输出内容 > 二维码
            if (contentBytes.length > 0 && contentBytes.length < 120) {
                boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);
                for (int i = 0; i < codeOut.length; i++) {
                    for (int j = 0; j < codeOut.length; j++) {
                        if (codeOut[j][i]) {
                            gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
                        }
                    }
                }
            } else {
                System.err.println("QRCode content bytes length = "
                        + contentBytes.length + " not in [ 0,120 ]. ");
                return -1;
            }
            Image logo = ImageIO.read(new File(ccbPath));//实例化一个Image对象。
            int widthLogo = logo.getWidth(null)>bufImg.getWidth()*2/10?(bufImg.getWidth()*2/10):logo.getWidth(null),
            heightLogo = logo.getHeight(null)>bufImg.getHeight()*2/10?(bufImg.getHeight()*2/10):logo.getWidth(null);

            /**
             * logo放在中心
             */
            int x = (bufImg.getWidth() - widthLogo) / 2;
            int y = (bufImg.getHeight() - heightLogo) / 2;
            gs.drawImage(logo, x, y, widthLogo, heightLogo, null);
            gs.dispose();
            bufImg.flush();

            // 生成二维码QRCode图片
            File imgFile = new File(imgPath);
            ImageIO.write(bufImg, "png", imgFile);

        } catch (Exception e)
        {
            e.printStackTrace();
            return -100;
        }

        return 0;
    }





}

class MyQRCodeImage implements QRCodeImage {
    BufferedImage bufImg;

    public MyQRCodeImage(BufferedImage bufImg) {
        this.bufImg = bufImg;
    }

    @Override
    public int getHeight() {
        return bufImg.getHeight();
    }

    @Override
    public int getPixel(int x, int y) {
        return bufImg.getRGB(x, y);
    }

    @Override
    public int getWidth() {
        return bufImg.getWidth();
    }
}