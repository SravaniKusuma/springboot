package com.example.StudentCourseApp.service;

import com.example.StudentCourseApp.entity.Student;

import java.util.List;

public interface StudentService {

    public List<Student> findAll();
    public Student findById(int theId);
    public void save(Student theStudent);
    public void deleteById(int theId);
    Student findByEmail(String s);
    public boolean studentExists(String email);

}
