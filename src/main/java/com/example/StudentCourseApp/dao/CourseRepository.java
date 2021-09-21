package com.example.StudentCourseApp.dao;

import com.example.StudentCourseApp.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
