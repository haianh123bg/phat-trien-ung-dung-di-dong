package com.haianh123.carcare.service.impl;

import com.haianh123.carcare.dto.response.PaymentSlipResponse;
import com.haianh123.carcare.entity.Course;
import com.haianh123.carcare.entity.Payment;
import com.haianh123.carcare.entity.User;
import com.haianh123.carcare.exception.AppException;
import com.haianh123.carcare.exception.ErrorCode;
import com.haianh123.carcare.repository.CourseRepository;
import com.haianh123.carcare.repository.PaymentRepository;
import com.haianh123.carcare.repository.UserRepository;
import com.haianh123.carcare.service.PaymentService;
import com.haianh123.carcare.utils.OrderStatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;

    @Override
    public PaymentSlipResponse createPayment(Integer courseId, String name, String email) {
        Course course = courseRepository.findById(courseId).orElseThrow(
                () -> new AppException(ErrorCode.COURSE_NOT_FOUND)
        );

        User user = userRepository.findByEmail(email).orElseGet(
                () -> userRepository.save(User.builder()
                                .role("USER")
                                .email(email)
                                .name(name)
                        .build())
        );

        Payment payment = Payment.builder()
                .course(course)
                .user(user)
                .amount(course.getPrice())
                .status(OrderStatusEnum.PENDING.name())
                .paymentDate(LocalDate.now())
                .build();

        Payment paymentCreated = paymentRepository.save(payment);

        return PaymentSlipResponse.builder()
                .accountName("NGUYEN NGOC HAI ANH")
                .bankAccount("0000319843565")
                .bankName("Ngân hàng TMCP MB")
                .total(paymentCreated.getAmount())
                .des(paymentCreated.getId().toString())
                .build();
    }
}
