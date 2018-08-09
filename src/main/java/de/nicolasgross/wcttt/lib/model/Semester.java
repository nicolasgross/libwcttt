package de.nicolasgross.wcttt.lib.model;

import javafx.collections.ObservableList;

public interface Semester {

	/**
	 * Getter for the name of a semester. Do not manipulate the returned
	 * reference.
	 *
	 * @return the name of the semester.
	 */
	String getName();

	/**
	 * Setter for the name of a semester.
	 *
	 * @param name the new name of the semester, must not be null.
	 */
	void setName(String name);

	/**
	 * Getter for the number of days per week of a semester.
	 *
	 * @return the number of days per week of the semester.
	 */
	int getDaysPerWeek();

	/**
	 * Setter for the number of days per week of a semester.
	 *
	 * @param daysPerWeek the new number of days per week, must be >=
	 *                    {@value de.nicolasgross.wcttt.lib.model.ValidationHelper#DAYS_PER_WEEK_MIN}
	 *                    and <= {@value de.nicolasgross.wcttt.lib.model.ValidationHelper#DAYS_PER_WEEK_MAX}.
	 * @throws WctttModelException if {@code daysPerWeek} is outside the given
	 *                             range.
	 */
	void setDaysPerWeek(int daysPerWeek) throws WctttModelException;

	/**
	 * Getter for the number of time slots per day of a semester.
	 *
	 * @return the number of time slots per day of the semester
	 */
	int getTimeSlotsPerDay();

	/**
	 * Setter for the number of time slots per day of a semester.
	 *
	 * @param timeSlotsPerDay the new number of time slots per day, must be >=
	 *                        {@value de.nicolasgross.wcttt.lib.model.ValidationHelper#TIME_SLOTS_PER_DAY_MIN}
	 *                        and <= {@value de.nicolasgross.wcttt.lib.model.ValidationHelper#TIME_SLOTS_PER_DAY_MAX}.
	 * @throws WctttModelException if {@code timeSlotsPerDay} is outside the
	 *                             given range.
	 */
	void setTimeSlotsPerDay(int timeSlotsPerDay) throws
			WctttModelException;

	/**
	 * Getter for the maximum number of daily lectures per curriculum of a
	 * semester.
	 *
	 * @return the maximum number of daily lectures per curriculum.
	 */
	int getMaxDailyLecturesPerCur();

	/**
	 * Setter for the maximum number of daily lectures per curriculum of a
	 * semester.
	 *
	 * @param maxDailyLecturesPerCur the new maximum number of daily lectures
	 *                               per curriculum, must be >=
	 *                               {@value de.nicolasgross.wcttt.lib.model.ValidationHelper#MIN_DAILY_LECTURES_PER_CUR_MIN}.
	 * @throws WctttModelException if {@code maxDailyLecturesPerCur} is outside
	 *                             the given range.
	 */
	void setMaxDailyLecturesPerCur(int maxDailyLecturesPerCur) throws
			WctttModelException;

	/**
	 * Getter for the soft constraint weightings of a semester.
	 *
	 * @return the soft constraint weightings of the semester.
	 */
	ConstraintWeightings getConstrWeightings();

	/**
	 * Setter for the soft constraint weightings of a semester.
	 *
	 * @param constrWeightings the new soft constraint weightings for the
	 *                         semester, must not be null.
	 */
	void setConstrWeightings(ConstraintWeightings constrWeightings);

	/**
	 * Getter for the chairs of a semester. Do not manipulate the returned
	 * references.
	 *
	 * @return the list of the chairs of the semester.
	 */
	ObservableList<Chair> getChairs();

	/**
	 * Getter for the internal rooms of a semester. Do not manipulate the
	 * returned references.
	 *
	 * @return the list of the rooms of the semester.
	 */
	ObservableList<InternalRoom> getInternalRooms();

	/**
	 * Getter for the external rooms of a semester. Do not manipulate the
	 * returned references.
	 *
	 * @return the list of the rooms of the semester.
	 */
	ObservableList<ExternalRoom> getExternalRooms();

	/**
	 * Getter for the courses of a semester. Do not manipulate the returned
	 * references.
	 *
	 * @return the list of the courses of the semester.
	 */
	ObservableList<Course> getCourses();

	/**
	 * Getter for the curricula of a semester. Do not manipulate the returned
	 * references.
	 *
	 * @return the list of the curricula of the semester.
	 */
	ObservableList<Curriculum> getCurricula();

	/**
	 * Getter for the timetables of a semester. Do not manipulate the returned
	 * references.
	 *
	 * @return the list of the timetables of the semester.
	 */
	ObservableList<Timetable> getTimetables();

	void addChair(Chair chair) throws WctttModelException;

	// timetables should be empty
	boolean removeChair(Chair chair) throws WctttModelException;

	void updateChairId(Chair chair, String id) throws
			WctttModelException;

	void addTeacherToChair(Teacher teacher, Chair chair) throws
					WctttModelException;

	// timetables should be empty
	boolean removeTeacherFromChair(Teacher teacher, Chair chair) throws
			WctttModelException;

	void updateTeacherId(Teacher teacher, Chair chair, String id) throws
					WctttModelException;

	void addInternalRoom(InternalRoom room) throws WctttModelException;

	void addExternalRoom(ExternalRoom room) throws WctttModelException;

	// timetables should be empty
	boolean removeInternalRoom(InternalRoom room);

	boolean removeExternalRoom(ExternalRoom room);

	void updateRoomId(Room room, String id) throws WctttModelException;

	void addCourse(Course course) throws WctttModelException;

	// timetables should be empty
	boolean removeCourse(Course course) throws WctttModelException;

	void updateCourseId(Course course, String id) throws
			WctttModelException;

	void addCourseLecture(Session lecture, Course course) throws
					WctttModelException;

	// timetables should be empty
	boolean removeCourseLecture(Session lecture, Course course) throws
			WctttModelException;

	void addCoursePractical(Session practical, Course course) throws
					WctttModelException;

	// timetables should be empty
	boolean removeCoursePractical(Session practical, Course course)
			throws WctttModelException;

	void updateCourseSessionId(Session session, Course course, String id)
					throws WctttModelException;

	void addCurriculum(Curriculum curriculum) throws
							WctttModelException;

	// timetables should be empty
	boolean removeCurriculum(Curriculum curriculum);

	void updateCurriculumId(Curriculum curriculum, String id) throws
			WctttModelException;

	void addTimetable(Timetable timetable);

	boolean removeTimetable(Timetable timetable);

	void removeAllTimetables();
}
