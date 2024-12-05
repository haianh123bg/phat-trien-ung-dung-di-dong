package com.nguyenngochaianh.btl.ui.appplication;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.nguyenngochaianh.btl.R;

import java.lang.annotation.Target;

public class PaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        // URL của mã QR code
        String qrCodeUrl = "https://qr.sepay.vn/img?bank=BIDV&acc=964516588REDAI38386&template=compact&amount=10000,00&des=DH1";

        // ImageView nơi hiển thị QR code
        ImageView qrCodeImageView = findViewById(R.id.img_qr_code);

        // ProgressBar hiển thị trong lúc tải
        ProgressBar progressBar = findViewById(R.id.);

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
    }
}