package de.nicolasgross.wcttt.lib.model;

public class ValidationHelper {

	/*
		'Semester:'  -> Following constants/methods are used in class Semester.
		                This invalidates all previously mentioned classes.
		' + Period:' -> Following constant/methods are also used in class
		                Period. This does not invalidate the previously
		                mentioned classes.
	 */

	// Semester:
	public static final int MIN_DAILY_LECTURES_PER_CUR_MIN = 1;
	public static final int TIME_SLOTS_PER_DAY_MIN = 2;
	public static final int DAYS_PER_WEEK_MIN = 1;
	// + Period:
	// + TimetableDay:
	public static final int TIME_SLOTS_PER_DAY_MAX = 7;
	public static final int DAYS_PER_WEEK_MAX = 7;

	// Period:
	public static final int PERIOD_TIME_SLOT_MIN = 1;
	public static final int PERIOD_DAY_MIN = 1;

	//Room:
	public static final int ROOM_CAPACITY_MIN = 1;
	public static final int PROJECTORS_MIN = 0;

	// Course:
	public static final int MIN_NUM_OF_DAYS_MIN = 1;

	// Session:
	public static final int STUDENTS_MIN = 1;

	//ConstraintWeightings:
	public static final double CONSTRAINT_WEIGHTING_MIN = 0.0;


	// Semester:

	static void validateDaysPerWeek(int daysPerWeek) throws
			WctttModelException {
		if (daysPerWeek < DAYS_PER_WEEK_MIN ||
				daysPerWeek > DAYS_PER_WEEK_MAX) {
			throw new WctttModelException("Parameter 'daysPerWeek' must " +
					"be >= " + DAYS_PER_WEEK_MIN + " and <= " +
					DAYS_PER_WEEK_MAX);
		}
	}

	static void validateTimeSlotsPerDay(int timeSlotsPerDay) throws
			WctttModelException {
		if (timeSlotsPerDay < TIME_SLOTS_PER_DAY_MIN ||
				timeSlotsPerDay > TIME_SLOTS_PER_DAY_MAX) {
			throw new WctttModelException("Parameter 'timeSlotsPerDay' " +
					"must be >= " + TIME_SLOTS_PER_DAY_MIN + " and <= " +
					TIME_SLOTS_PER_DAY_MAX);
		}
	}

	static void validateMaxDailyLecturesPerCur(int maxLectures) throws
			WctttModelException {
		if (maxLectures < MIN_DAILY_LECTURES_PER_CUR_MIN) {
			throw new WctttModelException("Parameter 'maxDailyLecturesPerCur'" +
					" must be >= " + MIN_DAILY_LECTURES_PER_CUR_MIN);
		}
	}


	// Room:

	static void validateRoomCapacity(int capacity) throws WctttModelException {
		if (capacity < ROOM_CAPACITY_MIN) {
			throw new WctttModelException("Parameter 'capacity' must be >= "
					+ ROOM_CAPACITY_MIN);
		}
	}


	// Period:

	static void validateTimeSlot(int timeSlot) throws WctttModelException {
		if (timeSlot < PERIOD_TIME_SLOT_MIN ||
				timeSlot > TIME_SLOTS_PER_DAY_MAX) {
			throw new WctttModelException("Parameter 'timeSlot' must be >= " +
					PERIOD_TIME_SLOT_MIN + " and <= " +
					TIME_SLOTS_PER_DAY_MAX);
		}
	}

	// + TimetableDay:

	static void validateDay(int day) throws WctttModelException {
		if (day < PERIOD_DAY_MIN || day > DAYS_PER_WEEK_MAX) {
			throw new WctttModelException("Parameter 'day' must be >= " +
					PERIOD_DAY_MIN + " and <= " + DAYS_PER_WEEK_MAX);
		}
	}


	// RoomFeatures:

	static void validateProjectors(int projectors) throws WctttModelException {
		if (projectors < PROJECTORS_MIN) {
			throw new WctttModelException("Parameter 'projectors' must be >= "
					+ PROJECTORS_MIN);
		}
	}


	// Course:

	static void validateMinNumOfDays(int minNumberOfDays) throws
			WctttModelException {
		if (minNumberOfDays < MIN_NUM_OF_DAYS_MIN) {
			throw new WctttModelException("Parameter 'minNumberOfDays' must " +
					"be >= " + MIN_NUM_OF_DAYS_MIN);
		}
	}


	// Session:

	static void validateStudents(int students) throws WctttModelException {
		if (students < STUDENTS_MIN) {
			throw new WctttModelException("Parameter 'students' must be >= "
					+ STUDENTS_MIN);
		}
	}


	// ConstraintWeightings:

	static void validateConstraintWeighting(double weighting) throws
			WctttModelException {
		if (weighting < CONSTRAINT_WEIGHTING_MIN) {
			throw new WctttModelException("Constraint weighting must be >= "
					+ CONSTRAINT_WEIGHTING_MIN);
		}
	}

}
