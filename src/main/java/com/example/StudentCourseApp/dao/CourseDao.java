package com.example.StudentCourseApp.dao;

import com.example.StudentCourseApp.entity.Course;

import java.util.List;

public interface CourseDao {

    public List<Course> findAll();

    void save(Course theCourse);

    Course findById(int theId);

    void deleteById(int theId);
}
