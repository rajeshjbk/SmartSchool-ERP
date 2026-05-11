package com.raj.schoolerp.entity;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

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
	private Date startDate;
	
	@NonNull
	private Date endDate;
	
	@NonNull
	private Date resultDate;
	
	@NonNull
	@Enumerated(EnumType.STRING)
	private ExamStatus examStatus;
	
	@OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
	private List<ExamSubjects> examSubjects;

}
