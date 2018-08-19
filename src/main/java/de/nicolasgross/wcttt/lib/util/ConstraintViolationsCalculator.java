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

	public ConstraintViolations calcAssignmentViolations(TimetablePeriod period,
	                                                     TimetableAssignment assignment,
	                                                     Timetable timetable) {
		ConstraintViolations violations = new ConstraintViolations();

		violations.getViolations().addAll(Collections.nCopies(
				h1ViolationCount(period, assignment), ConstraintType.h1));

		violations.getViolations().addAll(Collections.nCopies(
				h2ViolationCount(period, assignment), ConstraintType.h2));

		violations.getViolations().addAll(Collections.nCopies(
				h3ViolationCount(period, assignment), ConstraintType.h3));

		violations.getViolations().addAll(Collections.nCopies(
				h4ViolationCount(period, assignment), ConstraintType.h4));

		violations.getViolations().addAll(Collections.nCopies(
				h5ViolationCount(period, assignment), ConstraintType.h5));

		violations.getViolations().addAll(Collections.nCopies(
				h6ViolationCount(period, assignment), ConstraintType.h6));

		violations.getViolations().addAll(Collections.nCopies(
				h7ViolationCount(period, assignment), ConstraintType.h7));

		violations.getViolations().addAll(Collections.nCopies(
				h8ViolationCount(period, assignment), ConstraintType.h8));

		violations.getViolations().addAll(Collections.nCopies(
				h9ViolationCount(period, assignment), ConstraintType.h9));

		violations.getViolations().addAll(Collections.nCopies(
				h10ViolationCount(period, assignment), ConstraintType.h10));

		int numberOfHardConstraintViolation = violations.getViolations().size();
		if (numberOfHardConstraintViolation > 0) {
			violations.setHardConstraintViolated(true);
		}

		// TODO

		return violations;
	}

	private int h1ViolationCount(TimetablePeriod period,
	                             TimetableAssignment assignment) {
		if (!assignment.getSession().isLecture()) {
			return 0;
		}
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

		if (assignment.getSession().isLecture()) {
			return 0;
		} else if (assignment.getSession().getCourse().getPracticals().size() == 1) {
			// If the course has only one practical, it is treated like a lecture
			return h1ViolationCount(period, assignment);
		}
		int counter = 0;
		for (TimetableAssignment otherAssignment : period.getAssignments()) {
			if (!otherAssignment.equals(assignment) &&
					otherAssignment.getSession().getCourse().equals(
							assignment.getSession().getCourse()) &&
					otherAssignment.getSession().isLecture()) {
				counter++;
			}
		}
		return counter;
	}

	private int h3ViolationCount(TimetablePeriod period,
	                             TimetableAssignment assignment) {
		int counter = 0;
		for (TimetableAssignment otherAssignment : period.getAssignments()) {
			if (!otherAssignment.equals(assignment) &&
					otherAssignment.getRoom().equals(assignment.getRoom())) {
				counter++;
			}
		}
		return counter;
	}

	private int h4ViolationCount(TimetablePeriod period,
	                             TimetableAssignment assignment) {
		if (!assignment.getSession().isLecture()) {
			return 0;
		}
		int counter = 0;
		for (Curriculum curriculum : semester.getCurricula()) {
			if (curriculum.getCourses().contains(
					assignment.getSession().getCourse())) {
				for (TimetableAssignment otherAssignment : period.getAssignments()) {
					if (!otherAssignment.equals(assignment) &&
							curriculum.getCourses().contains(
									otherAssignment.getSession().getCourse())) {
						counter++;
					}
				}
			}
		}
		return counter;
	}

	private int h5ViolationCount(TimetablePeriod period,
	                             TimetableAssignment assignment) {
		if (assignment.getSession().isLecture()) {
			return 0;
		}
		int numberOfCoursePracticals =
				assignment.getSession().getCourse().getPracticals().size();
		if (numberOfCoursePracticals == 1 ) {
			return h4ViolationCount(period, assignment);
		}
		for (TimetableAssignment otherAssignment : period.getAssignments()) {
			if (otherAssignment.getSession().getCourse().equals(
					assignment.getSession().getCourse()) &&
					!assignment.getSession().isLecture()) {
				numberOfCoursePracticals--;
			}
		}
		if (numberOfCoursePracticals == 0) {
			return assignment.getSession().getCourse().getPracticals().size();
		} else {
			return 0;
		}
	}

	private int h6ViolationCount(TimetablePeriod period,
	                             TimetableAssignment assignment) {
		int counter = 0;
		for (TimetableAssignment otherAssignment : period.getAssignments()) {
			if (!otherAssignment.equals(assignment) &&
					otherAssignment.getSession().getTeacher().equals(
							assignment.getSession().getTeacher())) {
				counter++;
			}
		}
		return counter;
	}

	private int h7ViolationCount(TimetablePeriod period,
	                             TimetableAssignment assignment) {
		if (assignment.getSession().getTeacher().getUnavailablePeriods().
				contains(period)) {
			return 1;
		} else {
			return 0;
		}
	}

	private int h8ViolationCount(TimetablePeriod period,
	                             TimetableAssignment assignment) {
		if (assignment.getSession().getPreAssignment().isPresent() &&
				!assignment.getSession().getPreAssignment().equals(period)) {
			return 1;
		} else {
			return 0;
		}
	}

	private int h9ViolationCount(TimetablePeriod period,
	                             TimetableAssignment assignment) {
		if (assignment.getRoom() instanceof InternalRoom &&
				((InternalRoom) assignment.getRoom()).getHolder().isPresent() &&
				!((InternalRoom) assignment.getRoom()).getHolder().equals(
						assignment.getSession().getCourse().getChair())) {
			return 1;
		} else {
			return 0;
		}
	}

	private int h10ViolationCount(TimetablePeriod period,
	                              TimetableAssignment assignment) {
		if (assignment.getRoom() instanceof InternalRoom &&
				assignment.getSession() instanceof InternalSession &&
				((InternalRoom) assignment.getRoom()).getFeatures().compareTo(
						((InternalSession) assignment.getSession()).
								getRoomRequirements()) < 0) {
			return 1;
		} else {
			return 0;
		}
	}
}

