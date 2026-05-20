package com.raj.schoolerp.model;

import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "timetable")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Timetable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long timeTableId;

	@ManyToMany
	@JoinTable(name = "timetable_classes", joinColumns = @JoinColumn(name = "timetable_id"), inverseJoinColumns = @JoinColumn(name = "class_id"))
	@JsonIgnoreProperties({ "students", "subjects", "attendance", "timetables" })
	private List<Classes> classes;

	@ManyToOne
	@JoinColumn(name = "subjectId")
	@JsonIgnoreProperties({ "examSubjects", "timetables", "classes", "teacher" })
	private Subjects subject;

	@ManyToOne
	@JoinColumn(name = "teacherId")
	@JsonIgnoreProperties({ "subjects", "classes", "timetables"})
	private Teachers teachers;

	@NonNull
	@Enumerated(EnumType.STRING)
	private DayOfWeek dayOfWeek;

	@NonNull
	private Integer periodOfTime;

	@NonNull
	private LocalTime startTime;

	@NonNull
	private LocalTime endTime;

	@NonNull
	@Column(length = 30)
	private String roomNo;
}