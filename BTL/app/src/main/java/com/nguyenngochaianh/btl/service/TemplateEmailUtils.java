package com.nguyenngochaianh.btl.service;

public class TemplateEmailUtils {

    /**
     * Tạo nội dung email chào mừng người dùng mới với thông tin người dùng
     *
     * @param userName:    tên người dùng
     * @param email:       email của người dùng
     * @return: chuỗi HTML định dạng email chào mừng
     */
    public static String generateWelcomeEmailContent(String userName, String email) {
        return "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<style>"
                + "body {font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f4;}"
                + ".container {max-width: 600px; margin: 20px auto; background-color: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);}"
                + ".header {background-color: #28a745; color: white; padding: 10px 20px; text-align: center; border-top-left-radius: 8px; border-top-right-radius: 8px;}"
                + ".content {margin: 20px; text-align: center;}"
                + ".content p {font-size: 16px; line-height: 1.5;}"
                + ".user-info {font-size: 18px; font-weight: bold; color: #007bff;}"
                + ".footer {background-color: #f1f1f1; text-align: center; padding: 10px; border-bottom-left-radius: 8px; border-bottom-right-radius: 8px;}"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<div class='container'>"
                + "<div class='header'><h1>Chào Mừng, " + userName + "!</h1></div>"
                + "<div class='content'>"
                + "<p>Chúng tôi rất vui khi bạn trở thành một phần của cộng đồng chúng tôi!</p>"
                + "<p>Dưới đây là thông tin liên hệ của bạn:</p>"
                + "<p class='user-info'>Email: " + email + "</p>"
                + "<p>Nếu bạn có bất kỳ thắc mắc hoặc yêu cầu nào, xin vui lòng liên hệ với chúng tôi.</p>"
                + "</div>"
                + "<div class='footer'><p>Liên hệ với chúng tôi: support@example.com</p></div>"
                + "</div>"
                + "</body>"
                + "</html>";
    }
}
