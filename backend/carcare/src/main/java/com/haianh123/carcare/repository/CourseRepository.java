package com.haianh123.carcare.repository;

import com.haianh123.carcare.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}