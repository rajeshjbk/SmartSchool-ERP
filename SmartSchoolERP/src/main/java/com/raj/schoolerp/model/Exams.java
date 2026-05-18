package com.raj.schoolerp.model;

import java.time.LocalDate;
import java.util.List;

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
@Table(name = "exams")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Exams {

    @Id
    @GeneratedValue(strategy =
            GenerationType.IDENTITY)
    private Long examId;

    @Column(length = 30)
    private String examName;

    @ManyToOne
    @JoinColumn(name = "classId")
    @JsonIgnoreProperties({
            "students",
            "subjects",
            "attendance",
            "exams"
    })
    private Classes classes;

    @NonNull
    @Column(length = 30)
    private String academicYear;

    @NonNull
    private LocalDate startDate;

    @NonNull
    private LocalDate endDate;

    private LocalDate resultDate;

    @NonNull
    @Enumerated(EnumType.STRING)
    private ExamStatus examStatus;

    @OneToMany(mappedBy = "exam",
            cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ExamSubjects>
            examSubjects;
}