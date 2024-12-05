package com.nguyenngochaianh.btl.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "CarCare.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_USER = "Users";
    public static final String TABLE_CAR = "Cars";
    public static final String TABLE_SERVICE = "Services";
    public static final String TABLE_SERVICE_HISTORY = "ServiceHistory";
    public static final String TABLE_COURSE = "Courses";
    public static final String TABLE_USER_COURSE = "UserCourses";
    public static final String TABLE_NOTIFICATION = "Notifications";
    public static final String TABLE_PAYMENT = "Payments";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng Users
        String createUserTable = "CREATE TABLE Users (" +
                "user_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "email TEXT UNIQUE NOT NULL," +
                "password TEXT NOT NULL," +
                "phone TEXT," +
                "role TEXT CHECK(role IN ('CUSTOMER', 'ADMIN')) NOT NULL DEFAULT 'CUSTOMER');";

        // Tạo bảng Cars
        String createCarsTable = "CREATE TABLE Cars (" +
                "car_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "user_id INTEGER NOT NULL," +
                "license_plate TEXT UNIQUE NOT NULL," +
                "model TEXT NOT NULL," +
                "year INTEGER," +
                "FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE);";

        // Tạo bảng Services
        String createServicesTable = "CREATE TABLE Services (" +
                "service_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "description TEXT," +
                "price REAL NOT NULL" +
                ");";

        // Tạo bảng ServiceHistory
        String createServiceHistoryTable = "CREATE TABLE ServiceHistory (" +
                "service_history_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "car_id INTEGER NOT NULL," +
                "service_id INTEGER NOT NULL," +
                "service_date DATE NOT NULL," +
                "notes TEXT," +
                "FOREIGN KEY (car_id) REFERENCES Cars(car_id) ON DELETE CASCADE," +
                "FOREIGN KEY (service_id) REFERENCES Services(service_id)" +
                ");";

        // Tạo bảng Courses
        String createCoursesTable = "CREATE TABLE Courses (" +
                "course_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "description TEXT," +
                "duration TEXT," +  // Thời gian học (vd: "2 months")
                "price REAL NOT NULL," +
                "start_date DATE NOT NULL," +
                "end_date DATE" +
                ");";

        // Tạo bảng UserCourses
        String createUserCoursesTable = "CREATE TABLE UserCourses (" +
                "user_course_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "user_id INTEGER NOT NULL," +
                "course_id INTEGER NOT NULL," +
                "enrollment_date DATE NOT NULL," +
                "status TEXT CHECK(status IN ('ENROLLED', 'COMPLETED', 'CANCELLED')) NOT NULL DEFAULT 'ENROLLED'," +
                "FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE," +
                "FOREIGN KEY (course_id) REFERENCES Courses(course_id)" +
                ");";

        // Tạo bảng Notifications
        String createNotificationsTable = "CREATE TABLE Notifications (" +
                "notification_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "user_id INTEGER NOT NULL," +
                "message TEXT NOT NULL," +
                "notification_date DATE NOT NULL," +
                "status TEXT CHECK(status IN ('PENDING', 'SENT')) NOT NULL DEFAULT 'PENDING'," +
                "FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE" +
                ");";

        // Tạo bảng Payments
        String createPaymentsTable = "CREATE TABLE Payments (" +
                "payment_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "user_id INTEGER NOT NULL," +
                "amount REAL NOT NULL," +
                "payment_date DATE NOT NULL," +
                "status TEXT CHECK(status IN ('SUCCESS', 'FAILED', 'PENDING')) NOT NULL," +
                "FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE" +
                ");";

        // Bổ sung bảng DriverLicenses
        String createDriverLicensesTable = "CREATE TABLE DriverLicenses (" +
                "license_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "user_id INTEGER NOT NULL," +
                "license_number TEXT UNIQUE NOT NULL," +
                "category TEXT," + // Loại bằng lái (A1, B2, C, D, ...)
                "issue_date DATE NOT NULL," +
                "expiry_date DATE NOT NULL," +
                "status TEXT CHECK(status IN ('ACTIVE', 'EXPIRED', 'SUSPENDED')) NOT NULL DEFAULT 'ACTIVE'," +
                "FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE" +
                ");";

        // Execute các câu lệnh tạo bảng
        db.execSQL(createUserTable);
        db.execSQL(createCarsTable);
        db.execSQL(createServicesTable);
        db.execSQL(createServiceHistoryTable);
        db.execSQL(createCoursesTable);
        db.execSQL(createUserCoursesTable);
        db.execSQL(createNotificationsTable);
        db.execSQL(createPaymentsTable);
        db.execSQL(createDriverLicensesTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Users");
        db.execSQL("DROP TABLE IF EXISTS DriverLicenses");
        db.execSQL("DROP TABLE IF EXISTS Cars");
        db.execSQL("DROP TABLE IF EXISTS Services");
        db.execSQL("DROP TABLE IF EXISTS ServiceHistory");
        db.execSQL("DROP TABLE IF EXISTS Courses");
        db.execSQL("DROP TABLE IF EXISTS UserCourses");
        db.execSQL("DROP TABLE IF EXISTS Notifications");
        db.execSQL("DROP TABLE IF EXISTS Payments");
        onCreate(db);
    }
}
