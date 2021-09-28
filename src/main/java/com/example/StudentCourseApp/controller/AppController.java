package com.example.StudentCourseApp.controller;

import com.example.StudentCourseApp.dao.StudentRepository;
import com.example.StudentCourseApp.entity.Course;
import com.example.StudentCourseApp.entity.Student;
import com.example.StudentCourseApp.service.CourseService;
import com.example.StudentCourseApp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AppController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @GetMapping("/home")
    public String viewHomePage() {
        return "home-page";
    }

    @GetMapping("/register")
    public String registerStudent(Model theModel)
    {
        Student theStudent = new Student();
        theModel.addAttribute("student",theStudent);
        return "student-signup";
    }

    @GetMapping("/login")
    public String login()
    {
        return "login";
    }


    @PostMapping("/saveStudent")
    public String saveStudent(@Valid @ModelAttribute("student") Student theStudent,BindingResult bindingResult, Model theModel)
    {


        if(bindingResult.hasErrors())
        {
            return "student-signup";
        }

        if(studentService.studentExists(theStudent.getEmail()))
        {
            bindingResult.addError(new FieldError("student","email","Email address already exists"));
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(theStudent.getPassword());
        theStudent.setPassword(encodedPassword);
        theModel.addAttribute("student",theStudent);
        studentService.save(theStudent);
        return "register-success";
    }
    @GetMapping("/student-home")
    public String studentHome(@RequestParam("studentId") int theStudentId, Model theModel)
    {
        Student theStudent = studentService.findById(theStudentId);
        theModel.addAttribute("student",theStudent);
        return "student-home";
    }


    @GetMapping("/view-details")
    public String viewDetails(@RequestParam("studentId") int theStudentId, Model theModel)
    {
        Student theStudent = studentService.findById(theStudentId);
        theModel.addAttribute("student",theStudent);
        return "view-details";
    }

    @GetMapping("/update-student")
    public String updateStudent(@RequestParam("studentId") int theStudentId, Model theModel)
    {
        Student theStudent = studentService.findById(theStudentId);
        theModel.addAttribute("student",theStudent);
        return "student-update-form";

    }

    @PostMapping("/save-updated-student")
    public String saveUpdatedStudent(@Valid @ModelAttribute("student") Student theStudent, BindingResult theBindingResult)
    {
        if(theBindingResult.hasErrors())
        {
            System.out.println(theBindingResult);
            return "student-update-form";
        }
        int theStudentId= theStudent.getId();

        studentService.save(theStudent);
        return "redirect:/view-details?studentId="+theStudentId;
    }

    @GetMapping("/courses")
    public String showCourses(@RequestParam("studentId") int theStudentId, Model theModel)
    {
        Student theStudent = studentService.findById(theStudentId);
        List<Course> theCourses = courseService.findAll();
        theModel.addAttribute("student",theStudent);
        theModel.addAttribute("courses",theCourses);
        return "list-courses";

    }
    @GetMapping("/register-course")
    public String registerCourse(@RequestParam("courseId") int theCourseId, @RequestParam("studentId") int theStudentId,
                                 Model theModel)
    {
        Student theStudent = studentService.findById(theStudentId);
        Course theCourse= courseService.findById(theCourseId);
        List<Course> courses= theStudent.getCourses();
        for(Course tempCourse: courses)
        {
            if(tempCourse.getId()== theCourseId)
            {
                theModel.addAttribute("student",theStudent);
                return "course-denied";
            }
        }
        theCourse.addStudent(theStudent);
        courseService.save(theCourse);
        theModel.addAttribute("student",theStudent);
        return "redirect:/view-details?studentId="+theStudentId;
    }

    @GetMapping("/access-denied")
    public String showAccessDenied()
    {
        return "access-denied";
    }


    //adding an initbinder to trim input strings to remove leading and trailing whitespaces
    //called for every web request coming to controller

    @InitBinder
    public void initBinder(WebDataBinder dataBinder)
    {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }





}


