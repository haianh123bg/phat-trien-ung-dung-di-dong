package com.nguyenngochaianh.btl.ui.appplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.nguyenngochaianh.btl.R;
import com.nguyenngochaianh.btl.model.DriverLicense;
import com.nguyenngochaianh.btl.model.User;
import com.nguyenngochaianh.btl.repository.DriverLicensesRepository;
import com.nguyenngochaianh.btl.repository.UserRepository;

import java.util.List;

public class LicenseManagementActivity extends AppCompatActivity {

    private TextView motorbikeLicenseOwner, motorbikeLicenseType, motorbikeLicenseExpiry;
    private TextView carLicenseOwner, carLicenseType, carLicenseExpiry;
    private Button registerButton, backButton;
    private DriverLicensesRepository driverLicensesRepos;
    List<DriverLicense> listLicense;
    DriverLicense licenseMoto;
    DriverLicense licenseCar;
    private User currentUser;
    private UserRepository userRepository;

    @Override
    protected void onResume() {
        super.onResume();

        // Lấy lại thông tin mới nhất về giấy phép
        listLicense = driverLicensesRepos.getDriverLicensesByUserId(currentUser.getUserId());
        licenseMoto = null;
        licenseCar = null;

        // Lặp lại quá trình phân loại giấy phép như lúc ban đầu
        for (DriverLicense license : listLicense) {
            String category = license.getCategory();
            if (category.equals("A2")) {
                licenseMoto = license;
            } else if (category.equals("A1") && licenseMoto == null) {
                licenseMoto = license;
            }
            if (category.equals("B2")) {
                licenseCar = license;
            } else if (category.equals("B1") && licenseCar == null) {
                licenseCar = license;
            }
        }

        // Cập nhật lại thông tin trên giao diện
        motorbikeLicenseOwner.setText("Anh/Chị: " + currentUser.getName());
        motorbikeLicenseType.setText("Bằng xe máy hiện tại: " + (licenseMoto == null ? "Chưa có" : licenseMoto.getCategory()));
        motorbikeLicenseExpiry.setText("Ngày hết hạn: " + (licenseMoto == null ? "Chưa có" : licenseMoto.getExpiryDate()));

        carLicenseOwner.setText("Anh/Chị: " + currentUser.getName());
        carLicenseType.setText("Bằng ô tô hiện tại: " + (licenseCar == null ? "Chưa có" : licenseCar.getCategory()));
        carLicenseExpiry.setText("Ngày hết hạn: " + (licenseCar == null ? "Chưa có" : licenseCar.getExpiryDate()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_license_management);
        driverLicensesRepos = new DriverLicensesRepository(this);
        driverLicensesRepos.open();
        userRepository = new UserRepository(this);
        userRepository.open();

        // Lấy email người dùng từ Intent
        String email = getIntent().getStringExtra("email");
        currentUser = userRepository.getUserByEmail(email);

        // Liên kết các View
        motorbikeLicenseOwner = findViewById(R.id.motorbikeLicenseOwner);
        motorbikeLicenseType = findViewById(R.id.motorbikeLicenseType);
        motorbikeLicenseExpiry = findViewById(R.id.motorbikeLicenseExpiry);
        carLicenseOwner = findViewById(R.id.carLicenseOwner);
        carLicenseType = findViewById(R.id.carLicenseType);
        carLicenseExpiry = findViewById(R.id.carLicenseExpiry);
        registerButton = findViewById(R.id.registerButton);
        backButton = findViewById(R.id.backButton);

        // Cập nhật giao diện ban đầu
        onResume();  // Gọi onResume để cập nhật giao diện ngay khi Activity được tạo

        // Sự kiện cho nút Đăng ký
        registerButton.setOnClickListener(v -> {
            Intent intent = new Intent(LicenseManagementActivity.this, RegisterCourseActivity.class);
            intent.putExtra("userObject", String.valueOf(currentUser.getUserId()));
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