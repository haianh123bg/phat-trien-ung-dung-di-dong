package com.nguyenngochaianh.btl.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nguyenngochaianh.btl.R;
import com.nguyenngochaianh.btl.model.User;
import com.nguyenngochaianh.btl.repository.UserRepository;
import com.nguyenngochaianh.btl.service.EmailService;
import com.nguyenngochaianh.btl.service.TemplateEmailUtils;

public class RegisterActivity extends AppCompatActivity {

    private EditText usernameEditText, emailEditText, passwordEditText, confirmPasswordEditText;
    private UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userRepository = new UserRepository(this);
        userRepository.open();

        // Liên kết các View
        usernameEditText = findViewById(R.id.usernameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
        Button registerButton = findViewById(R.id.registerButton);
        TextView loginTextView = findViewById(R.id.loginTextView);

        // Xử lý sự kiện nhấn nút đăng ký

        registerButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            String confirmPassword = confirmPasswordEditText.getText().toString();

            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirmPassword)) {
                Toast.makeText(this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                return;
            }

            if (userRepository.getUserByEmail(email) != null) {
                Toast.makeText(this, "Email đã tồn tại", Toast.LENGTH_SHORT).show();
                return;
            }

            User user = new User(0, username, email, password, null, "CUSTOMER");
            long result = userRepository.addUser(user);

            if (result != -1) {
                Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                // Gửi email thông báo đăng ký thành công
                EmailService.sendEmail(email, "Đăng ký thành công", TemplateEmailUtils.generateWelcomeEmailContent(username, email));
                // Chuyển sang màn hình đăng nhập sau khi đăng ký thành công
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
            }
        });

        // Chuyển sang màn hình đăng nhập khi nhấn vào TextView
        loginTextView.setOnClickListener(v -> finish());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        userRepository.close();
    }
}

