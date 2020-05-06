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
 * ��֤��
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
			 //������֪ͨ�������Ҫ����
	        response.setHeader("pragma","no-cache");
	        response.setHeader("cache-control","no-cache");
	        response.setHeader("expires","0");

	        int width = 80, height = 20;

	        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

	        //��ȡ����
	        Graphics g = image.getGraphics();
	        //���û�����ɫΪ��ɫ
	        g.setColor(Color.GRAY);
	        //���ͼƬ
	        g.fillRect(0,0, width,height);

	        //��ȡ4�������֤��
	        String checkImageCode = userService.getCheckCode(4);

	        //����֤�����HttpSession��
	        HttpSession httpSession =  request.getSession();
	        httpSession.setAttribute("checkImageCode",checkImageCode);

	        //���û�����ɫΪ��ɫ
	        g.setColor(Color.YELLOW);
	        //���������С��
	        g.setFont(new Font("����",Font.BOLD,20));
	        //��ͼƬ��д����֤��
	        g.drawString(checkImageCode,15,15);

	        g.setColor(Color.GREEN);
	        //��������
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
