package com.example.StudentCourseApp.service;

import com.example.StudentCourseApp.entity.Course;
import com.example.StudentCourseApp.entity.Student;

import java.util.List;

public interface CourseService {

    public List<Course> findAll();
    public Course findById(int theId);
    public void save(Course theCourse);
    public void deleteById(int theId);
}
