package com.nguyenngochaianh.btl.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nguyenngochaianh.btl.db.DatabaseHelper;
import com.nguyenngochaianh.btl.model.CarCare;

import java.util.ArrayList;
import java.util.List;

public class CarCareRepository {

    private SQLiteDatabase db;
    private final DatabaseHelper dbHelper;

    public CarCareRepository(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public long addCarCare(CarCare carCare) {
        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("car_id", carCare.getCarId());
        values.put("receiving_location", carCare.getReceivingLocation());
        values.put("receipt_date", carCare.getReceiptDate());
        values.put("return_date", carCare.getReturnDate());
        values.put("status", carCare.getStatus());
        values.put("registration_service", carCare.getRegistrationService());

        long result = db.insert(DatabaseHelper.TABLE_CAR_CARE, null, values);
        db.close();
        return result;
    }

    public boolean checkCarProgressing(String carId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String query = "SELECT * FROM " + DatabaseHelper.TABLE_CAR_CARE + " WHERE car_id = ? AND status = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(carId), "PROCESSING"});

        if (cursor.getCount() > 0) {
            return true;
        }
        cursor.close();
        return false;
    }

    public List<CarCare> getAllCarCares() {
        db = dbHelper.getReadableDatabase();
        List<CarCare> carCares = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_CAR_CARE, null);

        if (cursor.moveToFirst()) {
            do {
                CarCare carCare = new CarCare();

                carCare.setCarCareId(cursor.getInt(cursor.getColumnIndexOrThrow("car_care_id")));
                carCare.setCarId(cursor.getString(cursor.getColumnIndexOrThrow("car_id")));
                carCare.setReceivingLocation(cursor.getString(cursor.getColumnIndexOrThrow("receiving_location")));
                carCare.setReceiptDate(cursor.getString(cursor.getColumnIndexOrThrow("receipt_date")));
                carCare.setReturnDate(cursor.getString(cursor.getColumnIndexOrThrow("return_date")));
                carCare.setStatus(cursor.getString(cursor.getColumnIndexOrThrow("status")));
                carCare.setRegistrationService(cursor.getString(cursor.getColumnIndexOrThrow("registration_service")));

                carCares.add(carCare);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return carCares;
    }
}
