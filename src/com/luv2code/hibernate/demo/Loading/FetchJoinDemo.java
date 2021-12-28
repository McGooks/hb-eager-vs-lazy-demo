package com.luv2code.hibernate.demo.Loading;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


public class FetchJoinDemo {
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

        try {
            // start the transaction
            session.beginTransaction();

            int instructorId = 1;

            Query<Instructor> query = session.createQuery("select i from Instructor i " + "JOIN FETCH i.courses " + "where i.id=:theInstructorId", Instructor.class);
            query.setParameter("theInstructorId", instructorId);
            Instructor tempInstructor = query.getSingleResult();
            System.out.println("GLENN::: Found InstructorDetail: " + tempInstructor);
            
            //commit the transaction
            session.getTransaction().commit();
            session.close();

            System.out.println("GLENN::: Associated courses: " + tempInstructor.getCourses());

            System.out.println("Complete");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
    }
}
