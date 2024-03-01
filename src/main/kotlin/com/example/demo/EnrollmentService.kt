package com.example.demo

import org.springframework.transaction.annotation.Transactional
import org.springframework.stereotype.Service

@Service
class EnrollmentService(
    private val studentRepository: StudentRepository,
    private val courseRepository: CourseRepository,
    private val studentCourseRepository: StudentCourseRepository
) {

    @Transactional
    suspend fun enrollStudentInCourse(studentId: Long, courseId: Long) {
        val studentExists = studentRepository.existsById(studentId)
        val courseExists = courseRepository.existsById(courseId)

        if (!studentExists || !courseExists) {
            throw IllegalArgumentException("Student or Course not found")
        }

        val existingEnrollment = studentCourseRepository.findByStudentIdAndCourseId(studentId, courseId).toList()
        if (existingEnrollment.isEmpty()) {
            val newEnrollment = StudentCourse(studentId = studentId, courseId = courseId)
            studentCourseRepository.save(newEnrollment)
        } else {
            throw IllegalStateException("Enrollment already exists")
        }
    }

    suspend fun createStudentAndEnrollInCourses(student: Student, courseIds: List<Long>) {
        val savedStudent = studentRepository.save(student)

        courseIds.forEach { courseId ->
            enrollStudentInCourse(savedStudent.id!!, courseId)
        }
    }
}