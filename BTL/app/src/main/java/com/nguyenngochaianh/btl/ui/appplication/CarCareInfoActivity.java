package com.nguyenngochaianh.btl.ui.appplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.nguyenngochaianh.btl.R;
import com.nguyenngochaianh.btl.adapter.CarCareAdapter;
import com.nguyenngochaianh.btl.databinding.ActivityCarCareInfoBinding;
import com.nguyenngochaianh.btl.model.CarCare;
import com.nguyenngochaianh.btl.repository.CarCareRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CarCareInfoActivity extends AppCompatActivity {

    private ActivityCarCareInfoBinding binding;
    private CarCareAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityCarCareInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        adapter = new CarCareAdapter(Collections.emptyList());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);

        binding.backButton.setOnClickListener(v -> {
            getOnBackPressedDispatcher().onBackPressed();
        });

        binding.registerCarCareButton.setOnClickListener(v -> {
            startActivity(new Intent(CarCareInfoActivity.this, CarCareRegistrationActivity.class));
        });

        binding.paymentCarCareButton.setOnClickListener(v -> {
            startActivity(new Intent(CarCareInfoActivity.this, PaymentActivity.class));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        CarCareRepository repository = new CarCareRepository(this);
        List<CarCare> carCares = repository.getAllCarCares();
        if (carCares.isEmpty()) {
            binding.layoutEmpty.setVisibility(View.VISIBLE);
            binding.recyclerView.setVisibility(View.GONE);
        } else {
            binding.recyclerView.setVisibility(View.VISIBLE);
            binding.layoutEmpty.setVisibility(View.GONE);
        }
        adapter.setDataSource(carCares);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}