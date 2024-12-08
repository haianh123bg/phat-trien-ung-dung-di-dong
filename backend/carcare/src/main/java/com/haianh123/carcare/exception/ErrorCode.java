package com.haianh123.carcare.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {

    UNCATEGORIZED_EXCEPTION(9999, "Lỗi không được phân loại", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHENTICATED(1001, "Chưa đăng nhập", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1002, "Bạn không có quyền truy cập", HttpStatus.FORBIDDEN),
    INVALID_CREDENTIALS(1003, "Tài khoản hoặc email sai", HttpStatus.BAD_REQUEST),
    COURSE_NOT_FOUND(1004, "Khóa học không tồn tại", HttpStatus.NOT_FOUND),
    ;

    ErrorCode(int code, String message, HttpStatusCode status) {
        this.code = code;
        this.originalMessage = message;  // Lưu trữ message ban đầu
        this.message = message;
        this.statusCode = status;
    }

    private int code;
    private String message;
    private HttpStatusCode statusCode;
    private final String originalMessage; // Lưu trữ message gốc (ban đầu)

    // Phương thức để định dạng message và trả về ErrorCode
    public ErrorCode formatMessage(Object... args) {
        this.message = String.format(this.originalMessage, args);  // Luôn sử dụng message gốc để định dạng
        return this;
    }

    public String getFormattedMessage() {
        return this.message;
    }

}
