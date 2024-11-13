package com.nguyenngochaianh.btl.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.nguyenngochaianh.btl.db.DatabaseHelper;
import com.nguyenngochaianh.btl.model.Payment;

import java.util.ArrayList;
import java.util.List;

public class PaymentRepository {

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public PaymentRepository(Context context) {
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

    // Phương thức thêm thanh toán vào cơ sở dữ liệu
    public long addPayment(Payment payment) {
        ContentValues values = new ContentValues();
        values.put("user_id", payment.getUserId());
        values.put("amount", payment.getAmount());
        values.put("payment_date", payment.getPaymentDate().toString());
        values.put("status", payment.getStatus());

        return database.insert("Payments", null, values);
    }

    // Phương thức lấy tất cả thanh toán của người dùng
    public List<Payment> getAllPayments() {
        List<Payment> payments = new ArrayList<>();
        Cursor cursor = database.query("Payments", null, null, null, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    Payment payment = cursorToPayment(cursor);
                    payments.add(payment);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        return payments;
    }

    // Phương thức lấy thanh toán theo ID
    public Payment getPaymentById(int paymentId) {
        Cursor cursor = database.query("Payments", null, "payment_id = ?", new String[]{String.valueOf(paymentId)}, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                Payment payment = cursorToPayment(cursor);
                cursor.close();
                return payment;
            }
        }

        return null;
    }

    // Phương thức cập nhật trạng thái thanh toán
    public int updatePaymentStatus(int paymentId, String status) {
        ContentValues values = new ContentValues();
        values.put("status", status);

        return database.update("Payments", values, "payment_id = ?", new String[]{String.valueOf(paymentId)});
    }

    // Phương thức xóa thanh toán theo ID
    public void deletePayment(int paymentId) {
        database.delete("Payments", "payment_id = ?", new String[]{String.valueOf(paymentId)});
    }

    // Phương thức chuyển con trỏ cursor thành đối tượng Payment
    private Payment cursorToPayment(Cursor cursor) {
        int paymentId = cursor.getInt(0);
        int userId = cursor.getInt(1);
        double amount = cursor.getDouble(2);
        String paymentDate = cursor.getString(3);
        String status = cursor.getString(4);

        return new Payment(paymentId, userId, amount, new java.util.Date(paymentDate), status);
    }
}
