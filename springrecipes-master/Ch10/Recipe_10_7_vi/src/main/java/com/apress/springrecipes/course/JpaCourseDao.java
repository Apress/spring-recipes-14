package com.apress.springrecipes.course;

import javax.persistence.*;
import java.util.List;

/**
 * Created by marten on 24-03-14.
 */
public class JpaCourseDao implements CourseDao {

    private final EntityManagerFactory entityManagerFactory;

    public JpaCourseDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory=entityManagerFactory;
    }

    public void store(Course course) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();
        try {
            tx.begin();
            manager.merge(course);
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            manager.close();
        }
    }

    public void delete(Long courseId) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();
        try {
            tx.begin();
            Course course = manager.find(Course.class, courseId);
            manager.remove(course);
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            manager.close();
        }
    }

    public Course findById(Long courseId) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        try {
            return manager.find(Course.class, courseId);
        } finally {
            manager.close();
        }
    }

    public List<Course> findAll() {
        EntityManager manager = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<Course> query = manager.createQuery("select course from Course course", Course.class);
            return query.getResultList();
        } finally {
            manager.close();
        }
    }


}
