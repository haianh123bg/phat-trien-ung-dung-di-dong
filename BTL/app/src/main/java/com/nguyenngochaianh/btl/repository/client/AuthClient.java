package com.nguyenngochaianh.btl.repository.client;

import com.nguyenngochaianh.btl.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AuthClient {
    // Đăng ký người dùng
    @POST("users/register")
    Call<User> registerUser(@Body User user);

    // Đăng nhập người dùng
    @POST("users/login")
    Call<User> loginUser(@Body User user);

    // Lấy thông tin người dùng
    @GET("users/{id}")
    Call<User> getUserById(@Path("id") int userId);

    // Lấy tất cả người dùng
    @GET("users")
    Call<List<User>> getAllUsers();
}
