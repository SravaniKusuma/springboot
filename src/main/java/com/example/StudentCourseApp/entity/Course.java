package com.example.StudentCourseApp.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="course_name")
    @NotBlank(message = "This field is mandatory")
    private String courseName;

    @Column(name = "rating")
    @Min(value = 1,message = "must be greater than or equal to  1")
    @Max(value=5,message="must be less than or equal to 5")
    private float rating;

    @Column(name="instructor_name")
   @NotBlank(message = "This field is mandatory")
    private String instructorName;



    @ManyToMany(fetch=FetchType.LAZY,cascade= {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH,
            CascadeType.REFRESH})
    @JoinTable(name="course_student",
            joinColumns = @JoinColumn(name="course_id"),
            inverseJoinColumns = @JoinColumn(name="student_id"))
    private List<Student> students;


    public Course()
    {

    }

    public Course(String courseName, float rating, String instructorName)
    {
        this.courseName = courseName;
        this.rating = rating;
        this.instructorName = instructorName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public List<Student> getStudents() {
        return students;
    }


    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return
                "courseName= " + courseName;
    }

    //convenience method to add student
    public void addStudent(Student theStudent)
    {
        if(students==null)
        {
            students = new ArrayList<>();
        }
        students.add(theStudent);
    }



}
