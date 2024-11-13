package com.nguyenngochaianh.btl.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.nguyenngochaianh.btl.db.DatabaseHelper;
import com.nguyenngochaianh.btl.model.Service;

import java.util.ArrayList;
import java.util.List;

public class ServiceRepository {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public ServiceRepository(Context context) {
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

    // Thêm một dịch vụ mới
    public long addService(Service service) {
        ContentValues values = new ContentValues();
        values.put("name", service.getName());
        values.put("description", service.getDescription());
        values.put("price", service.getPrice());

        return database.insert("Services", null, values);
    }

    // Lấy danh sách tất cả dịch vụ
    public List<Service> getAllServices() {
        List<Service> services = new ArrayList<>();
        Cursor cursor = database.query("Services", null, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Service service = cursorToService(cursor);
                services.add(service);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return services;
    }

    // Chuyển cursor thành đối tượng Service
    private Service cursorToService(Cursor cursor) {
        Service service = new Service();
        service.setServiceId(cursor.getInt(0));
        service.setName(cursor.getString(1));
        service.setDescription(cursor.getString(2));
        service.setPrice(cursor.getDouble(3));
        return service;
    }

    // Cập nhật thông tin dịch vụ
    public int updateService(Service service) {
        ContentValues values = new ContentValues();
        values.put("name", service.getName());
        values.put("description", service.getDescription());
        values.put("price", service.getPrice());

        return database.update("Services", values, "service_id = ?", new String[]{String.valueOf(service.getServiceId())});
    }

    // Xóa dịch vụ theo ID
    public void deleteService(int serviceId) {
        database.delete("Services", "service_id = ?", new String[]{String.valueOf(serviceId)});
    }

    // Lấy thông tin dịch vụ theo ID
    public Service getServiceById(int serviceId) {
        Cursor cursor = database.query("Services", null, "service_id = ?", new String[]{String.valueOf(serviceId)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            Service service = cursorToService(cursor);
            cursor.close();
            return service;
        }
        return null;
    }
}
