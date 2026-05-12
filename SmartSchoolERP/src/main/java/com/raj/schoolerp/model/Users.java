package com.raj.schoolerp.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

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
	@Column(length = 20)
    private String password;

	@NonNull
    @Enumerated(EnumType.STRING)
    private UserRole role; // ADMIN, TEACHER, STUDENT, PARENT

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
    
    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private Students students;
       
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Students> listStudents;
    
    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
    private Set<Notices> notices;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Attendance> attendances;
  
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<LeaveApplications> appliedApplications;

    @OneToMany(mappedBy = "approvedBy", cascade = CascadeType.ALL)
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
