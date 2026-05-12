package com.raj.schoolerp.model;

public enum TeacherStatus {

	ACTIVE,        // Currently working

	INACTIVE,      // Temporarily inactive (leave, sabbatical)

	ON_LEAVE,      // Approved leave (short-term)

	RESIGNED,      // Left job voluntarily

	TERMINATED,    // Removed by organization

	RETIRED        // Completed service (long-term exit)
}
