package com.raj.schoolerp.model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "tbl_classes")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Classes {

    @Id
    @GeneratedValue(strategy =
            GenerationType.IDENTITY)
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

    @OneToMany(mappedBy = "classes",
            cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Subjects> subjects;

    @OneToMany(mappedBy = "classes",
            cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Students> students;

    @ManyToOne
    @JoinColumn(name = "teacherId")
    @JsonIgnoreProperties({
            "classes",
            "user"
    })
    private Teachers teacher;

    @OneToMany(mappedBy = "classes",
            cascade = CascadeType.ALL)
    @JsonIgnore
    private List<FeeStructures>
            feeStructures;

    @OneToMany(mappedBy = "classes",
            cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Notices> notices;

    @OneToMany(mappedBy = "classes",
            cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Exams> exams;

    @ManyToMany(mappedBy = "classes",
            cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Timetable> timetables;

    @OneToMany(mappedBy = "classes",
            cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Attendance> attendance;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
}