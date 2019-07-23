package controller;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet(urlPatterns = "/verificationCode")
public class VerificationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //生成验证码
        //1、准备1万张图片
        //2、画一张图片
        int width = 100;
        int height = 50;

        //1.创建一对象，在内存中图片(验证码图片对象)
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        //2.美化图片
        //2.1 填充背景色
        Graphics g = image.getGraphics();//画笔对象
        g.setColor(Color.PINK);//设置画笔颜色
        g.fillRect(0,0,width,height);

        //2.2画边框
        g.setColor(Color.BLUE);
        Font font = new Font("Arial",Font.BOLD,16);
        g.drawRect(0,0,width-1 ,height-1);
        g.setFont(font);

        //字典
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder randomStr = new StringBuilder();

        for(byte i=1;i<=4;i++){
            //2.3写字
            int index = random.nextInt(str.length());
            char ch = str.charAt(index);
            randomStr.append(ch);
            g.drawString(ch+"",width/5*i,height/2);
        }

        HttpSession session = req.getSession();
        //将我们随机生成的这四个字符存入session域
        session.setAttribute("checkCode",randomStr.toString().toLowerCase());

        Color[] colors = new Color[5];
        colors[0] = Color.BLUE;
        colors[1] = Color.GREEN;
        colors[2] = Color.BLACK;
        colors[3] = Color.YELLOW;
        colors[4] = Color.CYAN;


        for(byte i=1;i<10;i++){
            int colorIndex = random.nextInt(colors.length);
            g.setColor(colors[colorIndex]);
            int x1 = random.nextInt(width);
            int x2 = random.nextInt(width);

            int y1 = random.nextInt(height);
            int y2 = random.nextInt(height);
            g.drawLine(x1,y1,x2,y2);
        }

        //3.将图片输出到页面展示
        ImageIO.write(image,"jpg",resp.getOutputStream());
    }
}
