package com.nguyenngochaianh.btl.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.nguyenngochaianh.btl.db.DatabaseHelper;
import com.nguyenngochaianh.btl.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public UserRepository(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    // Mở kết nối với cơ sở dữ liệu
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    // Đóng kết nối cơ sở dữ liệu
    public void close() {
        dbHelper.close();
    }

    // Phương thức thêm người dùng mới vào cơ sở dữ liệu
    public long addUser(User user) {
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("email", user.getEmail());
        values.put("password", user.getPassword());
        values.put("role", user.getRole());

        return database.insert("Users", null, values);
    }

    // Phương thức lấy tất cả người dùng
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Cursor cursor = database.query("Users", null, null, null, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    User user = cursorToUser(cursor);
                    users.add(user);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        return users;
    }

    // Phương thức lấy người dùng theo ID
    public User getUserById(int userId) {
        Cursor cursor = database.query("Users", null, "user_id = ?", new String[]{String.valueOf(userId)}, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                User user = cursorToUser(cursor);
                cursor.close();
                return user;
            }
        }

        return null;
    }

    // Phương thức lấy người dùng theo email
    public User getUserByEmail(String email) {
        Cursor cursor = database.query("Users", null, "email = ?", new String[]{email}, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                User user = cursorToUser(cursor);
                cursor.close();
                return user;
            }
        }

        return null;
    }

    // Phương thức cập nhật thông tin người dùng
    public int updateUser(User user) {
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("email", user.getEmail());
        values.put("password", user.getPassword());
        values.put("phone", user.getPhone());
        values.put("role", user.getRole());

        return database.update("Users", values, "user_id = ?", new String[]{String.valueOf(user.getUserId())});
    }

    // Phương thức xóa người dùng theo ID
    public void deleteUser(int userId) {
        database.delete("Users", "user_id = ?", new String[]{String.valueOf(userId)});
    }

    // Phương thức chuyển con trỏ cursor thành đối tượng User
    private User cursorToUser(Cursor cursor) {
        int userId = cursor.getInt(0);
        String name = cursor.getString(1);
        String email = cursor.getString(2);
        String password = cursor.getString(3);
        String phone = cursor.getString(4);
        String role = cursor.getString(5);

        return new User(userId, name, email, password, phone, role);
    }
}
