package com.nguyenngochaianh.btl.ui.appplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.nguyenngochaianh.btl.R;
import com.nguyenngochaianh.btl.model.User;
import com.nguyenngochaianh.btl.repository.UserRepository;

public class MainActivity extends AppCompatActivity {

    private TextView emailTextView, ownerTextView;
    private Button manageLicenseButton, carCareInfoButton;
    private UserRepository userRepository;
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Liên kết các view
        emailTextView = findViewById(R.id.emailTextView);
        ownerTextView = findViewById(R.id.ownerTextView);
        manageLicenseButton = findViewById(R.id.manageLicenseButton);
        carCareInfoButton = findViewById(R.id.carCareInfoButton);

        userRepository = new UserRepository(this);
        userRepository.open();

        // Lấy email người dùng từ Intent
        String email = getIntent().getStringExtra("email");
        currentUser = userRepository.getUserByEmail(email);

        // Hiển thị thông tin người dùng
        if (currentUser != null) {
            emailTextView.setText("Tài khoản: " + currentUser.getEmail());
            ownerTextView.setText("Chủ sở hữu: " + currentUser.getName());
        }

        // Sự kiện khi nhấn nút "Quản lý bằng lái"
        manageLicenseButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LicenseManagementActivity.class);
            intent.putExtra("email", currentUser.getEmail());
            startActivity(intent);
        });

        // Sự kiện khi nhấn nút "Thông tin chăm sóc xe"
        carCareInfoButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CarCareInfoActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        userRepository.close();
    }

}