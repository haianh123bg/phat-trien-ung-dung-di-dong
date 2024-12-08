package com.haianh123.carcare.exception;

import com.redon_agency.chatbot.dto.response.ApiResponse;
import com.redon_agency.chatbot.utils.EnumClient;
import com.redon_agency.chatbot.utils.TypeChartEInvoice;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handlingAppException(AppException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());

        return ResponseEntity.status(404).body(apiResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException caught: ", e);

        Map<String, String> errors = new HashMap<>();

        e.getBindingResult().getAllErrors().forEach((error) -> {
            // Kiểm tra xem lỗi có phải là lỗi của trường (FieldError) hay lỗi của toàn đối tượng
            if (error instanceof FieldError) {
                // Nếu lỗi là của một trường cụ thể
                String fieldName = ((FieldError) error).getField();
                String message = error.getDefaultMessage();
                errors.put(fieldName, message);
            } else {
                // Lỗi toàn đối tượng (ví dụ: lỗi PasswordMatches)
                String objectName = error.getObjectName();
                String message = error.getDefaultMessage();
                errors.put(objectName, message);
            }
        });

        return ResponseEntity.badRequest().body(ApiResponse.<Map<String, String>>builder()
                .code(HttpStatus.BAD_REQUEST.value())  // Mã lỗi 400 Bad Request
                .message("Xác thực không thành công")  // Thông báo lỗi tổng quát
                .result(errors)  // Trả về danh sách lỗi chi tiết
                .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> exception(RuntimeException e) {
        log.error("Unhandled exception caught: ", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.builder()
                .code(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode())
                .message(e.getMessage())
                .build());
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<ApiResponse> handlingAccessDeniedException(AccessDeniedException exception) {
        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;

        return ResponseEntity.status(errorCode.getStatusCode())
                .body(ApiResponse.builder()
                        .code(errorCode.getCode())
                        .message(errorCode.getMessage())
                        .build());
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ApiResponse> handleFeignException(FeignException e) {
        ErrorCode errorCode = ErrorCode.UNCATEGORIZED_EXCEPTION; // Default error code
        switch (e.status()) {
            case 551:
                errorCode = ErrorCode.UNAUTHORIZED;
                break;
            case 100:
                errorCode = ErrorCode.BAD_REQUEST;
                break;
            case 2022:
                errorCode = ErrorCode.FACEBOOK_AUTHENTICATION_FAIL;
                break;
            case 613:
                errorCode = ErrorCode.UNCATEGORIZED_EXCEPTION;
                break;
            case 10903:
                errorCode = ErrorCode.ACCESS_DENIED; // Truy cập bị từ chối
                break;
            case 200:
                errorCode = ErrorCode.UNAUTHORIZED; // Không có quyền truy cập
                break;
            case 190:
                errorCode = ErrorCode.INVALID_CREDENTIALS; // Thông tin đăng nhập không hợp lệ
                break;
            case 10900:
                errorCode = ErrorCode.CHAT_BOT_NOT_EXIST; // Chatbot không tồn tại
                break;
            case 10901:
                errorCode = ErrorCode.INVALID_USER_ID; // ID người dùng không hợp lệ
                break;
            case 194:
                errorCode = ErrorCode.VERIFY_CODE_INVALID; // Mã xác thực không hợp lệ
                break;
            default:
                errorCode = ErrorCode.UNCATEGORIZED_EXCEPTION; // Lỗi không được phân loại
                break;
        }
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());

        return ResponseEntity.status(errorCode.getStatusCode()).body(apiResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("JSON parse error: ", e);
        // Xây dựng phản hồi tùy chỉnh cho lỗi phân tích JSON không hợp lệ
        ApiResponse apiResponse = ApiResponse.builder()
                .code(9999)  // Mã lỗi tùy chỉnh
                .message("Yêu cầu không đúng định dạng")  // Thông báo lỗi chi tiết
                .build();

        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiResponse> handleNotFoundException(NoHandlerFoundException ex) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Đường dẫn không tồn tại: " + ex.getRequestURL());
        apiResponse.setCode(404);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @ExceptionHandler(AppResponse.class)
    public ResponseEntity<Object> handleAppResponse(AppResponse ex) {
        // Tạo cấu trúc phản hồi dựa trên AppResponse
        Map<String, Object> response = new HashMap<>();
        response.put("code", ex.getAppCode().getCode());
        response.put("message", ex.getAppCode().getMessage());

        Object result = ex.getAppCode().getAdditionalData();
        if (result != null) {
            response.put("result", result); // Thêm dữ liệu bổ sung nếu có
        }

        // Trả về phản hồi với trạng thái HTTP tương ứng
        return new ResponseEntity<>(response, ex.getAppCode().getHttpStatus());
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<String> handleMissingRequestPart(MissingServletRequestPartException ex) {
        return new ResponseEntity<>("Thiếu part: " + ex.getRequestPartName(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Map<String, String>> handleMissingParams(MissingServletRequestParameterException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "Bad Request");
        errorResponse.put("message", "Required parameter '" + ex.getParameterName() + "' is not present.");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ClientException.class)
    public ResponseEntity<ApiResponse> handleClientException(ClientException ex) {
        FeignException feignException = ex.getFeignException();
        EnumClient typeClient = ex.getClient();
        ErrorCode errorCode = null;
        switch (typeClient) {
            case MAT_BAO: {
                log.error("MAT_BAO feign client error: ", feignException);
                errorCode = errorMatBao(feignException.status());
                break;
            }
            case GOOGLE: {
                log.error("GOOGLE feign client error: ", feignException);

            }
            case OPEN_AI: {
                log.error("OPEN_AI feign client error: ", feignException);
            }
            case FACEBOOK: {
                log.error("FACEBOOK feign client error: ", feignException);
            }
            case SEPAY_HUB: {
                log.error("SEPAY_HUB feign client error: ", feignException);
            }
            default: {
                log.error("Feign Client error: ", feignException);
            }

        }
        assert errorCode != null;
        return ResponseEntity.status(errorCode.getStatusCode()).body(ApiResponse.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build());
    }

    // Exception handler cho MethodArgumentTypeMismatchException
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleEnumTypeMismatch(MethodArgumentTypeMismatchException ex) {
        if (ex.getRequiredType() == TypeChartEInvoice.class) {
            ErrorCode errorCode = ErrorCode.INVALID_ENUM;
            return ResponseEntity.status(errorCode.getStatusCode()).body(errorCode.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    private ErrorCode errorMatBao(Integer statusCode) {
        return switch (statusCode) {
            case 400 -> ErrorCode.BAD_REQUEST;
            case 401 -> ErrorCode.UNAUTHORIZED;
            case 403 -> ErrorCode.UNFINISHED_PREVIOUS_STEP;
            case 404 -> ErrorCode.FACEBOOK_AUTHENTICATION_FAIL;
            case 415 -> ErrorCode.UNSUPPORTED_MEDIA_TYPE;
            case 500 -> ErrorCode.TWO_FA_NOT_EXISTED;
            default -> ErrorCode.UNCATEGORIZED_EXCEPTION;
        };
    }

    private ErrorCode errorSePayHub(Integer statusCode) {
        return switch (statusCode) {
            case 401 -> ErrorCode.UNAUTHORIZED;
            case 500 -> ErrorCode.UNAUTHORIZED;
            default -> ErrorCode.UNAUTHORIZED;
        };
    }
}
