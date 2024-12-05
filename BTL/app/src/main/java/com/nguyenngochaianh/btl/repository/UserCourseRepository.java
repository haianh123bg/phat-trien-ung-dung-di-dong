package com.nguyenngochaianh.btl.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.nguyenngochaianh.btl.db.DatabaseHelper;
import com.nguyenngochaianh.btl.model.UserCourse;

import java.util.ArrayList;
import java.util.List;

public class UserCourseRepository {

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public UserCourseRepository(Context context) {
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

    // Phương thức thêm một khóa học vào bảng UserCourse
    public long addUserCourse(UserCourse userCourse) {
        ContentValues values = new ContentValues();
        values.put("user_id", userCourse.getUserId());
        values.put("course_id", userCourse.getCourseId());
        values.put("enrollment_date", userCourse.getEnrollmentDate().toString());
        values.put("status", userCourse.getStatus());

        return database.insert("user_courses", null, values);
    }

    // Phương thức lấy tất cả khóa học của người dùng
    public List<UserCourse> getAllUserCourses() {
        List<UserCourse> userCourses = new ArrayList<>();
        Cursor cursor = database.query("user_courses", null, null, null, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    UserCourse userCourse = cursorToUserCourse(cursor);
                    userCourses.add(userCourse);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        return userCourses;
    }

    // Phương thức lấy khóa học theo ID người dùng
    public List<UserCourse> getUserCoursesByUserId(int userId) {
        List<UserCourse> userCourses = new ArrayList<>();
        Cursor cursor = database.query("user_courses", null, "user_id = ?", new String[]{String.valueOf(userId)}, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    UserCourse userCourse = cursorToUserCourse(cursor);
                    userCourses.add(userCourse);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        return userCourses;
    }

    // Phương thức lấy khóa học theo ID khóa học
    public UserCourse getUserCourseById(int userCourseId) {
        Cursor cursor = database.query("user_courses", null, "user_course_id = ?", new String[]{String.valueOf(userCourseId)}, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                UserCourse userCourse = cursorToUserCourse(cursor);
                cursor.close();
                return userCourse;
            }
        }

        return null;
    }

    // Phương thức cập nhật thông tin khóa học
    public int updateUserCourse(UserCourse userCourse) {
        ContentValues values = new ContentValues();
        values.put("user_id", userCourse.getUserId());
        values.put("course_id", userCourse.getCourseId());
        values.put("enrollment_date", userCourse.getEnrollmentDate().toString());
        values.put("status", userCourse.getStatus());

        return database.update("user_courses", values, "user_course_id = ?", new String[]{String.valueOf(userCourse.getUserCourseId())});
    }

    // Phương thức xóa khóa học theo ID
    public void deleteUserCourse(int userCourseId) {
        database.delete("user_courses", "user_course_id = ?", new String[]{String.valueOf(userCourseId)});
    }

    // Phương thức chuyển con trỏ cursor thành đối tượng UserCourse
    private UserCourse cursorToUserCourse(Cursor cursor) {
        int userCourseId = cursor.getInt(0);
        int userId = cursor.getInt(1);
        int courseId = cursor.getInt(2);
        String enrollmentDate = cursor.getString(3);
        String status = cursor.getString(4);

        return new UserCourse(userCourseId, userId, courseId, new java.util.Date(enrollmentDate), status);
    }
}
