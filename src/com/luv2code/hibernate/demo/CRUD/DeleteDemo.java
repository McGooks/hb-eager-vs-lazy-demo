package com.luv2code.hibernate.demo.CRUD;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteDemo {
    public static void main(String[] args) {
        //create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class).buildSessionFactory();
        // create session


        try (factory) {
            Session session = factory.getCurrentSession();

            // start the transaction
            session.beginTransaction();

           // get record by ID
            int userId = 1;
            Instructor tempInstructor = session.get(Instructor.class, userId);
            System.out.println("Found Instructor: " + tempInstructor);

            // delete the instructor
            if(tempInstructor != null) {
                System.out.println("Deleting: " + tempInstructor);
                session.delete(tempInstructor);
            } else {
                throw new HibernateException("User not Found");
            }

            //commit the transaction
            session.getTransaction().commit();
            System.out.println("Complete");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
