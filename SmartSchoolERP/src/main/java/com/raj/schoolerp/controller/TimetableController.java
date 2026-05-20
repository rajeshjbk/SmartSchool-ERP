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

import com.raj.schoolerp.DTO.TimetableDTO;
import com.raj.schoolerp.exception.TimetableException;
import com.raj.schoolerp.model.DayOfWeek;
import com.raj.schoolerp.model.Timetable;
import com.raj.schoolerp.service.TimetableService;

@RestController
@RequestMapping("/schoolerp/timetable")
@CrossOrigin(origins = "http://localhost:5173")
public class TimetableController {

	@Autowired
	private TimetableService timetableService;

	@PostMapping("/add")
	public Timetable addTimetable(@RequestBody TimetableDTO timetableDTO) throws TimetableException {

		return timetableService.addTimetable(timetableDTO);
	}

	@PutMapping("/update/{timeTableId}")
	public Timetable updateTimetable(@PathVariable Long timeTableId, @RequestBody TimetableDTO timetableDTO)
			throws TimetableException {

		return timetableService.updateTimetable(timeTableId, timetableDTO);
	}

	@DeleteMapping("/delete/{timeTableId}")
	public String deleteTimetable(@PathVariable Long timeTableId) throws TimetableException {

		return timetableService.deleteTimetable(timeTableId);
	}

	@GetMapping("/{timeTableId}")
	public Timetable getTimetableById(@PathVariable Long timeTableId) throws TimetableException {

		return timetableService.getTimetableById(timeTableId);
	}

	@GetMapping("/all")
	public List<Timetable> getAllTimetables() throws TimetableException {

		return timetableService.getAllTimetables();
	}

	@GetMapping("/class/{classId}")
	public List<Timetable> getTimetableByClassId(@PathVariable Long classId) throws TimetableException {

		return timetableService.getTimetableByClassId(classId);
	}

	@GetMapping("/teacher/{teacherId}")
	public List<Timetable> getTimetableByTeacherId(@PathVariable Long teacherId) throws TimetableException {

		return timetableService.getTimetableByTeacherId(teacherId);
	}

	@GetMapping("/subject/{subjectId}")
	public List<Timetable> getTimetableBySubjectId(@PathVariable Long subjectId) throws TimetableException {

		return timetableService.getTimetableBySubjectId(subjectId);
	}

	@GetMapping("/day/{dayOfWeek}")
	public List<Timetable> getTimetableByDay(@PathVariable DayOfWeek dayOfWeek) throws TimetableException {

		return timetableService.getTimetableByDay(dayOfWeek);
	}

	@GetMapping("/class-day")
	public List<Timetable> getClassTimetableByDay(@RequestParam Long classId, @RequestParam DayOfWeek dayOfWeek)
			throws TimetableException {

		return timetableService.getClassTimetableByDay(classId, dayOfWeek);
	}

	@GetMapping("/student/{userId}")
	public List<Timetable> getStudentTimetable(@PathVariable Long userId) throws TimetableException {

		return timetableService.getStudentTimetable(userId);
	}

	@GetMapping("/parent/{parentId}")
	public ResponseEntity<List<Timetable>> getParentTimetableHandler(@PathVariable Long parentId)
			throws TimetableException {

		List<Timetable> timetable = timetableService.getParentTimetable(parentId);

		return new ResponseEntity<>(timetable, HttpStatus.OK);
	}
}