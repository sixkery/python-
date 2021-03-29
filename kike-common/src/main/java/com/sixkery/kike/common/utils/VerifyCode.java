package com.common.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * @author sixkery
 * @date 2020/5/4
 * <p>
 * 验证码工具类
 */
public class  VerifyCode {
    /**
     * 生成验证码图片的宽度
     */
    private int width = 100;
    /**
     * 生成验证码图片的高度
     */
    private int height = 50;
    private String[] fontNames = {"宋体", "楷体", "隶书", "微软雅黑"};
    private Color bgColor = new Color(255, 255, 255);
    /**
     * 定义验证码图片的背景颜色为白色
     */
    private Random random = new Random();
    /**
     * 记录随机字符串
     */
    private String text;

    /**
     * 获取一个随意颜色
     *
     * @return 随机的颜色
     */
    private Color randomColor() {

        int red = random.nextInt(150);
        int green = random.nextInt(150);
        int blue = random.nextInt(150);
        return new Color(red, green, blue);
    }

    /**
     * 获取一个随机字体
     *
     * @return 字体
     */
    private Font randomFont() {
        String name = fontNames[random.nextInt(fontNames.length)];
        int style = random.nextInt(4);
        int size = random.nextInt(5) + 24;
        return new Font(name, style, size);
    }

    /**
     * 获取一个随机字符
     *
     * @return 随机字符
     */
    private char randomChar() {
        String codes = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return codes.charAt(random.nextInt(codes.length()));
    }

    /**
     * 创建一个空白的BufferedImage对象
     *
     * @return BufferedImage对象
     */
    private BufferedImage createImage() {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        // 设置验证码图片的背景颜色
        g2.setColor(bgColor);
        g2.fillRect(0, 0, width, height);
        return image;
    }

    /**
     * 获取图像
     *
     * @return BufferedImage对象
     */
    public BufferedImage getImage() {
        BufferedImage image = createImage();
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            String s = randomChar() + "";
            sb.append(s);
            g2.setColor(randomColor());
            g2.setFont(randomFont());
            float x = i * width * 1.0f / 4;
            g2.drawString(s, x, height - 15);
        }
        this.text = sb.toString();
        drawLine(image);
        return image;
    }

    /**
     * 绘制干扰线
     *
     * @param image 图像
     */
    private void drawLine(BufferedImage image) {
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        int num = 5;
        for (int i = 0; i < num; i++) {
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int x2 = random.nextInt(width);
            int y2 = random.nextInt(height);
            g2.setColor(randomColor());
            g2.setStroke(new BasicStroke(1.5f));
            g2.drawLine(x1, y1, x2, y2);
        }
    }

    public String getText() {
        return text;
    }

    public static void output(BufferedImage image, OutputStream out) throws IOException {
        ImageIO.write(image, "JPEG", out);
    }
}
