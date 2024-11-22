package com.nguyenngochaianh.btl.ui.appplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.nguyenngochaianh.btl.R;

public class LicenseManagementActivity extends AppCompatActivity {

    private TextView motorbikeLicenseOwner, motorbikeLicenseType, motorbikeLicenseExpiry;
    private TextView carLicenseOwner, carLicenseType, carLicenseExpiry;
    private Button registerButton, backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_license_management);

        // Liên kết các View
        motorbikeLicenseOwner = findViewById(R.id.motorbikeLicenseOwner);
        motorbikeLicenseType = findViewById(R.id.motorbikeLicenseType);
        motorbikeLicenseExpiry = findViewById(R.id.motorbikeLicenseExpiry);
        carLicenseOwner = findViewById(R.id.carLicenseOwner);
        carLicenseType = findViewById(R.id.carLicenseType);
        carLicenseExpiry = findViewById(R.id.carLicenseExpiry);
        registerButton = findViewById(R.id.registerButton);
        backButton = findViewById(R.id.backButton);

        // Giả sử bạn lấy thông tin từ Intent hoặc cơ sở dữ liệu
        motorbikeLicenseOwner.setText("Anh/Chị: Nguyễn Văn A");
        motorbikeLicenseType.setText("Bằng xe máy hiện tại: A1");
        motorbikeLicenseExpiry.setText("Ngày hết hạn: 01/01/2025");

        carLicenseOwner.setText("Anh/Chị: Nguyễn Văn A");
        carLicenseType.setText("Bằng ô tô hiện tại: B2");
        carLicenseExpiry.setText("Ngày hết hạn: 01/01/2024");

        // Sự kiện cho nút Đăng ký
        registerButton.setOnClickListener(v -> {
            Intent intent = new Intent(LicenseManagementActivity.this, RegisterCourseActivity.class);
            startActivity(intent);
        });

        // Sự kiện cho nút Quay lại
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(LicenseManagementActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}