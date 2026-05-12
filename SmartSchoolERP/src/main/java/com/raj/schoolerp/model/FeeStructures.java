package com.raj.schoolerp.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "fee_structures")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class FeeStructures {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long feeStructId;

	@ManyToOne
	@JoinColumn(name = "classId")
	@JsonIgnoreProperties({ "students", "subjects", "attendance", "feeStructures" })
	private Classes classes;

	@NonNull
	private String feeType;

	@NonNull
	private Double amount;

	@NonNull
	@Enumerated(EnumType.STRING)
	private Frequency frequency;

	@NonNull
	private Integer dueDay;

	@NonNull
	private String academicYear;

	@NonNull
	private Boolean isMandatory;

	@NonNull
	private Double lateFine;

	@OneToMany(mappedBy = "feeStructures", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<FeeTransactions> feeTransactions;
}