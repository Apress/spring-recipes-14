package com.apress.springrecipes.course;

import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by marten on 24-03-14.
 */
public class HibernateCourseDao implements CourseDao {

    private final HibernateTemplate hibernateTemplate;

    public HibernateCourseDao(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate=hibernateTemplate;
    }

    @Transactional
    public void store(Course course) {
        hibernateTemplate.saveOrUpdate(course);
    }

    @Transactional
    public void delete(Long courseId) {
        Course course = hibernateTemplate.get(Course.class, courseId);
        hibernateTemplate.delete(course);
    }

    @Transactional(readOnly = true)
    public Course findById(Long courseId) {
        return hibernateTemplate.get(Course.class, courseId);
    }

    @Transactional(readOnly = true)
    public List<Course> findAll() {
        return (List<Course>) hibernateTemplate.find("from Course");
    }

}
