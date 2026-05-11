package com.raj.schoolerp.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
@Table(name = "notices")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Notices {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long noticeId;
	
	@NonNull
	@Column(length = 40)
	private String title;
	
	@NonNull
	@Column(length = 30)
	private String content;
	
	@Enumerated(EnumType.STRING)
	@NonNull
	private Audience audience;
	
	@ManyToOne
	@JoinColumn(name = "classId")
	private Classes classes;
	
	@ManyToOne
	@JoinColumn(name = "createdBy")
	private Users createdBy;
	

	@CreationTimestamp
	private LocalDate publishDate;
	
	@UpdateTimestamp
	private LocalDate expiryDate;
	
	@NonNull
	private String attachment;
	
	@NonNull
	private Boolean isUrgent;
	
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createdAt;
}
