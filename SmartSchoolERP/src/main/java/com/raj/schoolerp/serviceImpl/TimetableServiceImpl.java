package com.raj.schoolerp.serviceImpl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raj.schoolerp.DTO.TimetableDTO;
import com.raj.schoolerp.exception.TimetableException;
import com.raj.schoolerp.model.Classes;
import com.raj.schoolerp.model.DayOfWeek;
import com.raj.schoolerp.model.Students;
import com.raj.schoolerp.model.Subjects;
import com.raj.schoolerp.model.Teachers;
import com.raj.schoolerp.model.Timetable;
import com.raj.schoolerp.model.Users;
import com.raj.schoolerp.repository.ClassesRepository;
import com.raj.schoolerp.repository.StudentsRepository;
import com.raj.schoolerp.repository.SubjectsRepository;
import com.raj.schoolerp.repository.TeachersRepository;
import com.raj.schoolerp.repository.TimetableRepository;
import com.raj.schoolerp.repository.UsersRepository;
import com.raj.schoolerp.service.TimetableService;

@Service
public class TimetableServiceImpl implements TimetableService {

	@Autowired
	private TimetableRepository timetableRepo;

	@Autowired
	private UsersRepository usersRepo;

	@Autowired
	private StudentsRepository studentsRepo;

	@Autowired
	private SubjectsRepository subjectsRepository;

	@Autowired
	private ClassesRepository classesRepository;

	@Autowired
	private TeachersRepository teachersRepository;

	@Override
	public Timetable addTimetable(TimetableDTO timetableDTO) throws TimetableException {

		Timetable newTimetable = new Timetable();

		BeanUtils.copyProperties(timetableDTO, newTimetable);

		// Fetch Class
		List<Classes> classes = classesRepository.findAllById(timetableDTO.getClassIds());

		if (classes.isEmpty()) {

			throw new TimetableException("Classes Not Found");
		}

		// Fetch Teacher
		Teachers teacher = teachersRepository.findById(timetableDTO.getTeacherId())
				.orElseThrow(() -> new TimetableException("Teacher Not Found"));

		// Fetch Subject
		Subjects subject = subjectsRepository.findById(timetableDTO.getSubjectId())
				.orElseThrow(() -> new TimetableException("Subject Not Found"));

		// Set Relations
		newTimetable.setClasses(classes);

		newTimetable.setSubject(subject);

		newTimetable.setTeachers(teacher);

		return timetableRepo.save(newTimetable);
	}

	@Override
	public Timetable updateTimetable(Long timeTableId, TimetableDTO timetableDTO) throws TimetableException {

		Timetable existTimetable = timetableRepo.findById(timeTableId)
				.orElseThrow(() -> new TimetableException("Timetable Not Found"));

		BeanUtils.copyProperties(timetableDTO, existTimetable);

		// Fetch Class
		List<Classes> classes = classesRepository.findAllById(timetableDTO.getClassIds());

		if (classes.isEmpty()) {

			throw new TimetableException("Class Not Found");
		}

		// Fetch Subject
		Subjects subject = subjectsRepository.findById(timetableDTO.getSubjectId())
				.orElseThrow(() -> new TimetableException("Subject Not Found"));

		// Fetch Teacher
		Teachers teacher = teachersRepository.findById(timetableDTO.getTeacherId())
				.orElseThrow(() -> new TimetableException("Teacher Not Found"));

		// Set Relations
		existTimetable.setClasses(classes);

		existTimetable.setSubject(subject);

		existTimetable.setTeachers(teacher);

		return timetableRepo.save(existTimetable);
	}

	@Override
	public String deleteTimetable(Long timeTableId) throws TimetableException {

		timetableRepo.findById(timeTableId).orElseThrow(() -> new TimetableException("Timetable Not Found"));

		timetableRepo.deleteById(timeTableId);

		return "Timetable deleted with ID: " + timeTableId;
	}

	@Override
	public Timetable getTimetableById(Long timeTableId) throws TimetableException {

		return timetableRepo.findById(timeTableId).orElseThrow(() -> new TimetableException("Wrong Timetable Id"));
	}

	@Override
	public List<Timetable> getAllTimetables() throws TimetableException {

		return timetableRepo.findAll();
	}

	@Override
	public List<Timetable> getTimetableByClassId(Long classId) throws TimetableException {

		List<Timetable> timetables = timetableRepo.findTimetableByClassId(classId);

		if (timetables.isEmpty()) {

			throw new TimetableException("No Timetable Found For This Class");
		}

		return timetables;
	}

	@Override
	public List<Timetable> getTimetableByTeacherId(Long teacherId) throws TimetableException {

		List<Timetable> timetables = timetableRepo.findTimetableByTeacherId(teacherId);

		if (timetables.isEmpty()) {

			throw new TimetableException("No Timetable Found For This Teacher");
		}

		return timetables;
	}

	@Override
	public List<Timetable> getTimetableBySubjectId(Long subjectId) throws TimetableException {

		List<Timetable> timetables = timetableRepo.findTimetableBySubjectId(subjectId);

		if (timetables.isEmpty()) {

			throw new TimetableException("No Timetable Found For This Subject");
		}

		return timetables;
	}

	@Override
	public List<Timetable> getTimetableByDay(DayOfWeek dayOfWeek) throws TimetableException {

		List<Timetable> timetables = timetableRepo.findTimetableByDay(dayOfWeek);

		if (timetables.isEmpty()) {

			throw new TimetableException("No Timetable Found For " + dayOfWeek);
		}

		return timetables;
	}

	@Override
	public List<Timetable> getClassTimetableByDay(Long classId, DayOfWeek dayOfWeek) throws TimetableException {

		List<Timetable> timetables = timetableRepo.findClassTimetableByDay(classId, dayOfWeek);

		if (timetables.isEmpty()) {

			throw new TimetableException("No Timetable Found");
		}

		return timetables;
	}

	@Override
	public List<Timetable> getStudentTimetable(Long userId) throws TimetableException {

		// Fetch User
		Users user = usersRepo.findById(userId).orElseThrow(() -> new TimetableException("User Not Found"));

		// Fetch Student by User
		Students student = studentsRepo.findByUser(user).orElseThrow(() -> new TimetableException("Student Not Found"));

		// Check Class
		if (student.getClasses() == null) {
			throw new TimetableException("Student class not assigned");
		}

		// Get Timetable by Class
		List<Timetable> timetableList = timetableRepo.findByClassesClassId(student.getClasses().getClassId());

		if (timetableList.isEmpty()) {
			throw new TimetableException("No timetable found");
		}

		return timetableList;
	}

	@Override
	public List<Timetable> getParentTimetable(Long parentId) throws TimetableException {

		List<Timetable> timetable = timetableRepo.getParentTimetable(parentId);

		if (timetable.isEmpty()) {
			throw new TimetableException("No Timetable Found");
		}

		return timetable;
	}
}