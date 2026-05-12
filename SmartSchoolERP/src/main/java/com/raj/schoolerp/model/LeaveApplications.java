package com.raj.schoolerp.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
@Table(name = "leave_applications")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class LeaveApplications {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long leaveId;

	@NonNull
	@Enumerated(EnumType.STRING)
	private LeaveType leaveType;

	@NonNull
	private LocalDate fromDate;

	@NonNull
	private LocalDate toDate;

	@NonNull
	private Integer totalDays;

	@NonNull
	private String reason;

	@NonNull
	@Enumerated(EnumType.STRING)
	private LeaveStatus leaveStatus;

	@ManyToOne
	@JoinColumn(name = "userId")
	@JsonIgnoreProperties({ "password", "appliedApplications", "approvedApplications" })
	private Users user;

	@ManyToOne
	@JoinColumn(name = "approvedBy")
	@JsonIgnoreProperties({ "password", "appliedApplications", "approvedApplications" })
	private Users approvedBy;

	private String rejectionNote;

	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime appliedOn;

	@UpdateTimestamp
	@Column(insertable = false)
	private LocalDateTime approvedOn;
}