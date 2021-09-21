package com.example.coursapirest.controller;


import com.example.coursapirest.entities.Course;
import com.example.coursapirest.exception.ResourceNotFoundException;
import com.example.coursapirest.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "http://localhost:3000")
public class CourseController {

    private CourseService courseService;

    public CourseController() {
    }

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }


    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable(value = "id") Long courseId) {
        return courseService.getCourseById(courseId);
    }

    @PostMapping
    public Course createUser(@RequestBody Course course) {
        return courseService.createUser(course);
    }

    @PutMapping("/{id}")
    public Course updateUser(@RequestBody Course course, @PathVariable("id") Long courseId) {
        return courseService.updateUser(course, courseId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Course> deleteCourse(@PathVariable("id") Long courseId) {
            return courseService.deleteCourse(courseId);
    }
}
