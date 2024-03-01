package com.example.demo

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("author")
data class Author(
    @Id val id: Long? = null,
    val name: String
)

@Table("book")
data class Book(
    @Id val id: Long? = null,
    val title: String,
    val authorId: Long
)

@Table("student")
data class Student(
    @Id val id: Long? = null,
    val name: String
)

@Table("course")
data class Course(
    @Id val id: Long? = null,
    val title: String
)

@Table("student_course")
data class StudentCourse(
    val studentId: Long,
    val courseId: Long
)
