package com.raj.schoolerp.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
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
@Table(name = "exam_subjects")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class ExamSubjects {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long examSubId;
	
	@ManyToOne
	@JoinColumn(name = "examId")
	private Exams exam;
	
	@ManyToOne
	@JoinColumn(name = "subjectId")
	private Subjects subjects;
	
	@NonNull
	private LocalDate examDate;
	
	@NonNull
	private Integer durationMin;
	
	@NonNull
	private Integer maxMarks;
	
	@NonNull
	private Integer passMarks;
	
	@CreationTimestamp
	private LocalTime startTime;
	
	@NonNull
	private String roomNo;
	
	@OneToMany(mappedBy = "examSubjects", cascade = CascadeType.ALL)
	private List<Results> results;
	
	
}
