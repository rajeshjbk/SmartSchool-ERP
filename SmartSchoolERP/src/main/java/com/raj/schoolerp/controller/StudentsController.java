package com.raj.schoolerp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raj.schoolerp.DTO.StudentsDTO;
import com.raj.schoolerp.exception.StudentsException;
import com.raj.schoolerp.model.Students;
import com.raj.schoolerp.model.UserRole;
import com.raj.schoolerp.service.StudentService;

@RestController
@RequestMapping("schoolerp/students")
@CrossOrigin(origins = "http://localhost:5173")
public class StudentsController {

	@Autowired
	private StudentService studentService;

	@PostMapping("/add")
	public Students addStudent(@RequestBody StudentsDTO studentsDTO) throws StudentsException {

		return studentService.addStudent(studentsDTO);
	}

	@GetMapping("/admission/{admissionNo}")
	public Students getStudentByAdmissionNo(@PathVariable String admissionNo) throws StudentsException {

		return studentService.getStudentByAdmissionNo(admissionNo);
	}

	@GetMapping("/{studentId}")
	public Students getStudentByStudentId(@PathVariable Long studentId) throws StudentsException {

		return studentService.getStudentByStudentId(studentId);
	}

	@PutMapping("/update/{studentId}")
	public Students updateStudentByStudentId(@PathVariable Long studentId, @RequestBody StudentsDTO studentsDTO)
			throws StudentsException {

		return studentService.updateStudentByStudentId(studentId, studentsDTO);
	}

	@DeleteMapping("/delete/{studentId}")
	public String deleteStudentByStudentId(@PathVariable Long studentId) throws StudentsException {

		return studentService.deleteStudentByStudentId(studentId);
	}

	@GetMapping("/all")
	public List<Students> getAllStudents() throws StudentsException {

		return studentService.getAllStudents();
	}

	@GetMapping("/role/{role}")
	public ResponseEntity<?> getStudentByRole(@PathVariable UserRole role) throws StudentsException {

		return new ResponseEntity<>(studentService.getStudentByRole(role), HttpStatus.OK);
	}

	@GetMapping("/parent/{parentId}")
	public ResponseEntity<List<Students>> getStudentByParentIdHandler(@PathVariable Long parentId) throws StudentsException {

		List<Students> students = studentService.getStudentByParentId(parentId);

		return new ResponseEntity<>(students, HttpStatus.OK);
	}
}