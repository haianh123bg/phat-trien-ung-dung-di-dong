package com.nguyenngochaianh.btl.ui.appplication;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.nguyenngochaianh.btl.R;

import java.util.Calendar;

public class RegisterCourseActivity extends AppCompatActivity {

    private RadioGroup licenseTypeGroup;
    private EditText startDateEditText;
    private Button registerButton, backButton;
    private String selectedLicenseType;
    private String startDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_course);

        licenseTypeGroup = findViewById(R.id.licenseTypeGroup);
        startDateEditText = findViewById(R.id.startDateEditText);
        registerButton = findViewById(R.id.registerButton);
        backButton = findViewById(R.id.backButton);

        // Xử lý chọn ngày
        startDateEditText.setOnClickListener(v -> showDatePicker());

        // Lấy loại bằng lái được chọn
        licenseTypeGroup.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton radioButton = findViewById(checkedId);
            selectedLicenseType = radioButton.getText().toString();
        });

        // Xử lý sự kiện nút Đăng ký
        registerButton.setOnClickListener(v -> {
            if (selectedLicenseType == null || startDate == null) {
                Toast.makeText(this, "Vui lòng chọn loại bằng và ngày bắt đầu học", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
            }
        });

        // Xử lý nút Quay lại
        backButton.setOnClickListener(v -> finish());
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year1, month1, dayOfMonth) -> {
                    startDate = dayOfMonth + "/" + (month1 + 1) + "/" + year1;
                    startDateEditText.setText(startDate);
                }, year, month, day);
        datePickerDialog.show();
    }
}