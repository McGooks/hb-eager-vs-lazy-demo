package com.luv2code.hibernate.demo.CRUD;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstructorCoursesDemo {
    public static void main(String[] args) {
        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();

        try{
            // start the transaction
            session.beginTransaction();

            int instructorId = 1;
            Instructor tempInstructor = session.get(Instructor.class, instructorId);
            System.out.println("Found InstructorDetail: " + tempInstructor);
            System.out.println("Associated courses: " + tempInstructor.getCourses());

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
