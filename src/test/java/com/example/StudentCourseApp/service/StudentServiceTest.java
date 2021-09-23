package com.example.StudentCourseApp.service;

import com.example.StudentCourseApp.dao.StudentRepository;
import com.example.StudentCourseApp.entity.Student;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


@SpringBootTest
public class StudentServiceTest {

    private StudentService studentService;
    private StudentRepository studentRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllStudents()
    {
        studentRepository = mock(StudentRepository.class);
        studentService = new StudentServiceImpl(studentRepository);
        List<Student> students = new ArrayList<Student>();
        Student s1 = new Student(1,"Sravani","Kusuma","7013052444","sravanikusuma14@gmail.com","test123");
        Student s2 = new Student(2,"Soumya","Kusuma","9390672152","soumyakusuma2@gmail.com","test123");
        Student s3 = new Student(3,"Uma","Kasthuri","8465039781","uma26@gmail.com","test123");
        students.add(s1);
        students.add(s2);
        students.add(s3);
        when(studentRepository.findAll()).thenReturn(students);
        List<Student> studentList = studentService.findAll();
        assertEquals(3, studentList.size());

    }

    @Test
    public void getStudentByIdTest()
    {
        studentRepository = mock(StudentRepository.class);
        studentService = new StudentServiceImpl(studentRepository);
        when(studentRepository.findById(1)).thenReturn(java.util.Optional.of(new Student(1, "Sravani", "Kusuma", "7013052444", "sravanikusuma14@email.com", "test123")));

       Student student = studentService.findById(1);

        assertEquals("Sravani", student.getFirstName());
        assertEquals("Kusuma", student.getLastName());

    }

    @Test
    public void createStudent()
    {
        studentRepository = mock(StudentRepository.class);
        studentService = new StudentServiceImpl(studentRepository);
        Student student  = new Student(1,"Sravani","Kusuma","7013052444","sravanikusuma14@gmail.com","test123");
        studentService.save(student);
    }



}
