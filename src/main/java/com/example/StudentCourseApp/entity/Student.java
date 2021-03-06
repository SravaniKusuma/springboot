package com.example.StudentCourseApp.entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student")
public class Student {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="first_name")
    @NotNull(message=" Enter your first name")
    @Pattern(regexp = "[a-zA-z]*",message="only alphabets are allowed")
    private String firstName;

    @Column(name="last_name")
    @NotNull(message="Enter your last name")
    //@Pattern(regexp = "[a-zA-z]*",message="only alphabets are allowed")
    private String lastName;

    @Column(name="phone_number")
    @NotNull(message=" Enter your phone Number")
   // @Pattern(regexp = "^[0-9]{10}",message = "must contain 10 digits")
    private String phoneNumber;

    @Column(name="email")
    @NotNull(message=" This field is required")
    @Email(message = "Enter valid email")
    private String email;

    @Column(name="password")
    @NotNull(message=" This field is required")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{6,}$",message = "Password must contain atleast one uppercase alphabet, atleast one lowercase alphabet, atleast one digit, atleast one special character and minimum 6 in length")
    private String password;



    @ManyToMany(fetch=FetchType.LAZY,cascade= {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH,
            CascadeType.REFRESH})
    @JoinTable(name="course_student",
            joinColumns = @JoinColumn(name="student_id"),
            inverseJoinColumns = @JoinColumn(name="course_id"))
    private List<Course> courses;

    public Student()
    {

    }

    public Student(int id,String firstName, String lastName, String phoneNumber, String email,String password) {
        this.id=id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password=password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                '}';
    }

    //convenience method to add courses

    public void addCourse(Course theCourse)
    {
        if(courses == null)
        {
            courses = new ArrayList<>();
        }
        courses.add(theCourse);
    }

}
