package com.example.StudentCourseApp.controller;

import com.example.StudentCourseApp.entity.Course;
import com.example.StudentCourseApp.entity.Student;
import com.example.StudentCourseApp.service.CourseService;
import com.example.StudentCourseApp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;


    @GetMapping("/admin-home")
    public String adminHome()
    {
        return "admin-home";
    }


    @GetMapping("/list-courses")
    public String adminCourses(Model theModel)
    {
        List<Course> theCourses= courseService.findAll();
        theModel.addAttribute("courses",theCourses);
        return "admin-courses";
    }

    @GetMapping("/add-course")
    public String addCourse(Model theModel)
    {
        Course theCourse = new Course();
        theModel.addAttribute("course",theCourse);
        return "add-course";
    }
    @PostMapping("/saveCourse")
    public String saveEmployee(@Valid @ModelAttribute("course") Course theCourse, BindingResult theBindingResult)
    {

        if(theBindingResult.hasErrors())
        {

            return "add-course";
        }


        courseService.save(theCourse);


        return "redirect:/admin/list-courses";
    }

    @GetMapping("/update-course")
    public  String updateCourse(@RequestParam("courseId") int theCourseId, Model theModel)
    {
        Course theCourse = courseService.findById(theCourseId);
        theModel.addAttribute("course",theCourse);
        return "add-course";
    }

    @GetMapping("/delete-course")
    public  String deleteCourse(@RequestParam("courseId") int theCourseId)
    {

        courseService.deleteById(theCourseId);
        return "redirect:/admin/list-courses";
    }

    @GetMapping("/students-enrolled")
    public String studentsEnrolled(Model theModel)
    {
        List<Student> students= studentService.findAll();
        for(Student temp: students)
        {
            System.out.println(temp.getCourses());
        }

        theModel.addAttribute("students",students);
        return "students-enrolled";
    }


}
