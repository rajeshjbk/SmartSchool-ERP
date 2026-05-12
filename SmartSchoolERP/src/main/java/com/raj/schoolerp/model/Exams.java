package com.raj.schoolerp.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.CascadeType;
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
@Table(name = "exams")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Exams {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long examId;
	
	@Column(length = 30)
	private String examName;
	
	@ManyToOne
	@JoinColumn(name = "classId")
	private Classes classes;
	
	@NonNull
	@Column(length = 30)
	private String academicYear;
	
	@CreatedDate
	@Column(updatable = false)
	private LocalDate startDate;
	
	@NonNull
	private LocalDate endDate;
	
	private LocalDate resultDate;
	
	@NonNull
	@Enumerated(EnumType.STRING)
	private ExamStatus examStatus;
	
	@OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
	private List<ExamSubjects> examSubjects;

}
