package com.raj.schoolerp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.raj.schoolerp.DTO.StudentsDTO;
import com.raj.schoolerp.entity.Students;
import com.raj.schoolerp.exception.StudentsException;
import com.raj.schoolerp.service.StudentService;

@RestController
@RequestMapping("schoolerp/students")
@CrossOrigin("*")
public class StudentsController {

    @Autowired
    private StudentService studentService;

    // Add Student
    @PostMapping("/add")
    public Students addStudent(@RequestBody StudentsDTO studentsDTO) throws StudentsException {

        return studentService.addStudent(studentsDTO);
    }

    // Get Student By Admission No
    @GetMapping("/admission/{admissionNo}")
    public Students getStudentByAdmissionNo(
            @PathVariable String admissionNo) throws StudentsException {

        return studentService.getStudentByAdmissionNo(admissionNo);
    }

    // Get Student By Student ID
    @GetMapping("/{studentId}")
    public Students getStudentByStudentId(
            @PathVariable Long studentId) throws StudentsException {

        return studentService.getStudentByStudentId(studentId);
    }

    // Update Student By Student ID
    @PutMapping("/update/{studentId}")
    public Students updateStudentByStudentId(
            @PathVariable Long studentId,
            @RequestBody StudentsDTO studentsDTO) throws StudentsException {

        return studentService.updateStudentByStudentId(studentId, studentsDTO);
    }

    // Delete Student By Student ID
    @DeleteMapping("/delete/{studentId}")
    public String deleteStudentByStudentId(
            @PathVariable Long studentId) throws StudentsException {

        return studentService.deleteStudentByStudentId(studentId);
    }

    // Get All Students
    @GetMapping("/all")
    public List<Students> getAllStudents() throws StudentsException {

        return studentService.getAllStudents();
    }
}