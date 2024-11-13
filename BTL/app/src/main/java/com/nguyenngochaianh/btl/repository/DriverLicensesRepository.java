package com.nguyenngochaianh.btl.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.nguyenngochaianh.btl.db.DatabaseHelper;
import com.nguyenngochaianh.btl.model.DriverLicense;

import java.util.ArrayList;
import java.util.List;

public class DriverLicensesRepository {

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public DriverLicensesRepository(Context context) {
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

    // Phương thức thêm bằng lái mới vào cơ sở dữ liệu
    public long addDriverLicense(DriverLicense license) {
        ContentValues values = new ContentValues();
        values.put("user_id", license.getUserId());
        values.put("license_number", license.getLicenseNumber());
        values.put("category", license.getCategory());
        values.put("issue_date", license.getIssueDate());
        values.put("expiry_date", license.getExpiryDate());
        values.put("status", license.getStatus());

        return database.insert("DriverLicenses", null, values);
    }

    // Phương thức lấy tất cả bằng lái
    public List<DriverLicense> getAllDriverLicenses() {
        List<DriverLicense> licenses = new ArrayList<>();
        Cursor cursor = database.query("DriverLicenses", null, null, null, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    DriverLicense license = cursorToDriverLicense(cursor);
                    licenses.add(license);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        return licenses;
    }

    // Phương thức lấy bằng lái theo ID
    public DriverLicense getDriverLicenseById(int licenseId) {
        Cursor cursor = database.query("DriverLicenses", null, "license_id = ?", new String[]{String.valueOf(licenseId)}, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                DriverLicense license = cursorToDriverLicense(cursor);
                cursor.close();
                return license;
            }
        }

        return null;
    }

    // Phương thức lấy bằng lái theo user_id
    public List<DriverLicense> getDriverLicensesByUserId(int userId) {
        List<DriverLicense> licenses = new ArrayList<>();
        Cursor cursor = database.query("DriverLicenses", null, "user_id = ?", new String[]{String.valueOf(userId)}, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    DriverLicense license = cursorToDriverLicense(cursor);
                    licenses.add(license);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        return licenses;
    }

    // Phương thức cập nhật thông tin bằng lái
    public int updateDriverLicense(DriverLicense license) {
        ContentValues values = new ContentValues();
        values.put("license_number", license.getLicenseNumber());
        values.put("category", license.getCategory());
        values.put("issue_date", license.getIssueDate());
        values.put("expiry_date", license.getExpiryDate());
        values.put("status", license.getStatus());

        return database.update("DriverLicenses", values, "license_id = ?", new String[]{String.valueOf(license.getLicenseId())});
    }

    // Phương thức xóa bằng lái theo ID
    public void deleteDriverLicense(int licenseId) {
        database.delete("DriverLicenses", "license_id = ?", new String[]{String.valueOf(licenseId)});
    }

    // Phương thức chuyển con trỏ cursor thành đối tượng DriverLicense
    private DriverLicense cursorToDriverLicense(Cursor cursor) {
        int licenseId = cursor.getInt(0);
        int userId = cursor.getInt(1);
        String licenseNumber = cursor.getString(2);
        String category = cursor.getString(3);
        String issueDate = cursor.getString(4);
        String expiryDate = cursor.getString(5);
        String status = cursor.getString(6);

        return new DriverLicense(licenseId, userId, licenseNumber, category, issueDate, expiryDate, status);
    }
}
