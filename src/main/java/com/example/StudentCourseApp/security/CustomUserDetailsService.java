package com.example.StudentCourseApp.security;

import com.example.StudentCourseApp.entity.Student;
import com.example.StudentCourseApp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private StudentService studentService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Student theStudent = studentService.findByEmail(s);
        if (theStudent == null) {
            throw new UsernameNotFoundException("Student not found");
        }
        return new CustomUserDetails(theStudent);

    }
}
