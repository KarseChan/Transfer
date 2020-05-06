package com.Karse.event.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Karse.event.service.UserService;
import com.Karse.event.service.impl.UserServiceImpl;

/**
 * 验证码
 * @author Karse
 *
 */
@WebServlet("/checkCode/*")
public class CheckCodeServlet extends HttpServlet{
		
	 private static final long serialVersionUID = 1L;
	 private static UserService userService = new UserServiceImpl();
	       
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public CheckCodeServlet() {
	        super();
	        // TODO Auto-generated constructor stub
	    }

		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			 //服务器通知浏览器不要缓存
	        response.setHeader("pragma","no-cache");
	        response.setHeader("cache-control","no-cache");
	        response.setHeader("expires","0");

	        int width = 80, height = 20;

	        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

	        //获取画笔
	        Graphics g = image.getGraphics();
	        //设置画笔颜色为灰色
	        g.setColor(Color.GRAY);
	        //填充图片
	        g.fillRect(0,0, width,height);

	        //获取4个随机验证码
	        String checkImageCode = userService.getCheckCode(4);

	        //将验证码放入HttpSession中
	        HttpSession httpSession =  request.getSession();
	        httpSession.setAttribute("checkImageCode",checkImageCode);

	        //设置画笔颜色为黄色
	        g.setColor(Color.YELLOW);
	        //设置字体的小大
	        g.setFont(new Font("黑体",Font.BOLD,20));
	        //向图片上写入验证码
	        g.drawString(checkImageCode,15,15);

	        g.setColor(Color.GREEN);
	        //画干扰线
	        Random random = new Random();
	        for(int k = 0; k<5;k++) {
	            int x1 = random.nextInt(width);
	            int x2 = random.nextInt(width);
	            int y1 = random.nextInt(height);
	            int y2 = random.nextInt(height);
	            g.drawLine(x1,y1,x2,y2);
	        }

	        ImageIO.write(image,"PNG",response.getOutputStream());
		}
		
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			this.doGet(request, response);
		}
}
