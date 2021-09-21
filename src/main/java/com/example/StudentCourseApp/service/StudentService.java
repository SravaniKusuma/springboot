package com.example.StudentCourseApp.service;

import com.example.StudentCourseApp.dao.StudentRepository;
import com.example.StudentCourseApp.entity.Course;
import com.example.StudentCourseApp.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface StudentService {

    public List<Student> findAll();
    public Student findById(int theId);
    public void save(Student theStudent);
    public void deleteById(int theId);

    Student findByEmail(String s);
}
