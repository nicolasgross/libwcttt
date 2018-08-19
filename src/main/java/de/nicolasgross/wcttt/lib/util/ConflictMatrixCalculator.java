package de.nicolasgross.wcttt.lib.util;

import de.nicolasgross.wcttt.lib.model.*;

import java.util.Map;

public class ConflictMatrixCalculator {

	private Semester semester;

	public ConflictMatrixCalculator(Semester semester) {
		this.semester = semester;
	}

	public Map<Course, Map<Course, ConstraintViolations>> calcCourseCourseConflicts() {
		// TODO
		return null;
	}

	public Map<Course, Map<InternalRoom, ConstraintViolations>> calcCourseRoomConflicts() {
		// TODO
		return null;
	}

	public Map<Teacher, Map<Period, ConstraintViolations>> calcTeacherPeriodConflicts() {
		// TODO
		return null;
	}


}
