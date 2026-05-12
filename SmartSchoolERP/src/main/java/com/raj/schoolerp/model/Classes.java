package com.raj.schoolerp.model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.context.annotation.Fallback;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
@Table(name = "tbl_classes")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Classes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long classId;
	
	@NonNull
	@Column(length = 30)
	private String className;
	
	@NonNull
	@Column(length = 30)
	private String section;
	
	@NonNull
	@Column(length = 30)
	private String academicYear;
	
	@NonNull
	@Column(length = 30)
	private String roomNo;
	
	@NonNull
	private Integer capacity;
		
	@OneToMany(mappedBy = "classes", cascade = CascadeType.ALL)
	private List<Subjects> subjects;
	
	@OneToMany(mappedBy = "classes", cascade = CascadeType.ALL)
	private List<Students> students;
	
	@ManyToOne
	@JoinColumn(name = "teacherId")
	private Teachers teacher;
	
	@OneToMany(mappedBy = "classes", cascade = CascadeType.ALL)
	private List<FeeStructures> feeStructures;
	
	@OneToMany(mappedBy = "classes", cascade = CascadeType.ALL)
	private List<Notices> notices;
	
	@OneToMany(mappedBy = "classes", cascade = CascadeType.ALL)
	private List<Exams> exams;
	
	@ManyToMany(mappedBy = "classes", cascade = CascadeType.ALL)
	private List<Timetable> timetables;
	
	@OneToMany(mappedBy = "classes", cascade = CascadeType.ALL)
	private List<Attendance> attendance;
	
	@CreationTimestamp
	@Column(insertable = false)
	private LocalDateTime createdAt;
}
