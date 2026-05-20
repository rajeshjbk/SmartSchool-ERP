package com.raj.schoolerp.model;

import java.time.LocalDateTime;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

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
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "attendance")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Attendance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long attendanceId;

	@ManyToOne
	@JoinColumn(name = "studentId")
	@JsonIgnoreProperties({ "students", "listStudents", "attendances", "leaveApplications" })
	private Students students;

	@CreationTimestamp
	@Column(updatable = false)
	private Date date;

	@NonNull
	@Enumerated(EnumType.STRING)
	private AttendanceStatus attendanceStatus;

	private String remarks;

	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createdAt;

	@ManyToOne
	@JoinColumn(name = "markedBy")
	@JsonIgnoreProperties({ "password", "students", "listStudents", "attendances" })
	private Users user;

	@ManyToOne
	@JoinColumn(name = "classId")
	@JsonIgnoreProperties({ "students"})
	private Classes classes;
}