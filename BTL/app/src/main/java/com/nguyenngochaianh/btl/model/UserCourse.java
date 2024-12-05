package com.nguyenngochaianh.btl.model;

import java.util.Date;

public class UserCourse {
    private int userCourseId;
    private int userId;
    private int courseId;
    private Date enrollmentDate;
    private String status;

    // Constructor mặc định
    public UserCourse() {
    }

    // Constructor với tất cả các tham số
    public UserCourse(int userCourseId, int userId, int courseId, Date enrollmentDate, String status) {
        this.userCourseId = userCourseId;
        this.userId = userId;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
        this.status = status;
    }

    // Getter và Setter
    public int getUserCourseId() {
        return userCourseId;
    }

    public void setUserCourseId(int userCourseId) {
        this.userCourseId = userCourseId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Builder Pattern
    public static class Builder {
        private int userCourseId;
        private int userId;
        private int courseId;
        private Date enrollmentDate;
        private String status;

        public Builder userCourseId(int userCourseId) {
            this.userCourseId = userCourseId;
            return this;
        }

        public Builder userId(int userId) {
            this.userId = userId;
            return this;
        }

        public Builder courseId(int courseId) {
            this.courseId = courseId;
            return this;
        }

        public Builder enrollmentDate(Date enrollmentDate) {
            this.enrollmentDate = enrollmentDate;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        // Build method to return the final UserCourse object
        public UserCourse build() {
            return new UserCourse(userCourseId, userId, courseId, enrollmentDate, status);
        }
    }
}
