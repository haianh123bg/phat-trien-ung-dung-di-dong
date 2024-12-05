package com.nguyenngochaianh.btl.ui.appplication;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.nguyenngochaianh.btl.R;
import com.nguyenngochaianh.btl.model.DriverLicense;
import com.nguyenngochaianh.btl.model.User;
import com.nguyenngochaianh.btl.repository.DriverLicensesRepository;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RegisterCourseActivity extends AppCompatActivity {

    private RadioGroup licenseTypeGroup;
    private EditText startDateEditText;
    private Button registerButton, backButton;
    private String selectedLicenseType;
    private String startDate;
    private String userID;
    List<DriverLicense> listLicense;
    private DriverLicensesRepository driverLicensesRepos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_course);
        userID = getIntent().getStringExtra("userObject");

        driverLicensesRepos = new DriverLicensesRepository(this);
        driverLicensesRepos.open();
        listLicense = driverLicensesRepos.getDriverLicensesByUserId(Integer.parseInt(userID));
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
                boolean isAlready = false;
                for(DriverLicense license : listLicense){
                    if(license.getCategory().equals(selectedLicenseType)){
                        isAlready = true;
                    }
                }
                if(!isAlready){
                    showCustomDialog();
                }else{
                    showCustomDialogNotice();
                }

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

    private void showCustomDialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dl_confirm_signup_license, null);
        Button btnYes = dialogView.findViewById(R.id.btn_yes);
        Button btnNo = dialogView.findViewById(R.id.btn_no);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);

        AlertDialog dialog = builder.create();

        btnYes.setOnClickListener(v -> {
            signUpLicense();
            dialog.dismiss();
            showCustomDialogConfirm();
        });

        btnNo.setOnClickListener(v -> {
            dialog.dismiss();
        });
        dialog.show();
    }
    private void showCustomDialogConfirm() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dl_notify, null);

        Button btnYes = dialogView.findViewById(R.id.btn_confirm);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);

        AlertDialog dialog = builder.create();

        btnYes.setOnClickListener(v -> {
            dialog.dismiss();
            finish();
        });
        dialog.show();
    }
    private void showCustomDialogNotice() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dl_notify, null);

        Button btnYes = dialogView.findViewById(R.id.btn_confirm);
        TextView textView = dialogView.findViewById(R.id.txtv_notice_title);
        TextView txtContent = dialogView.findViewById(R.id.txtv_notify_content);
        txtContent.setText("Bạn đã có bằng "+ selectedLicenseType + ", không được đăng ký tiếp");
        textView.setTextColor(Color.parseColor("#F44336"));

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);

        AlertDialog dialog = builder.create();

        btnYes.setOnClickListener(v -> {
            dialog.dismiss();
        });
        dialog.show();
    }

    private void signUpLicense(){
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HHmmss");
        String id = formatter.format(now);

        DriverLicense driverLicense = new DriverLicense(
                Integer.parseInt(id),
                Integer.parseInt(userID), id, selectedLicenseType.toString(), convertDateFormat(startDate),
                convertDateFormat("11/09/2027"),
                "ACTIVE"
                );
        driverLicensesRepos.addDriverLicense(driverLicense);
    }
    private String convertDateFormat(String dateStr) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date date = inputFormat.parse(dateStr);
            return outputFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}