package com.raj.schoolerp.service;

import java.util.List;

import com.raj.schoolerp.DTO.TeachersDTO;
import com.raj.schoolerp.entity.TeacherStatus;
import com.raj.schoolerp.entity.Teachers;
import com.raj.schoolerp.exception.TeachersException;

public interface TeachersService  {

	// Create Teacher
    Teachers addTeacher(TeachersDTO teacherDTO) throws TeachersException;

    // Update Teacher
    Teachers updateTeacher(Long teacherId, Teachers teacher) throws TeachersException;

    // Delete Teacher
    void deleteTeacher(Long teacherId) throws TeachersException;

    // Get Teacher By Id
    Teachers getTeacherById(Long teacherId) throws TeachersException;

    // Get All Teachers
    List<Teachers> getAllTeachers() throws TeachersException;

    // Get Teacher By Employee Id
    Teachers getTeacherByEmployeeId(String employeeId) throws TeachersException;

    // Get Teachers By Department
    List<Teachers> getTeachersByDepartment(String department) throws TeachersException;

    // Get Teachers By Designation
    List<Teachers> getTeachersByDesignation(String designation) throws TeachersException;

    // Get Teachers By Status
    List<Teachers> getTeachersByStatus(TeacherStatus status) throws TeachersException;

    // Update Teacher Status (ACTIVE, INACTIVE, ON_LEAVE)
    Teachers updateTeacherStatus(Long teacherId, TeacherStatus status) throws TeachersException;

    // Get Teacher By User Id
    Teachers getTeacherByUserId(Long userId) throws TeachersException;

    // Search Teacher By Name
    List<Teachers> searchTeacherByName(String name)throws TeachersException;

    // Get Teachers By Qualification
    List<Teachers> getTeachersByQualification(String qualification) throws TeachersException;

    // Count Total Teachers
    Long countTeachers();

    // Check Teacher Exists By Employee Id
    boolean existsByEmployeeId(String employeeId) throws TeachersException;
}
