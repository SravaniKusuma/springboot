package com.example.StudentCourseApp.service;

import com.example.StudentCourseApp.dao.CourseDao;
import com.example.StudentCourseApp.dao.StudentRepository;
import com.example.StudentCourseApp.entity.Course;
import com.example.StudentCourseApp.entity.Student;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CourseServiceTest {

    private CourseService courseService;
    private CourseDao courseDao;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllCourses()
    {
        courseDao = mock(CourseDao.class);
        courseService = new CourseServiceImpl(courseDao);
        List<Course> courses = new ArrayList<Course>();
        Course c1= new Course(1,"Spring",4,"Akhil");
        Course c2 = new Course(2,"Hibernate",4,"Anil");
        courses.add(c1);
        courses.add(c2);
        when(courseDao.findAll()).thenReturn(courses);
        List<Course> courseList = courseService.findAll();
        assertEquals(2, courseList.size());

    }

    @Test
    public void getCourseByIdTest()
    {
        courseDao = mock(CourseDao.class);
        courseService = new CourseServiceImpl(courseDao);
        when(courseDao.findById(1)).thenReturn(new Course(1, "Spring",4,"Akhil"));

       Course course = courseService.findById(1);

        assertEquals("Spring", course.getCourseName());
        assertEquals("Akhil", course.getInstructorName());

    }

}
