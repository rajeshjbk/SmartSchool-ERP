package com.raj.schoolerp.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@NonNull
	@Column(unique = true)
	private String userName;

	@NonNull
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;

	@NonNull
	@Enumerated(EnumType.STRING)
	private UserRole role;

	@NonNull
	@Column(length = 40)
	private String fullName;

	@NonNull
	@Column(length = 40)
	private String email;

	@NonNull
	@Column(length = 20)
	private String phone;

	private Boolean active = true;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonIgnore
	private Students students;

	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Students> listStudents;

	@OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Notices> notices;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Attendance> attendances;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<LeaveApplications> appliedApplications;

	@OneToMany(mappedBy = "approvedById", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<LeaveApplications> approvedApplications;

	@OneToMany(mappedBy = "user")
	private List<Teachers> teachers;

	@UpdateTimestamp
	@Column(insertable = false)
	private LocalDate lastLogin;

	@CreationTimestamp
	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(insertable = false)
	private LocalDateTime updatedAt;
}