package com.nguyenngochaianh.btl.ui.appplication;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nguyenngochaianh.btl.R;
import com.nguyenngochaianh.btl.databinding.ActivityCarCareRegisterBinding;
import com.nguyenngochaianh.btl.model.CarCare;
import com.nguyenngochaianh.btl.repository.CarCareRepository;

import java.util.Calendar;

public class CarCareRegistrationActivity extends AppCompatActivity {

    private ActivityCarCareRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityCarCareRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        binding.cbCarWash.setOnCheckedChangeListener((compoundButton, b) -> {
            binding.tvService.setError(null);
        });
        binding.cbChangeOil.setOnCheckedChangeListener((compoundButton, b) -> {
            binding.tvService.setError(null);
        });
        binding.cbHoldCar.setOnCheckedChangeListener((compoundButton, b) -> {
            binding.tvService.setError(null);
        });

        binding.backButton.setOnClickListener(v -> finish());
        binding.dateEditText.setOnClickListener(v -> showDatePicker());
        binding.registerCarCareButton.setOnClickListener(v -> {
            binding.carIdEditText.setError(null);
            binding.dateEditText.setError(null);
            binding.tvService.setError(null);

            String cardId = binding.carIdEditText.getText().toString();
            if (TextUtils.isEmpty(cardId)) {
                binding.carIdEditText.setError("Vui lòng nhập biển số xe");
                return;
            }
            if (!isValidCard(cardId)) {
                binding.carIdEditText.setError("Biển số xe phải đúng định dạng! Ví dụ: 28H1-12345");
                return;
            }
            String date = binding.dateEditText.getText().toString();
            if (TextUtils.isEmpty(date)) {
                binding.dateEditText.setError("Vui lòng chọn ngày");
                return;
            }
            StringBuilder service = new StringBuilder();
            if (binding.cbCarWash.isChecked()) {
                service.append("WASH");
            }
            if (binding.cbChangeOil.isChecked()) {
                if (service.length() > 0) {
                    service.append(",");
                }
                service.append("OIL");
            }
            if (binding.cbHoldCar.isChecked()) {
                if (service.length() > 0) {
                    service.append(",");
                }
                service.append("HOLD");
            }
            if (service.length() == 0) {
                binding.tvService.setError("Vui lòng chọn dịch vụ");
                return;
            }

            CarCareRepository repository = new CarCareRepository(this);
            if (repository.checkCarProgressing(cardId)) {
                Toast.makeText(this, "Biển số xe " + cardId + " đang trong quá trình xử lý! Không thể đăng ký mới", Toast.LENGTH_SHORT).show();
            } else {
                long result = repository.addCarCare(new CarCare(cardId, "", date, null, "PROCESSING", service.toString()));
                if (result > 0) {
                    Toast.makeText(this, "Đăng ký bảo dưỡng xe thành công", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Đăng ký bảo dưỡng xe thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isValidCard(String cardId) {
        if (TextUtils.isEmpty(cardId)) return false;
        if (cardId.length() != 10) {
            return false;
        }
        if (!cardId.contains("-")) return false;
        String[] s = cardId.split("-");
        if (s[1].length() != 5) {
            return false;
        }
        for (int i = 0; i < s[1].length(); i++) {
            if (!Character.isDigit(s[1].charAt(i))) {
                return false;
            }
        }
        try {
            boolean s0 = Character.isDigit(s[0].charAt(0));
            boolean s1 = Character.isDigit(s[0].charAt(1));
            boolean s2 = Character.isLetter(s[0].charAt(2));
            boolean s3 = Character.isDigit(s[0].charAt(3));
            return s0 && s1 && s2 && s3;
        } catch (Exception ex) {
            return false;
        }
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year1, month1, dayOfMonth) -> {
                    String startDate = dayOfMonth + "/" + (month1 + 1) + "/" + year1;
                    binding.dateEditText.setText(startDate);
                    binding.dateEditText.setError(null);
                }, year, month, day);
        datePickerDialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}