package com.raj.schoolerp.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@Table(name = "teachers")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Teachers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long teacherId;

	@NonNull
	@Column(unique = true)
	private String employeeId;

	@NonNull
	@Column(length = 30)
	private String department;

	@NonNull
	@Column(length = 30)
	private String designation;

	@CreationTimestamp
	@Column(updatable = false)
	private LocalDate joiningDate;

	@NonNull
	private Double salary;

	@NonNull
	private String qualification;

	@NonNull
	@Enumerated(EnumType.STRING)
	private TeacherStatus teacherStatus;

	@ManyToOne
	@JoinColumn(name = "userId")
	@JsonIgnoreProperties({ "password", "teachers" })
	private Users user;

	@OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Subjects> subjects;

	@OneToMany(mappedBy = "issuedBy", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<BookIssues> issuedBooks;
	
	@OneToMany(mappedBy = "teacher")
	@JsonIgnore
	private List<Classes> classes;

	@OneToMany(mappedBy = "teachers")
	@JsonIgnore
	private List<Timetable> timetables;
}