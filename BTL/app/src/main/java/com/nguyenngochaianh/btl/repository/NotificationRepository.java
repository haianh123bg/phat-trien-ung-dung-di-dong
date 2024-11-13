package com.nguyenngochaianh.btl.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.nguyenngochaianh.btl.db.DatabaseHelper;
import com.nguyenngochaianh.btl.model.Notification;

import java.util.ArrayList;
import java.util.List;

public class NotificationRepository {

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public NotificationRepository(Context context) {
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

    // Phương thức thêm thông báo mới vào cơ sở dữ liệu
    public long addNotification(Notification notification) {
        ContentValues values = new ContentValues();
        values.put("user_id", notification.getUserId());
        values.put("message", notification.getMessage());
        values.put("notification_date", notification.getNotificationDate().toString());
        values.put("status", notification.getStatus());

        return database.insert("Notifications", null, values);
    }

    // Phương thức lấy tất cả thông báo của người dùng
    public List<Notification> getAllNotifications() {
        List<Notification> notifications = new ArrayList<>();
        Cursor cursor = database.query("Notifications", null, null, null, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    Notification notification = cursorToNotification(cursor);
                    notifications.add(notification);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        return notifications;
    }

    // Phương thức lấy thông báo theo ID
    public Notification getNotificationById(int notificationId) {
        Cursor cursor = database.query("Notifications", null, "notification_id = ?", new String[]{String.valueOf(notificationId)}, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                Notification notification = cursorToNotification(cursor);
                cursor.close();
                return notification;
            }
        }

        return null;
    }

    // Phương thức cập nhật trạng thái thông báo
    public int updateNotificationStatus(int notificationId, String status) {
        ContentValues values = new ContentValues();
        values.put("status", status);

        return database.update("Notifications", values, "notification_id = ?", new String[]{String.valueOf(notificationId)});
    }

    // Phương thức xóa thông báo theo ID
    public void deleteNotification(int notificationId) {
        database.delete("Notifications", "notification_id = ?", new String[]{String.valueOf(notificationId)});
    }

    // Phương thức chuyển con trỏ cursor thành đối tượng Notification
    private Notification cursorToNotification(Cursor cursor) {
        int notificationId = cursor.getInt(0);
        int userId = cursor.getInt(1);
        String message = cursor.getString(2);
        String notificationDate = cursor.getString(3);
        String status = cursor.getString(4);

        return new Notification(notificationId, userId, message, new java.util.Date(notificationDate), status);
    }
}
