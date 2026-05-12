package com.raj.schoolerp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "results")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Results {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long resultId;

	@ManyToOne
	@JoinColumn(name = "studentId")
	@JsonIgnoreProperties({ "user", "attendances", "results" })
	private Students student;

	@ManyToOne
	@JoinColumn(name = "examSubjectId")
	@JsonIgnoreProperties({ "results", "exam", "subjects" })
	private ExamSubjects examSubjects;

	@NonNull
	private Double marksObtained;

	@NonNull
	@Column(length = 20)
	private String grade;

	@NonNull
	private Double gradePoint;

	@NonNull
	private Integer rankInClass;

	@NonNull
	private Boolean isAbsent;

	@NonNull
	@Column(length = 100)
	private String remarks;
}