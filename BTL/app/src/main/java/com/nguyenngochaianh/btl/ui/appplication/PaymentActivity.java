package com.nguyenngochaianh.btl.ui.appplication;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.nguyenngochaianh.btl.R;

public class PaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button backButton = findViewById(R.id.btn_back);
        Button submitButton = findViewById(R.id.btn_submit_payment);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        // URL của mã QR code
        String qrCodeUrl = "https://qr.sepay.vn/img?bank=BIDV&acc=964516588REDAI38386&template=compact&amount=10000,00&des=DH1";

        // ImageView nơi hiển thị QR code
        ImageView qrCodeImageView = findViewById(R.id.img_qr_code);

        // ProgressBar hiển thị trong lúc tải
        ProgressBar progressBar = findViewById(R.id.progress_bar);

        // Hiển thị ProgressBar trước khi bắt đầu tải
        progressBar.setVisibility(View.VISIBLE);
        qrCodeImageView.setVisibility(View.GONE);

        // Sử dụng Glide để tải ảnh QR code
        Glide.with(this)
                .load(qrCodeUrl) // URL mã QR
                .placeholder(R.drawable.qr) // Ảnh tạm trong lúc chờ tải
                .error(R.drawable.qr) // Ảnh hiển thị khi xảy ra lỗi
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, com.bumptech.glide.request.target.Target<Drawable> target, boolean isFirstResource) {
                        // Ẩn ProgressBar và hiển thị ảnh lỗi nếu tải thất bại
                        progressBar.setVisibility(View.GONE);
                        qrCodeImageView.setVisibility(View.VISIBLE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, com.bumptech.glide.request.target.Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        // Ẩn ProgressBar và hiển thị ảnh khi tải thành công
                        progressBar.setVisibility(View.GONE);
                        qrCodeImageView.setVisibility(View.VISIBLE);
                        return false;
                    }
                })
                .into(qrCodeImageView);

        // Sự kiện cho nút Quay lại
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(PaymentActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Thông báo");
        alertDialogBuilder.setMessage("Thanh toán thành công");
        alertDialogBuilder.setPositiveButton("OK", (dialog, which) -> {
            // Handle OK button click
        });
        AlertDialog alertDialog = alertDialogBuilder.create();


        // Sự kiện cho nút Xác nhận thanh toán hiển thị thông báo thanh toán thành công
        submitButton.setOnClickListener(v -> {
            alertDialog.show();
        });
    }

}
