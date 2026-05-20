package com.raj.schoolerp.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "subjects")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Subjects {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long subjectId;

	@NonNull
	@Column(length = 30)
	private String subjectName;

	@NonNull
	@Column(length = 30, unique = true)
	private String subjectCode;

	@NonNull
	@Enumerated(EnumType.STRING)
	private SubjectType subjectType;

	@NonNull
	private Integer creditHrs;

	@NonNull
	private Boolean isElective;

	@ManyToOne
	@JoinColumn(name = "classId")
	private Classes classes;

	@ManyToOne
	@JoinColumn(name = "teacherId")
	@JsonIgnoreProperties({ "subjects", "classes" })
	private Teachers teacher;

	@OneToMany(mappedBy = "subjects")
	@JsonIgnore
	private List<ExamSubjects> examSubjects;

	@OneToMany(mappedBy = "subject")
	@JsonIgnore
	private List<Timetable> timetables;
}