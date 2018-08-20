package de.nicolasgross.wcttt.lib.util;

import de.nicolasgross.wcttt.lib.model.*;

import java.util.*;

public class ConstraintViolationsCalculator {

	private Semester semester;
	private Map<Course, Map<Course, Conflict>> courseCourseConflicts;
	private Map<Course, Map<InternalRoom, Conflict>> courseRoomConflicts;
	private Map<Teacher, Map<Period, Conflict>> teacherPeriodConflicts;

	public ConstraintViolationsCalculator(Semester semester) {
		this.semester = semester;
		ConflictMatrixCalculator confCalc = new ConflictMatrixCalculator(semester);
		this.courseCourseConflicts = confCalc.calcCourseCourseConflicts();
		this.courseRoomConflicts = confCalc.calcCourseRoomConflicts();
		this.teacherPeriodConflicts = confCalc.calcTeacherPeriodConflicts();
	}

	public List<ConstraintType> calcAssignmentHardViolations(
			TimetablePeriod period, TimetableAssignment assignment) {
		List<ConstraintType> hardViolations = new LinkedList<>();

		hardViolations.addAll(Collections.nCopies(
				h1ViolationCount(period, assignment), ConstraintType.h1));

		hardViolations.addAll(Collections.nCopies(
				h2ViolationCount(period, assignment), ConstraintType.h2));

		hardViolations.addAll(Collections.nCopies(
				h3ViolationCount(period, assignment), ConstraintType.h3));

		hardViolations.addAll(Collections.nCopies(
				h4ViolationCount(period, assignment), ConstraintType.h4));

		hardViolations.addAll(Collections.nCopies(
				h5ViolationCount(period, assignment), ConstraintType.h5));

		hardViolations.addAll(Collections.nCopies(
				h6ViolationCount(period, assignment), ConstraintType.h6));

		hardViolations.addAll(Collections.nCopies(
				h7ViolationCount(period, assignment), ConstraintType.h7));

		hardViolations.addAll(Collections.nCopies(
				h8ViolationCount(period, assignment), ConstraintType.h8));

		hardViolations.addAll(Collections.nCopies(
				h9ViolationCount(assignment), ConstraintType.h9));

		hardViolations.addAll(Collections.nCopies(
				h10ViolationCount(assignment), ConstraintType.h10));

		return hardViolations;
	}

	public double calcTimetablePenalty(Timetable timetable) {
		double penalty = 0.0;

		List<ConstraintType> s6Violations = Collections.nCopies(
				s6ViolationCount(timetable), ConstraintType.s6);
		penalty += calculateViolationsPenalty(s6Violations);

		for (Course course : semester.getCourses()) {
			List<ConstraintType> violations =
					calcCourseSoftViolations(course, timetable);
			penalty += calculateViolationsPenalty(violations);
		}

		for (Curriculum curriculum : semester.getCurricula()) {
			List<ConstraintType> violations =
					calcCurriculumSoftViolations(curriculum, timetable);
			penalty += calculateViolationsPenalty(violations);
		}

		for (TimetableDay day : timetable.getDays()) {
			for (TimetablePeriod period : day.getPeriods()) {
				for (TimetableAssignment assignment : period.getAssignments()) {
					List<ConstraintType> violations = calcAssignmentSoftViolations(
							period, assignment, timetable);
					penalty += calculateViolationsPenalty(violations);
				}
			}
		}

		return penalty;
	}

	private double calculateViolationsPenalty(List<ConstraintType> violations) {
		double penalty = 0.0;
		for (ConstraintType violation : violations) {
			double weighting = semester.getConstrWeightings().
					getWeighting(violation);
			if (weighting != -1.0) {
				penalty += weighting * 1.0;
			}
		}
		return penalty;
	}

	private List<ConstraintType> calcCourseSoftViolations(
			Course course, Timetable timetable) {
		List<ConstraintType> violations = new LinkedList<>();

		violations.addAll(Collections.nCopies(
				s2ViolationCount(course, timetable), ConstraintType.s2));

		violations.addAll(Collections.nCopies(
				s4ViolationCount(course, timetable), ConstraintType.s4));

		return violations;
	}

	private List<ConstraintType> calcCurriculumSoftViolations(
			Curriculum curriculum, Timetable timetable) {
		List<ConstraintType> violations = new LinkedList<>();

		violations.addAll(Collections.nCopies(
				s7ViolationCount(curriculum, timetable), ConstraintType.s1));

		return violations;
	}

	private List<ConstraintType> calcAssignmentSoftViolations(
			TimetablePeriod period, TimetableAssignment assignment,
			Timetable timetable) {
		List<ConstraintType> violations = new LinkedList<>();

		violations.addAll(Collections.nCopies(
				s1ViolationCount(assignment), ConstraintType.s1));

		violations.addAll(Collections.nCopies(
				s3ViolationCount(period, assignment, timetable),
				ConstraintType.s3));

		violations.addAll(Collections.nCopies(
				s5ViolationCount(period, assignment), ConstraintType.s5));

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
		if (numberOfCoursePracticals == 1) {
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

	private int h9ViolationCount(TimetableAssignment assignment) {
		if (assignment.getRoom() instanceof InternalRoom &&
				((InternalRoom) assignment.getRoom()).getHolder().isPresent() &&
				!((InternalRoom) assignment.getRoom()).getHolder().equals(
						assignment.getSession().getCourse().getChair())) {
			return 1;
		} else {
			return 0;
		}
	}

	private int h10ViolationCount(TimetableAssignment assignment) {
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

	private int s1ViolationCount(TimetableAssignment assignment) {
		if (assignment.getRoom() instanceof InternalRoom &&
				assignment.getSession() instanceof InternalSession) {
			int actualRoomCapacity =
					((InternalRoom) assignment.getRoom()).getCapacity();
			int requiredRoomCapacity =
					((InternalSession) assignment.getSession()).getStudents();
			if (actualRoomCapacity > requiredRoomCapacity) {
				return actualRoomCapacity - requiredRoomCapacity;
			} else {
				return requiredRoomCapacity - actualRoomCapacity;
			}
		} else {
			return 0;
		}
	}

	private int s2ViolationCount(Course course, Timetable timetable) {
		int earliestDay = ValidationHelper.DAYS_PER_WEEK_MIN;
		int lastDay = ValidationHelper.DAYS_PER_WEEK_MIN;
		for (TimetableDay day : timetable.getDays()) {
			for (TimetablePeriod period : day.getPeriods()) {
				for (TimetableAssignment assignment : period.getAssignments()) {
					if (assignment.getSession().getCourse().equals(course) &&
							assignment.getSession().isLecture()) {
						if (period.getDay() < earliestDay) {
							earliestDay = period.getDay();
						} else if (period.getDay() > lastDay) {
							lastDay = period.getDay();
						}
					}
				}
			}
		}
		int difference = course.getMinNumberOfDays() -
				((lastDay - earliestDay) + 1);
		if (difference > 0) {
			return difference;
		} else {
			return 0;
		}
	}


	private int s3ViolationCount(TimetablePeriod period,
	                             TimetableAssignment assignment,
	                             Timetable timetable) {
		if (!assignment.getSession().isLecture() ||
				semester.getTimeSlotsPerDay() == 1) {
			return 0;
		}
		int counter = 0;
		for (Curriculum curriculum : semester.getCurricula()) {
			if (curriculum.getCourses().contains(
					assignment.getSession().getCourse())) {
				boolean foundAdjacent = false;
				if (period.getTimeSlot() >
						ValidationHelper.TIME_SLOTS_PER_DAY_MIN) {
					TimetablePeriod before = timetable.getDays().get(
							period.getDay()).getPeriods().get(
							period.getTimeSlot() - 1);
					for (TimetableAssignment assBefore : before.getAssignments()) {
						if (assBefore.getSession().isLecture() &&
								curriculum.getCourses().contains(
										assBefore.getSession().getCourse())) {
							foundAdjacent = true;
							break;
						}
					}
				}
				if (!foundAdjacent &&
						period.getTimeSlot() < semester.getTimeSlotsPerDay()) {
					TimetablePeriod after = timetable.getDays().get(
							period.getDay()).getPeriods().get(
							period.getTimeSlot() + 1);
					for (TimetableAssignment assAfter : after.getAssignments()) {
						if (assAfter.getSession().isLecture() &&
								curriculum.getCourses().contains(
										assAfter.getSession().getCourse())) {
							foundAdjacent = true;
							break;
						}
					}
				}
				if (!foundAdjacent) {
					counter++;
				}
			}
		}
		return counter;
	}

	private int s4ViolationCount(Course course, Timetable timetable) {
		int counter = 0;
		Room room = null;
		for (TimetableDay day : timetable.getDays()) {
			for (TimetablePeriod period : day.getPeriods()) {
				for (TimetableAssignment assgmt : period.getAssignments()) {
					if (assgmt.getSession().getCourse().equals(course) &&
							assgmt.getSession().isLecture()) {
						if (room == null) {
							room = assgmt.getRoom();
						} else if (!room.equals(assgmt.getRoom())) {
							counter++;
						}
					}
				}
			}
		}
		return counter;
	}

	private int s5ViolationCount(TimetablePeriod period,
	                             TimetableAssignment assignment) {
		if (assignment.getSession().getTeacher().getUnfavorablePeriods().
				contains(period)) {
			return 1;
		} else {
			return 0;
		}
	}

	private int s6ViolationCount(Timetable timetable) {
		int counter = 0;
		for (TimetableDay day : timetable.getDays()) {
			List<List<Teacher>> activeTeachers =
					new ArrayList<>(semester.getTimeSlotsPerDay());
			for (TimetablePeriod period : day.getPeriods()) {
				List<Teacher> activeInTimeSlot = new LinkedList<>();
				for (TimetableAssignment assgmt : period.getAssignments()) {
					activeInTimeSlot.add(assgmt.getSession().getTeacher());
				}
				activeTeachers.add(activeInTimeSlot);
			}
			for (int i = 0; i < activeTeachers.size() - 2; i++) {
				for (Teacher teacher : activeTeachers.get(i)) {
					for (int j = i + 1; j < activeTeachers.size(); j++) {
						if (!activeTeachers.get(j).contains(teacher)) {
							if (j - i > 2) {
								counter += j - i;
								for (int k = i + 1; k < j; k++) {
									activeTeachers.get(k).remove(teacher);
								}
							}
							break;
						} else if (j == activeTeachers.size() - 1 &&
								activeTeachers.get(j).contains(teacher)) {
							if ((j - i) + 1 > 2) {
								counter += (j - i) + 1;
							}
						}
					}

				}
			}
		}
		return counter;
	}

	private int s7ViolationCount(Curriculum curriculum, Timetable timetable) {
		int counter = 0;
		for (TimetableDay day : timetable.getDays()) {
			int lecturesPerDay = 0;
			for (TimetablePeriod period : day.getPeriods()) {
				for (TimetableAssignment assgmt : period.getAssignments()) {
					if (curriculum.getCourses().contains(
							assgmt.getSession().getCourse()) &&
							assgmt.getSession().isLecture()) {
						lecturesPerDay++;
					}
				}
			}
			if (lecturesPerDay > semester.getMaxDailyLecturesPerCur()) {
				counter += lecturesPerDay - semester.getMaxDailyLecturesPerCur();
			}
		}
		return counter;
	}
}
