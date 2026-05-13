package com.raj.schoolerp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.raj.schoolerp.DTO.StudentsDTO;
import com.raj.schoolerp.exception.StudentsException;
import com.raj.schoolerp.model.Students;
import com.raj.schoolerp.service.StudentService;

@RestController
@RequestMapping("schoolerp/students")
@CrossOrigin("*")
public class StudentsController {

    @Autowired
    private StudentService studentService;

   
    @PostMapping("/add")
    public Students addStudent(@RequestBody StudentsDTO studentsDTO) throws StudentsException {

        return studentService.addStudent(studentsDTO);
    }

    
    @GetMapping("/admission/{admissionNo}")
    public Students getStudentByAdmissionNo(
            @PathVariable String admissionNo) throws StudentsException {

        return studentService.getStudentByAdmissionNo(admissionNo);
    }

    
    @GetMapping("/{studentId}")
    public Students getStudentByStudentId(
            @PathVariable Long studentId) throws StudentsException {

        return studentService.getStudentByStudentId(studentId);
    }

    
    @PutMapping("/update/{studentId}")
    public Students updateStudentByStudentId(
            @PathVariable Long studentId,
            @RequestBody StudentsDTO studentsDTO) throws StudentsException {

        return studentService.updateStudentByStudentId(studentId, studentsDTO);
    }

    
    @DeleteMapping("/delete/{studentId}")
    public String deleteStudentByStudentId(
            @PathVariable Long studentId) throws StudentsException {

        return studentService.deleteStudentByStudentId(studentId);
    }

   
    @GetMapping("/all")
    public List<Students> getAllStudents() throws StudentsException {

        return studentService.getAllStudents();
    }
}