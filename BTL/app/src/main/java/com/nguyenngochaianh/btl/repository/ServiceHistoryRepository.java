package com.nguyenngochaianh.btl.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.nguyenngochaianh.btl.db.DatabaseHelper;
import com.nguyenngochaianh.btl.model.ServiceHistory;

import java.util.ArrayList;
import java.util.List;

public class ServiceHistoryRepository {

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public ServiceHistoryRepository(Context context) {
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

    // Thêm ServiceHistory mới vào cơ sở dữ liệu
    public long addServiceHistory(ServiceHistory serviceHistory) {
        ContentValues values = new ContentValues();
        values.put("car_id", serviceHistory.getCarId());
        values.put("service_id", serviceHistory.getServiceId());
        values.put("service_date", serviceHistory.getServiceDate().toString());
        values.put("notes", serviceHistory.getNotes());

        return database.insert("ServiceHistory", null, values);
    }

    // Lấy tất cả ServiceHistory từ cơ sở dữ liệu
    public List<ServiceHistory> getAllServiceHistory() {
        List<ServiceHistory> serviceHistories = new ArrayList<>();
        Cursor cursor = database.query("ServiceHistory", null, null, null, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    ServiceHistory serviceHistory = cursorToServiceHistory(cursor);
                    serviceHistories.add(serviceHistory);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        return serviceHistories;
    }

    // Lấy ServiceHistory theo carId
    public List<ServiceHistory> getServiceHistoryByCarId(int carId) {
        List<ServiceHistory> serviceHistories = new ArrayList<>();
        Cursor cursor = database.query(
                "ServiceHistory",
                null,
                "car_id = ?",
                new String[]{String.valueOf(carId)},
                null,
                null,
                null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    ServiceHistory serviceHistory = cursorToServiceHistory(cursor);
                    serviceHistories.add(serviceHistory);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        return serviceHistories;
    }

    // Cập nhật ServiceHistory
    public int updateServiceHistory(ServiceHistory serviceHistory) {
        ContentValues values = new ContentValues();
        values.put("car_id", serviceHistory.getCarId());
        values.put("service_id", serviceHistory.getServiceId());
        values.put("service_date", serviceHistory.getServiceDate().toString());
        values.put("notes", serviceHistory.getNotes());

        return database.update("ServiceHistory", values, "service_history_id = ?",
                new String[]{String.valueOf(serviceHistory.getServiceHistoryId())});
    }

    // Xóa ServiceHistory theo ID
    public void deleteServiceHistory(int serviceHistoryId) {
        database.delete("ServiceHistory", "service_history_id = ?", new String[]{String.valueOf(serviceHistoryId)});
    }

    // Xóa tất cả ServiceHistory theo carId
    public void deleteServiceHistoryByCarId(int carId) {
        database.delete("ServiceHistory", "car_id = ?", new String[]{String.valueOf(carId)});
    }

    // Phương thức chuyển con trỏ cursor thành đối tượng ServiceHistory
    private ServiceHistory cursorToServiceHistory(Cursor cursor) {
        int serviceHistoryId = cursor.getInt(0);
        int carId = cursor.getInt(1);
        int serviceId = cursor.getInt(2);
        String serviceDate = cursor.getString(3);
        String notes = cursor.getString(4);

        return new ServiceHistory(serviceHistoryId, carId, serviceId, new java.util.Date(serviceDate), notes);
    }
}
