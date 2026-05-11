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

import com.raj.schoolerp.DTO.ClassesDTO;
import com.raj.schoolerp.entity.Classes;
import com.raj.schoolerp.exception.ClassesException;
import com.raj.schoolerp.service.ClassesService;

@RestController
@RequestMapping("/schoolerp/classes")
@CrossOrigin(origins = "*")
public class ClassesController {

	@Autowired
	private ClassesService classesService;

	// Add Class
	@PostMapping("/add")
	public ResponseEntity<Classes> addClass(@RequestBody ClassesDTO classes) throws ClassesException {

		Classes savedClass = classesService.addClass(classes);

		return new ResponseEntity<>(savedClass, HttpStatus.CREATED);
	}

	// Update Class
	@PutMapping("/update/{classId}")
	public ResponseEntity<Classes> updateClass(@PathVariable Long classId, @RequestBody ClassesDTO classes)
			throws ClassesException {

		Classes updatedClass = classesService.updateClass(classId, classes);

		return new ResponseEntity<>(updatedClass, HttpStatus.OK);
	}

	// Delete Class
	@DeleteMapping("/delete/{classId}")
	public ResponseEntity<String> deleteClass(@PathVariable Long classId) throws ClassesException {

		classesService.deleteClass(classId);

		return new ResponseEntity<>("Class deleted successfully", HttpStatus.OK);
	}

	// Get Class By Id
	@GetMapping("/{classId}")
	public ResponseEntity<Classes> getClassById(@PathVariable Long classId) throws ClassesException {

		Classes classes = classesService.getClassById(classId);

		return new ResponseEntity<>(classes, HttpStatus.OK);
	}

	// Get All Classes
	@GetMapping("/all")
	public ResponseEntity<List<Classes>> getAllClasses() throws ClassesException {

		List<Classes> classesList = classesService.getAllClasses();

		return new ResponseEntity<>(classesList, HttpStatus.OK);
	}

	// Get Class By Name
	@GetMapping("/name/{className}")
	public ResponseEntity<List<Classes>> getClassByName(@PathVariable String className) throws ClassesException {

		List<Classes> classes = classesService.getClassByName(className);

		return new ResponseEntity<>(classes, HttpStatus.OK);
	}

	// Get Class By Section
	@GetMapping("/section/{section}")
	public ResponseEntity<List<Classes>> getClassBySection(@PathVariable String section) throws ClassesException {

		List<Classes> classes = classesService.getClassBySection(section);

		return new ResponseEntity<>(classes, HttpStatus.OK);
	}

	// Get Classes By Academic Year
	@GetMapping("/academic-year/{academicYear}")
	public ResponseEntity<List<Classes>> getClassesByAcademicYear(@PathVariable String academicYear)
			throws ClassesException {

		List<Classes> classes = classesService.getClassesByAcademicYear(academicYear);

		return new ResponseEntity<>(classes, HttpStatus.OK);
	}

	// Get Class By Room Number
	@GetMapping("/room/{roomNo}")
	public ResponseEntity<Classes> getClassByRoomNo(@PathVariable String roomNo) throws ClassesException {

		Classes classes = classesService.getClassByRoomNo(roomNo);

		return new ResponseEntity<>(classes, HttpStatus.OK);
	}

	// Get Class By Teacher Id
	@GetMapping("/teacher/{teacherId}")
	public ResponseEntity<Classes> getClassByTeacherId(@PathVariable Long teacherId) throws ClassesException {

		Classes classes = classesService.getClassByTeacherId(teacherId);

		return new ResponseEntity<>(classes, HttpStatus.OK);
	}

	// Get Students By Class
	@GetMapping("/{classId}/students")
	public ResponseEntity<Classes> getStudentsByClass(@PathVariable Long classId) throws ClassesException {

		Classes classes = classesService.getStudentsByClass(classId);

		return new ResponseEntity<>(classes, HttpStatus.OK);
	}

	// Get Subjects By Class
	@GetMapping("/{classId}/subjects")
	public ResponseEntity<Classes> getSubjectsByClass(@PathVariable Long classId) throws ClassesException {

		Classes classes = classesService.getSubjectsByClass(classId);

		return new ResponseEntity<>(classes, HttpStatus.OK);
	}

	// Count Total Classes
	@GetMapping("/count")
	public ResponseEntity<Long> countClasses() {

		Long totalClasses = classesService.countClasses();

		return new ResponseEntity<>(totalClasses, HttpStatus.OK);
	}

	// Check Class Exists
	@GetMapping("/exists")
	public ResponseEntity<Boolean> existsByClassNameAndSection(@RequestParam String className,
			@RequestParam String section) {

		boolean exists = classesService.existsByClassNameAndSection(className, section);

		return new ResponseEntity<>(exists, HttpStatus.OK);
	}
}