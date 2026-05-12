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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.raj.schoolerp.DTO.TeachersDTO;
import com.raj.schoolerp.exception.TeachersException;
import com.raj.schoolerp.model.TeacherStatus;
import com.raj.schoolerp.model.Teachers;
import com.raj.schoolerp.service.TeachersService;

@RestController
@RequestMapping("/schoolerp/teachers")
@CrossOrigin("*")
public class TeachersController {

	@Autowired
	private TeachersService teacherService;

	@PostMapping("/add")
	public ResponseEntity<Teachers> addTeacher(@RequestBody TeachersDTO teacher) throws TeachersException {

		return new ResponseEntity<>(teacherService.addTeacher(teacher), HttpStatus.CREATED);
	}

	@PutMapping("/update/{teacherId}")
	public ResponseEntity<Teachers> updateTeacher(@PathVariable Long teacherId, @RequestBody TeachersDTO teacher)
			throws TeachersException {

		return ResponseEntity.ok(teacherService.updateTeacher(teacherId, teacher));
	}

	@DeleteMapping("/delete/{teacherId}")
	public ResponseEntity<String> deleteTeacher(@PathVariable Long teacherId) throws TeachersException {

		teacherService.deleteTeacher(teacherId);

		return ResponseEntity.ok("Teacher deleted successfully");
	}

	@GetMapping("/{teacherId}")
	public ResponseEntity<Teachers> getTeacherById(@PathVariable Long teacherId) throws TeachersException {

		return ResponseEntity.ok(teacherService.getTeacherById(teacherId));
	}

	@GetMapping("/all")
	public ResponseEntity<List<Teachers>> getAllTeachers() throws TeachersException {

		return ResponseEntity.ok(teacherService.getAllTeachers());
	}

	@GetMapping("/employee/{employeeId}")
	public ResponseEntity<Teachers> getTeacherByEmployeeId(@PathVariable String employeeId) throws TeachersException {

		return ResponseEntity.ok(teacherService.getTeacherByEmployeeId(employeeId));
	}

	@GetMapping("/department/{department}")
	public ResponseEntity<List<Teachers>> getTeachersByDepartment(@PathVariable String department)
			throws TeachersException {

		return ResponseEntity.ok(teacherService.getTeachersByDepartment(department));
	}

	@PutMapping("/{teacherId}/status")
	public ResponseEntity<Teachers> updateTeacherStatus(@PathVariable Long teacherId,
			@RequestParam TeacherStatus status) throws TeachersException {

		return ResponseEntity.ok(teacherService.updateTeacherStatus(teacherId, status));
	}

	@GetMapping("/count")
	public ResponseEntity<Long> countTeachers() {

		return ResponseEntity.ok(teacherService.countTeachers());
	}
}