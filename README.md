# HỆ THỐNG QUẢN LÝ KHÓA HỌC

## 1. Mô Tả Dự Án

Dự án "Hệ Thống Quản Lý Khóa Học" giúp quản lý việc đăng ký, theo dõi, và quản lý các khóa học cho sinh viên, giảng viên và quản trị viên. Hệ thống cung cấp các chức năng như:

- Đăng ký khóa học.
- Quản lý thông tin khóa học.
- Theo dõi tiến độ học tập của sinh viên.
- Quản lý tài nguyên học tập.
- Hệ thống điểm danh và chấm điểm.

## 2. Cấu Trúc Dự Án

Dự án được chia thành hai phần chính: **Frontend** và **Backend**.

### **Frontend**:
- Thư mục chính: `frontend/main`.
- Xây dựng giao diện người dùng, giúp sinh viên và giảng viên tương tác với hệ thống.

Cấu trúc thư mục frontend:
frontend/ └── main/ ├── src/ │ ├── components/ │ ├── pages/ │ ├── assets/ │ ├── services/ │ └── App.js └── package.json

### **Backend**:
- Thư mục chính: `backend/learn-programming`.
- Xử lý logic, quản lý cơ sở dữ liệu, xác thực người dùng và các dịch vụ API.

Cấu trúc thư mục backend:
backend/ └── learn-programming/ ├── src/ │ ├── main/ │ │ ├── java/com/example/coursemanagement/ │ │ ├── resources/ │ └── test/ ├── pom.xml └── README.md

## 3. Quy Trình Làm Việc Trên GitHub

### Quy Trình Làm Việc Git Với Nhánh `main` và `develop`

- **`main`**: Nhánh chính chứa mã nguồn ổn định, được xem là phiên bản phát hành chính thức của dự án. Chỉ nhóm trưởng hoặc người có quyền mới được phép merge mã vào nhánh này.
- **`develop`**: Nhánh trung tâm dùng cho việc phát triển. Tất cả các tính năng mới hoặc sửa lỗi đều được phát triển từ nhánh này. Sau khi mã ở `develop` đã ổn định và không có lỗi, nó sẽ được merge vào `main`.

### Trước Mỗi Phiên Làm Việc
1. **Pull Code Mới Nhất**:
    - Đảm bảo bạn có phiên bản code mới nhất từ nhánh `develop` trước khi bắt đầu làm việc:
    ```bash
    git checkout develop
    git pull origin develop
    ```

### Trong Quá Trình Làm Việc

2. **Tạo Nhánh Mới Từ `develop`**:
    - Khi phát triển một tính năng mới hoặc sửa lỗi, bạn tạo một nhánh mới từ `develop`. Tên nhánh nên theo cấu trúc `feature/[tên-tính-năng]` hoặc `bugfix/[mô-tả-lỗi]`:
    ```bash
    git checkout develop
    git checkout -b feature/tinh-nang-moi
    ```

3. **Thực Hiện Công Việc và Commit**:
    - Thực hiện các thay đổi và commit chúng:
    ```bash
    git add <file_name>
    git commit -m "Mô tả ngắn gọn thay đổi"
    ```

4. **Push Nhánh Lên Remote**:
    - Sau khi hoàn thành tính năng, bạn push nhánh của mình lên remote repository:
    ```bash
    git push origin feature/tinh-nang-moi
    ```

### Gửi Yêu Cầu Pull Request (PR)

5. **Tạo Pull Request**:
    - Truy cập GitHub và tạo Pull Request từ nhánh `feature/tinh-nang-moi` về nhánh `develop`.
    - Mô tả ngắn gọn về những thay đổi và tại sao thay đổi đó là cần thiết.

6. **Review PR**:
    - Nhóm trưởng hoặc người có trách nhiệm sẽ review mã của bạn. Nếu mã đáp ứng yêu cầu và không có lỗi, mã sẽ được merge vào nhánh `develop`.

### Sau Khi Merge Vào `develop`

7. **Cập Nhật `develop` Sau Khi Merge**:
    - Sau khi mã của bạn được merge vào nhánh `develop`, tất cả các thành viên cần cập nhật phiên bản mới nhất:
    ```bash
    git checkout develop
    git pull origin develop
    ```

### Trước Khi Merge Vào `main`

8. **Merge `develop` Vào `main`**:
    - Khi nhánh `develop` đã ổn định, nhóm trưởng sẽ tạo Pull Request từ nhánh `develop` vào `main`. Sau khi PR được review và xác nhận là ổn định, mã sẽ được merge vào nhánh `main` và có thể phát hành phiên bản mới.

## Lưu Ý Quan Trọng

- **Không Thao Tác Trực Tiếp Trên Nhánh `main`**: Các thành viên không được phép thực hiện bất kỳ thay đổi nào trực tiếp trên nhánh `main`. Tất cả thay đổi phải được thực hiện qua nhánh `develop`.
- **Pull Request (PR)**: Tất cả thay đổi từ các nhánh tính năng phải được review qua Pull Request trước khi merge vào `develop`.
- **Commit Rõ Ràng**: Mỗi commit phải rõ ràng, mô tả ngắn gọn và có ý nghĩa.
- **Kiểm Tra Kỹ Lưỡng**: Đảm bảo mã đã được kiểm tra và hoạt động ổn định trước khi gửi Pull Request.

## 4. Phần Mềm Cần Cài Đặt

### **Frontend**:
- **Node.js**: Để cài đặt các package và chạy frontend. [Tải Node.js](https://nodejs.org)
- **IDE**: Khuyến khích sử dụng **Visual Studio Code**. [Tải VS Code](https://code.visualstudio.com)

### **Backend**:
- **JDK 17**: Để biên dịch và chạy mã nguồn Java. [Tải JDK 17](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
- **Maven**: Quản lý các dependencies của dự án backend. [Tải Maven](https://maven.apache.org)
- **IDE**: Khuyến khích sử dụng **IntelliJ IDEA**. [Tải IntelliJ IDEA](https://www.jetbrains.com/idea)

## 5. Cách Chạy Dự Án

### **Frontend**:
1. Mở terminal và điều hướng vào thư mục `main` trong frontend:
 ```bash
 cd frontend/main
 ```

2. Cài đặt các package cần thiết:
 ```bash
npm install
```

3. Chạy dự án frontend:
 ```bash
npm run dev
 ```

### **Backend**:
1. Mở terminal và điều hướng vào thư mục learn-programming trong backend:
 ```bash
cd backend/learn-programming
 ```
2. Cài đặt các dependencies:
 ```bash
mvn clean install
 ```
3. Chạy dự án backend:
 ```bash
mvn spring-boot:run
 ```