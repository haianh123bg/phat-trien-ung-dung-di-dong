package com.nguyenngochaianh.btl.model;

import java.util.Date;

public class Course {
    private int courseId;
    private String name;
    private String description;
    private String duration;
    private double price;
    private Date startDate;
    private Date endDate;

    // Constructor mặc định
    public Course() {
    }

    // Constructor với tất cả các tham số
    public Course(int courseId, String name, String description, String duration, double price, Date startDate, Date endDate) {
        this.courseId = courseId;
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getter và Setter
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    // Builder Pattern
    public static class Builder {
        private int courseId;
        private String name;
        private String description;
        private String duration;
        private double price;
        private Date startDate;
        private Date endDate;

        public Builder courseId(int courseId) {
            this.courseId = courseId;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder duration(String duration) {
            this.duration = duration;
            return this;
        }

        public Builder price(double price) {
            this.price = price;
            return this;
        }

        public Builder startDate(Date startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder endDate(Date endDate) {
            this.endDate = endDate;
            return this;
        }

        // Build method to return the final Course object
        public Course build() {
            return new Course(courseId, name, description, duration, price, startDate, endDate);
        }
    }
}
