package com.Karse.event.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class MailUtils {
    //�ʼ�������������ַ
//  private static String HOST="localhost";
    private static String HOST="smtp.qq.com";
    //�ʺ�
//  private static String ACCOUNT = "zzzgdx@zgd.com";
    private static String ACCOUNT = "1960073343@qq.com";
    //����
//  private static String PASSWORD = "123";
    private static String PASSWORD = "jkixqtlvhxxbebdh";



    /**
     * @param toUser  �����ʼ���˭
     * @param title   �ʼ��ı��� 
     * @param emailMsg  �ʼ���Ϣ
     */
    public static void sendMail(String toUser,String title, String emailMsg)throws AddressException, MessagingException {
        // 1.����һ���������ʼ��������Ự���� Session
        Properties props = new Properties();
        //���÷��͵�Э��
        props.setProperty("mail.transport.protocol", "SMTP");

        //���÷����ʼ��ķ�����
        props.setProperty("mail.host", HOST);
        props.setProperty("mail.smtp.auth", "true");// ָ����֤Ϊtrue

        // ������֤��
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                //���÷����˵��ʺź�����      �ʺ�          ��Ȩ��
                return new PasswordAuthentication(ACCOUNT, PASSWORD);
            }
        };
        //�Ự
        Session session = Session.getInstance(props, auth);

        // 2.����һ��Message�����൱�����ʼ�����
        Message message = new MimeMessage(session);

        //���÷�����
        message.setFrom(new InternetAddress(ACCOUNT));

        //���÷��ͷ�ʽ�������
        message.setRecipient(RecipientType.TO, new InternetAddress(toUser)); 

        //�����ʼ�����
        message.setSubject(title);
        // message.setText("����һ�⼤���ʼ�����<a href='#'>���</a>");

        //�����ʼ�����
        message.setContent(emailMsg, "text/html;charset=utf-8");

        // 3.���� Transport���ڽ��ʼ�����
        Transport.send(message);
    }




}

