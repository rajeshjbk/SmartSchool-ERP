package com.raj.schoolerp.entity;

public enum BookStatus {

	AVAILABLE,      // Book is available for issue

	ISSUED,         // Currently issued to a student/teacher

	RESERVED,       // Reserved by someone

	LOST,           // Book reported lost

	DAMAGED,        // Book is damaged/unusable

	MAINTENANCE,    // Under repair or processing

	REMOVED         // Removed from library (obsolete/discarded)
}