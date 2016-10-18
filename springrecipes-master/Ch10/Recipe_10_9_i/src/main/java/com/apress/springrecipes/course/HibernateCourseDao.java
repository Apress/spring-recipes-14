package com.apress.springrecipes.course;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by marten on 24-03-14.
 */
public class HibernateCourseDao implements CourseDao {

    private final SessionFactory sessionFactory;

    public HibernateCourseDao(SessionFactory sessionFactory) {
        this.sessionFactory=sessionFactory;
    }

    @Transactional
    public void store(Course course) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(course);
    }

    @Transactional
    public void delete(Long courseId) {
        Session session = sessionFactory.getCurrentSession();
        Course course = (Course) session.get(Course.class, courseId);
        session.delete(course);
    }

    @Transactional(readOnly=true)
    public Course findById(Long courseId) {
        Session session = sessionFactory.getCurrentSession();
        return (Course) session.get(Course.class, courseId);
    }

    @Transactional(readOnly=true)
    public List<Course> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Course");
        return query.list();
    }

}
