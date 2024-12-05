package com.nguyenngochaianh.btl.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nguyenngochaianh.btl.db.DatabaseHelper;
import com.nguyenngochaianh.btl.model.Car;

import java.util.ArrayList;
import java.util.List;

public class CarRepository {

    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public CarRepository(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public long addCar(Car car) {
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("userId", car.getUserId());
        values.put("licensePlate", car.getLicensePlate());
        values.put("model", car.getModel());
        values.put("year", car.getYear());

        long result = db.insert(DatabaseHelper.TABLE_CAR, null, values);
        db.close();
        return result;
    }

    public Car getCarById(int carId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Car car = null;

        // Truy vấn thông tin xe theo carId
        String query = "SELECT * FROM " + DatabaseHelper.TABLE_CAR + " WHERE car_id = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(carId)});

        // Kiểm tra và lấy dữ liệu từ cursor
        if (cursor != null && cursor.moveToFirst()) {
            car = new Car();
            car.setCarId(cursor.getInt(0));
            car.setUserId(cursor.getInt(1));
            car.setLicensePlate(cursor.getString(2));
            car.setModel(cursor.getString(3));
            car.setYear(cursor.getInt(4));
        }

        // Đóng cursor và db để giải phóng tài nguyên
        if (cursor != null) {
            cursor.close();
        }
        db.close();

        return car;
    }


    public List<Car> getAllCars() {
        db = dbHelper.getReadableDatabase();
        List<Car> cars = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_CAR, null);

        if (cursor.moveToFirst()) {
            do {
                Car car = new Car();
                car.setCarId(cursor.getInt(0));
                car.setUserId(cursor.getInt(1));
                car.setLicensePlate(cursor.getString(2));
                car.setModel(cursor.getString(3));
                car.setYear(cursor.getInt(4));
                cars.add(car);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return cars;
    }

    public int updateCar(Car car) {
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("userId", car.getUserId());
        values.put("licensePlate", car.getLicensePlate());
        values.put("model", car.getModel());
        values.put("year", car.getYear());

        int rowsUpdated = db.update(DatabaseHelper.TABLE_CAR, values, "carId = ?", new String[]{String.valueOf(car.getCarId())});
        db.close();
        return rowsUpdated;
    }

    public void deleteCar(int carId) {
        db = dbHelper.getWritableDatabase();
        db.delete(DatabaseHelper.TABLE_CAR, "carId = ?", new String[]{String.valueOf(carId)});
        db.close();
    }
}
