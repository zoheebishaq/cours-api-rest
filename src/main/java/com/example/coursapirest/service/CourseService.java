package com.example.coursapirest.service;

import com.example.coursapirest.dao.CourseRepository;
import com.example.coursapirest.entities.Course;
import com.example.coursapirest.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private CourseRepository courseRepository;

    public CourseService() {

    }

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long courseId) {
        return courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("User not Found with id :" + courseId));
    }

    public Course createUser(Course course) {
        return courseRepository.save(course);
    }

    public Course updateUser(Course course, Long courseId) {
        Course existingCourse = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not Found with id:" + courseId));
        existingCourse.setTitle(course.getTitle());
        existingCourse.setDescription(course.getDescription());
        return courseRepository.save(existingCourse);
    }

    public ResponseEntity<Course> deleteCourse(Long courseId) {
        Course existingCourse = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not Found with id:" + courseId));
        courseRepository.delete(existingCourse);
        return ResponseEntity.ok().build();
    }
}
