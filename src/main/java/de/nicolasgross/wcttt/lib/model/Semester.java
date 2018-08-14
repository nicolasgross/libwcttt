package de.nicolasgross.wcttt.lib.model;

import javafx.collections.ObservableList;

import java.util.List;

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

	boolean removeChair(Chair chair) throws WctttModelException;

	void updateChairId(Chair chair, String id) throws
			WctttModelException;

	void updateChairData(Chair chair, String name, String abbreviation,
	                     ObservableList<Teacher> teachers);

	void addTeacherToChair(Teacher teacher, Chair chair) throws
					WctttModelException;

	boolean removeTeacherFromChair(Teacher teacher, Chair chair) throws
			WctttModelException;

	void updateTeacherId(Teacher teacher, Chair chair, String id) throws
					WctttModelException;

	void updateTeacherData(Teacher teacher, Chair chair, String name,
	                       List<Period> unfavorablePeriods,
	                       List<Period> unavailablePeriods);

	void addInternalRoom(InternalRoom room) throws WctttModelException;

	void addExternalRoom(ExternalRoom room) throws WctttModelException;

	boolean removeInternalRoom(InternalRoom room) throws WctttModelException;

	boolean removeExternalRoom(ExternalRoom room) throws WctttModelException;

	void updateRoomId(Room room, String id) throws WctttModelException;

	void updateInternalRoomData(InternalRoom room, String name, int capacity,
	                            Chair holder, RoomFeatures features)
			throws WctttModelException;

	void updateExternalRoomData(ExternalRoom room, String name);

	void addCourse(Course course) throws WctttModelException;

	boolean removeCourse(Course course) throws WctttModelException;

	void updateCourseId(Course course, String id) throws
			WctttModelException;

	void updateCourseData(Course course, String name, String abbreviation,
	                      Chair chair, CourseLevel courseLevel,
	                      int minNumberOfDays, List<Session> lectures,
	                      List<Session> practicals);

	void addCourseLecture(Session lecture, Course course) throws
					WctttModelException;

	boolean removeCourseLecture(Session lecture, Course course) throws
			WctttModelException;

	void addCoursePractical(Session practical, Course course) throws
					WctttModelException;

	boolean removeCoursePractical(Session practical, Course course)
			throws WctttModelException;

	void updateCourseSessionId(Session session, Course course, String id)
					throws WctttModelException;

	void updateCourseIntSessionData(Session session, Course course, String name,
	                                Teacher teacher, boolean doubleSession,
	                                Period preAssignment, int students,
	                                RoomFeatures roomRequirements);
	void updateCourseExtSessionData(Session session, Course course, String name,
	                                Teacher teacher, boolean doubleSession,
	                                Period preAssignment, Room room);

	void addCurriculum(Curriculum curriculum) throws
							WctttModelException;

	boolean removeCurriculum(Curriculum curriculum) throws WctttModelException;

	void updateCurriculumId(Curriculum curriculum, String id) throws
			WctttModelException;

	void updateCurriculumData(Curriculum curriculum, String name,
	                          List<Course> courses);

	void addTimetable(Timetable timetable) throws WctttModelException;

	boolean removeTimetable(Timetable timetable);

	void updateTimetableName(Timetable timetable, String name)
			throws WctttModelException;
}
