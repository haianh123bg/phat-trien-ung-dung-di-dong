package com.nguyenngochaianh.btl.service;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.AuthenticationFailedException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailService {
    // Gmail SMTP server thông qua SSL
    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";

    // Địa chỉ và mật khẩu Gmail (nên lấy thông tin này từ môi trường an toàn, không hard-code)
    private static final String USERNAME = "nguyenngochaianh33@gmail.com";
    private static final String PASSWORD = "gnsn tydz vhwl cbun";

    private static ExecutorService executorService = Executors.newSingleThreadExecutor(); // Executor service to run tasks in background

    public static void sendEmail(final String recipient, final String subject, final String body) {
        // Run the email sending task in a background thread
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    // Cấu hình properties của mail server
                    Properties properties = new Properties();
                    properties.put("mail.smtp.host", SMTP_HOST);
                    properties.put("mail.smtp.port", SMTP_PORT);
                    properties.put("mail.smtp.auth", "true");
                    properties.put("mail.smtp.socketFactory.port", SMTP_PORT);
                    properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                    properties.put("mail.smtp.socketFactory.fallback", "false");

                    // Khởi tạo phiên làm việc mail với tên người dùng và mật khẩu
                    Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(USERNAME, PASSWORD);
                        }
                    });

                    // Tạo một message email
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(USERNAME));
                    message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
                    message.setSubject(subject);
                    message.setText(body);

                    // Gửi email
                    Transport.send(message);
                    Log.d("EmailService", "Email sent successfully to " + recipient);

                    // Post the result back to the main thread
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            // You can update UI here if needed
                        }
                    });

                } catch (AuthenticationFailedException e) {
                    Log.e("EmailService", "Authentication failed: " + e.getMessage());
                } catch (MessagingException e) {
                    Log.e("EmailService", "Error in sending email: " + e.getMessage());
                }
            }
        });
    }
}
