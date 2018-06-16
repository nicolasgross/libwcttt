package de.nicolasgross.wcttt.model;

class ValidationHelper {

	// Semester

	static void validateDaysPerWeek(int daysPerWeek) throws
			WctttModelException {
		int limit = 1;
		if (daysPerWeek < limit) {
			throw new WctttModelException("Parameter 'daysPerWeek' must " +
					"be >= " + limit);
		}
	}

	static void validateTimeSlotsPerDay(int timeSlotsPerDay) throws
			WctttModelException {
		int limit = 1;
		if (timeSlotsPerDay < limit) {
			throw new WctttModelException("Parameter 'timeSlotsPerDay' " +
					"must be >= " + limit);
		}
	}

	static void validateMinDailyLecturesPerCur(int minLectures) throws
			WctttModelException {
		int limit = 0;
		if (minLectures < limit) {
			throw new WctttModelException("Parameter " +
					"'minDailyLecturesPerCur' must be >= " + limit);
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
		int limit = 1;
		if (capacity < limit) {
			throw new WctttModelException("Parameter 'capacity' must be " +
					"larger " + (limit - 1));
		}
	}

}
