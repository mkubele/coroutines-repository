package com.example.demo

import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface AuthorRepository : CoroutineCrudRepository<Author, Long>

interface BookRepository : CoroutineCrudRepository<Book, Long> {
    suspend fun findByAuthorId(authorId: Long): List<Book>
}

interface StudentRepository : CoroutineCrudRepository<Student, Long>

interface CourseRepository : CoroutineCrudRepository<Course, Long>

interface StudentCourseRepository : CoroutineCrudRepository<StudentCourse, Long> {
    suspend fun findByStudentId(studentId: Long): List<StudentCourse>
    suspend fun findByCourseId(courseId: Long): List<StudentCourse>
    suspend fun findByStudentIdAndCourseId(studentId: Long, courseId: Long): List<StudentCourse>
}