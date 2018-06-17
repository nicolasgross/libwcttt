package de.nicolasgross.wcttt.model;

class ValidationHelper {

	// Semester:
	private static final int DAYS_PER_WEEK_MIN = 1;
	// + Period:
	private static final int TIME_SLOTS_PER_DAY_MIN = 1;
	private static final int MIN_DAILY_LECTURES_PER_CUR_MIN = 0;

	//Room:
	private static final int ROOM_CAPACITY_MIN = 1;
	private static final int PROJECTORS_MIN = 0;

	// Course:
	private static final int MIN_NUM_OF_DAYS_MIN = 0;

	// Session:
	private static final int STUDENTS_MIN = 1;


	// Semester:

	static void validateDaysPerWeek(int daysPerWeek) throws
			WctttModelException {
		if (daysPerWeek < DAYS_PER_WEEK_MIN) {
			throw new WctttModelException("Parameter 'daysPerWeek' must " +
					"be >= " + DAYS_PER_WEEK_MIN);
		}
	}

	static void validateTimeSlotsPerDay(int timeSlotsPerDay) throws
			WctttModelException {
		if (timeSlotsPerDay < TIME_SLOTS_PER_DAY_MIN) {
			throw new WctttModelException("Parameter 'timeSlotsPerDay' " +
					"must be >= " + TIME_SLOTS_PER_DAY_MIN);
		}
	}

	static void validateMinDailyLecturesPerCur(int minLectures) throws
			WctttModelException {
		if (minLectures < MIN_DAILY_LECTURES_PER_CUR_MIN) {
			throw new WctttModelException("Parameter " +
					"'minDailyLecturesPerCur' must be >= "
					+ MIN_DAILY_LECTURES_PER_CUR_MIN);
		}
	}

	static void validateMaxDailyLecturesPerCur(int maxLectures, int
			minLectures) throws WctttModelException {
		if (maxLectures < minLectures) {
			throw new WctttModelException("Parameter 'maxDailyLecturesPerCur'" +
					" must be >= 'minDailyLecturesPerCur'");
		}
	}


	// Room

	static void validateRoomCapacity(int capacity) throws WctttModelException {
		if (capacity < ROOM_CAPACITY_MIN) {
			throw new WctttModelException("Parameter 'capacity' must be >= "
					+ ROOM_CAPACITY_MIN);
		}
	}


	// Period

	static void validateDay(int day) throws WctttModelException {
		if (day < DAYS_PER_WEEK_MIN) {
			throw new WctttModelException("Parameter 'day' must be >= "
					+ DAYS_PER_WEEK_MIN);
		}
	}

	static void validateTimeSlot(int timeSlot) throws WctttModelException {
		if (timeSlot < TIME_SLOTS_PER_DAY_MIN) {
			throw new WctttModelException("Parameter 'timeSlot' must be >= "
					+ TIME_SLOTS_PER_DAY_MIN);
		}
	}


	// RoomFeatures

	static void validateProjectors(int projectors) throws WctttModelException {
		if (projectors < PROJECTORS_MIN) {
			throw new WctttModelException("Parameter 'projectors' must be >= "
					+ PROJECTORS_MIN);
		}
	}


	// Course

	static void validateMinNumOfDays(int minNumberOfDays) throws
			WctttModelException {
		if (minNumberOfDays < MIN_NUM_OF_DAYS_MIN) {
			throw new WctttModelException("Parameter 'minNumberOfDays' must " +
					"be >= " + MIN_NUM_OF_DAYS_MIN);
		}
	}


	// Session

	static void validateStudents(int students) throws
			WctttModelException {
		if (students < STUDENTS_MIN) {
			throw new WctttModelException("Parameter 'students' must be >= "
					+ STUDENTS_MIN);
		}
	}


}
