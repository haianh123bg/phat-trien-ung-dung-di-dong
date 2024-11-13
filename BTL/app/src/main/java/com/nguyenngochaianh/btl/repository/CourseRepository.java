package com.nguyenngochaianh.btl.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.nguyenngochaianh.btl.db.DatabaseHelper;
import com.nguyenngochaianh.btl.model.Course;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CourseRepository {

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public CourseRepository(Context context) {
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

    // Phương thức thêm khóa học mới vào cơ sở dữ liệu
    public long addCourse(Course course) {
        ContentValues values = new ContentValues();
        values.put("name", course.getName());
        values.put("description", course.getDescription());
        values.put("duration", course.getDuration());
        values.put("price", course.getPrice());
        values.put("start_date", course.getStartDate().toString());
        values.put("end_date", course.getEndDate().toString());

        return database.insert("Courses", null, values);
    }

    // Phương thức lấy thông tin tất cả khóa học
    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        Cursor cursor = database.query("Courses", null, null, null, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    Course course = cursorToCourse(cursor);
                    courses.add(course);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        return courses;
    }

    // Phương thức lấy thông tin một khóa học theo ID
    public Course getCourseById(int courseId) {
        Cursor cursor = database.query("Courses", null, "course_id = ?", new String[]{String.valueOf(courseId)}, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                Course course = cursorToCourse(cursor);
                cursor.close();
                return course;
            }
        }

        return null;
    }

    // Phương thức cập nhật thông tin khóa học
    public int updateCourse(Course course) {
        ContentValues values = new ContentValues();
        values.put("name", course.getName());
        values.put("description", course.getDescription());
        values.put("duration", course.getDuration());
        values.put("price", course.getPrice());
        values.put("start_date", course.getStartDate().toString());
        values.put("end_date", course.getEndDate().toString());

        return database.update("Courses", values, "course_id = ?", new String[]{String.valueOf(course.getCourseId())});
    }

    // Phương thức xóa khóa học theo ID
    public void deleteCourse(int courseId) {
        database.delete("Courses", "course_id = ?", new String[]{String.valueOf(courseId)});
    }

    // Phương thức chuyển con trỏ cursor thành đối tượng Course
    private Course cursorToCourse(Cursor cursor) {
        int courseId = cursor.getInt(0);
        String name = cursor.getString(1);
        String description = cursor.getString(2);
        String duration = cursor.getString(3);
        double price = cursor.getDouble(4);
        Date startDate = new Date(cursor.getString(5));
        Date endDate = new Date(cursor.getString(6));

        return new Course(courseId, name, description, duration, price, startDate, endDate);
    }
}
