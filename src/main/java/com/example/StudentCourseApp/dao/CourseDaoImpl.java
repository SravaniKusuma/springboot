package com.example.StudentCourseApp.dao;

import com.example.StudentCourseApp.entity.Course;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CourseDaoImpl implements CourseDao{

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Course> findAll()
    {

        Session currentSession = entityManager.unwrap(Session.class);

        // create a query
        Query theQuery = currentSession.createQuery("from Course", Course.class);

        // execute query and get result list
        List<Course> courses = theQuery.getResultList();

        // return the results
        return courses;
    }


    @Override
    public Course findById(int theId) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // get the employee
        Course theCourse = currentSession.get(Course.class, theId);
        // return the employee
        return theCourse;
    }


    @Override
    public void save(Course theCourse) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // save employee
        currentSession.saveOrUpdate(theCourse);
    }


    @Override
    public void deleteById(int theId) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // delete object with primary key
        Query theQuery =
                currentSession.createQuery(
                        "delete from Course where id=:courseId");
        theQuery.setParameter("courseId", theId);

        theQuery.executeUpdate();
    }
}
