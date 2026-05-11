package com.raj.schoolerp.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "students")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Students {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long studentId;
	
	@OneToOne
	@JoinColumn(name = "userId")
	private Users user;
	
	@ManyToOne
	@JoinColumn(name = "parentId")
	private Users users;
	
	@NonNull
	private String admissionNo;
	
	@NonNull
	private String fullName;
	
	@NonNull
	private LocalDate dob;
	
	@NonNull
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@ManyToOne
	@JoinColumn(name = "classId")
	private Classes classes;
	
	@NonNull
	private String academicYear;
	
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDate admissionDate;
	
	@NonNull
	@Enumerated(EnumType.STRING)
	private StudentStatus studentStatus;
     
	@OneToMany(mappedBy = "students", cascade = CascadeType.ALL)
	private List<Attendance> attendance;
	
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
	private Set<BookIssues> bookIssues; 
	
	@OneToMany(mappedBy = "issuedBy", cascade = CascadeType.ALL)
	private Set<BookIssues> bookIssuedBy; 
	
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
	private List<FeeTransactions> feeTransactions;
	
	@OneToMany(mappedBy = "student")
	private List<Results> results;
	
}
