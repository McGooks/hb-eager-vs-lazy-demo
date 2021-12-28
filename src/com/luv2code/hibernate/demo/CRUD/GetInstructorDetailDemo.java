package com.luv2code.hibernate.demo.CRUD;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstructorDetailDemo {
    public static void main(String[] args) {
        //create session factory
        SessionFactory factory =
                new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class).buildSessionFactory();
        // create session

        Session session = factory.getCurrentSession();
        try{

            // start the transaction
            session.beginTransaction();

            // get record by ID
            int instructorDetailId = 3;
            InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, instructorDetailId);
            System.out.println("Found InstructorDetail: " + tempInstructorDetail);
            System.out.println("Associated Instructor: " + tempInstructorDetail.getInstructor());

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
