package com.example.StudentCourseApp.service;

import com.example.StudentCourseApp.dao.CourseDao;
import com.example.StudentCourseApp.dao.CourseRepository;
import com.example.StudentCourseApp.dao.StudentRepository;
import com.example.StudentCourseApp.entity.Course;
import com.example.StudentCourseApp.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService{

    private final CourseDao courseDao;

    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao= courseDao;
    }


    @Override
    @Transactional
    public List<Course> findAll() {
        return courseDao.findAll();
    }

    @Override
    @Transactional
    public Course findById(int theId) {

        Optional<Course> result = Optional.ofNullable(courseDao.findById(theId));

        Course theCourse = null;

        if (result.isPresent()) {
            theCourse = result.get();
        }
        else {
            // we didn't find the student
            throw new RuntimeException("Did not find student id - " + theId);
        }

        return theCourse;
    }

    @Override
    @Transactional
    public void save(Course theCourse) {
        courseDao.save(theCourse);
    }

    @Override
    @Transactional
    public void deleteById(int theId) {

        courseDao.deleteById(theId);
    }

}
