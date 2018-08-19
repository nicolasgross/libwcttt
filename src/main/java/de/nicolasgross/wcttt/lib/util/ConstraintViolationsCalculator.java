package de.nicolasgross.wcttt.lib.util;

import de.nicolasgross.wcttt.lib.model.*;

import java.util.*;

public class ConstraintViolationsCalculator {

	private Semester semester;
	private Map<Course, Map<Course, ConstraintViolations>> courseCourseConflicts;
	private Map<Course, Map<InternalRoom, ConstraintViolations>> courseRoomConflicts;
	private Map<Teacher, Map<Period, ConstraintViolations>> teacherPeriodConflicts;

	public ConstraintViolationsCalculator(Semester semester) {
		this.semester = semester;
		ConflictMatrixCalculator confCalc = new ConflictMatrixCalculator(semester);
		this.courseCourseConflicts = confCalc.calcCourseCourseConflicts();
		this.courseRoomConflicts = confCalc.calcCourseRoomConflicts();
		this.teacherPeriodConflicts = confCalc.calcTeacherPeriodConflicts();
	}

	public ConstraintViolations calcAssignmentViolations(TimetablePeriod period,
	                                                     TimetableAssignment assignment,
	                                                     Timetable timetable) {
		ConstraintViolations violations = new ConstraintViolations();

		if (assignment.getSession().isLecture()) {
			violations.getViolations().addAll(Collections.nCopies(
					h1ViolationCount(period, assignment), ConstraintType.h1));
		} else {
			violations.getViolations().addAll(Collections.nCopies(
					h2ViolationCount(period, assignment), ConstraintType.h2));
		}

		return violations;
	}

	private int h1ViolationCount(TimetablePeriod period,
	                             TimetableAssignment assignment) {
		int counter = 0;
		for (TimetableAssignment otherAssignment : period.getAssignments()) {
			if (!otherAssignment.equals(assignment) &&
					otherAssignment.getSession().getCourse().equals(
							assignment.getSession().getCourse())) {
				counter++;
			}
		}
		return counter;
	}

	private int h2ViolationCount(TimetablePeriod period,
	                             TimetableAssignment assignment) {
		// If the course has only one practical, it is treated like a lecture
		if (assignment.getSession().getCourse().getPracticals().size() == 1) {
			return h1ViolationCount(period, assignment);
		}
		int counter = 0;
		for (TimetableAssignment otherAssignment : period.getAssignments()) {
			if (!otherAssignment.equals(assignment) &&
					otherAssignment.getSession().getCourse().equals(assignment.getSession().getCourse()) &&
					otherAssignment.getSession().isLecture()) {
				counter++;
			}
		}
		return counter;
	}

	public double calcTimetablePenalty(Timetable timetable) {
		double penalty = 0.0;
		for (TimetableDay day : timetable.getDays()) {
			for (TimetablePeriod period : day.getPeriods()) {
				for (TimetableAssignment assignment : period.getAssignments()) {
					ConstraintViolations conflict = calcAssignmentViolations(
							period, assignment, timetable);
					for (ConstraintType type : conflict.getViolations()) {
						double weighting = semester.getConstrWeightings().
								getWeighting(type);
						if (weighting != -1.0) {
							penalty += weighting * 1.0;
						}
					}
				}
			}
		}
		return penalty;
	}
}
