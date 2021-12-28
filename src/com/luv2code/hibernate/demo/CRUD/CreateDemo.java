package com.luv2code.hibernate.demo.CRUD;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDemo {
    public static void main(String[] args) {
        //create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class).buildSessionFactory();
        // create session


        try (factory) {
            Session session = factory.getCurrentSession();
            //Create the objects
//            Instructor tempInstructor = new Instructor("Glenn", "Marshall-Adams", "glenn@123.com");
//            InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.google.com", "Love to Code");

            Instructor tempInstructor = new Instructor("Gary", "Marshall-Adams", "Gary@123.com");
            InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.google.co.uk", "Sleep");

            //Associate the objects
            tempInstructor.setInstructorDetail(tempInstructorDetail);

            // start the transaction
            session.beginTransaction();

            //Save the instructor
            //Note that due to Cascading this will save the InstructorDetail
            session.save(tempInstructor);

            //commit the transaction
            session.getTransaction().commit();
            System.out.println("Complete");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
