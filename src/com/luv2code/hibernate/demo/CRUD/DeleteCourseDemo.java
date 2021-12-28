package com.luv2code.hibernate.demo.CRUD;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCourseDemo {
    public static void main(String[] args) {
        //create session factory
        SessionFactory factory =
                new Configuration()
                        .configure("hibernate.cfg.xml")
                        .addAnnotatedClass(Instructor.class)
                        .addAnnotatedClass(InstructorDetail.class)
                        .addAnnotatedClass(Course.class)
                        .buildSessionFactory();
        // create session

        Session session = factory.getCurrentSession();
        try {
            // start the transaction
            session.beginTransaction();

            // get record by ID
            int courseId = 10;
            Course tempCourse = session.get(Course.class, courseId);
            System.out.println("Found Course: " + tempCourse);

            session.delete(tempCourse);

            //commit the transaction
            session.getTransaction().commit();
            System.out.println("Complete");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();

        }
    }
}
