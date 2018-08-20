package de.nicolasgross.wcttt.lib.util;

import de.nicolasgross.wcttt.lib.model.*;

import java.util.Map;

public class ConflictMatrixCalculator {

	private Semester semester;

	public ConflictMatrixCalculator(Semester semester) {
		this.semester = semester;
	}

	public Map<Course, Map<Course, Conflict>> calcCourseCourseConflicts() {
		// TODO
		return null;
	}

	public Map<Course, Map<InternalRoom, Conflict>> calcCourseRoomConflicts() {
		// TODO
		return null;
	}

	public Map<Teacher, Map<Period, Conflict>> calcTeacherPeriodConflicts() {
		// TODO
		return null;
	}


}
