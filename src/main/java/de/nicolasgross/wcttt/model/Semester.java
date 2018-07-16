package de.nicolasgross.wcttt.model;

import javax.xml.bind.annotation.*;
import java.util.LinkedList;
import java.util.List;

import static de.nicolasgross.wcttt.model.ValidationHelper.*;

/**
 * Represents a semester.
 */
@XmlRootElement(name = "semester")
@XmlType(propOrder = {"name", "daysPerWeek", "timeSlotsPerDay",
		"maxDailyLecturesPerCur", "constrWeightings", "chairs", "rooms",
		"courses", "curricula", "timetables"})
public class Semester {

	private String name;
	private int daysPerWeek;
	private int timeSlotsPerDay;
	private int maxDailyLecturesPerCur;
	private ConstraintWeightings constrWeightings;
	private final List<Chair> chairs = new LinkedList<>();
	private final List<Room> rooms = new LinkedList<>();
	private final List<Course> courses = new LinkedList<>();
	private final List<Curriculum> curricula = new LinkedList<>();
	private final List<Timetable> timetables = new LinkedList<>();

	/**
	 * Creates a new semester with an empty name, 1 days per week, 1 time
	 * slots per day, 1 max daily lecture per curriculum and default constraint
	 * weightings (see {@link ConstraintWeightings#ConstraintWeightings()}).
	 */
	public Semester() {
		this.name = "";
		this.daysPerWeek = 1;
		this.timeSlotsPerDay = 1;
		this.maxDailyLecturesPerCur = 1;
		this.constrWeightings = new ConstraintWeightings();
	}

	/**
	 * Creates a new semester.
	 *
	 * @param name                   the name of the semester, must not be null.
	 * @param daysPerWeek            the number of days in a week, must be > 0.
	 * @param timeSlotsPerDay        the number of time slots in a day, must
	 *                               be > 0.
	 * @param maxDailyLecturesPerCur the maximum number of daily lectures per
	 *                               curriculum, must be >= 0.
	 * @param constrWeightings       the soft constraint weightings for the
	 *                               semester, must not be null.
	 * @throws WctttModelException if parameters do not adhere to their
	 *                             respective value ranges.
	 */
	public Semester(String name, int daysPerWeek, int timeSlotsPerDay,
	                int maxDailyLecturesPerCur,
	                ConstraintWeightings constrWeightings) throws
			WctttModelException {
		if (name == null || constrWeightings == null) {
			throw new IllegalArgumentException("Parameters 'name' and " +
					"'constrWeightings' must not be null");
		}
		validateDaysPerWeek(daysPerWeek);
		validateTimeSlotsPerDay(timeSlotsPerDay);
		validateMaxDailyLecturesPerCur(maxDailyLecturesPerCur);
		this.name = name;
		this.daysPerWeek = daysPerWeek;
		this.timeSlotsPerDay = timeSlotsPerDay;
		this.maxDailyLecturesPerCur = maxDailyLecturesPerCur;
		this.constrWeightings = constrWeightings;
	}

	/**
	 * Getter for the name of a semester. Do not manipulate the returned
	 * reference.
	 *
	 * @return the name of the semester.
	 */
	@XmlAttribute(required = true)
	public String getName() {
		return name;
	}

	/**
	 * Setter for the name of a semester.
	 *
	 * @param name the new name of the semester, must not be null.
	 */
	public void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Parameter 'name' must not be " +
					"null");
		}
		this.name = name;
	}

	/**
	 * Getter for the number of days per week of a semester.
	 *
	 * @return the number of days per week of the semester.
	 */
	@XmlAttribute(required = true)
	public int getDaysPerWeek() {
		return daysPerWeek;
	}

	/**
	 * Setter for the number of days per week of a semester.
	 *
	 * @param daysPerWeek the new number of days per week, must be >= 1.
	 * @throws WctttModelException if {@code daysPerWeek} is < 1.
	 */
	public void setDaysPerWeek(int daysPerWeek) throws WctttModelException {
		validateDaysPerWeek(daysPerWeek);
		this.daysPerWeek = daysPerWeek;
	}

	/**
	 * Getter for the number of time slots per day of a semester.
	 *
	 * @return the number of time slots per day of the semester
	 */
	@XmlAttribute(required = true)
	public int getTimeSlotsPerDay() {
		return timeSlotsPerDay;
	}

	/**
	 * Setter for the number of time slots per day of a semester.
	 *
	 * @param timeSlotsPerDay the new number of time slots per day, must be
	 *                        >= 1.
	 * @throws WctttModelException if {@code timeSlotsPerDay} is < 1.
	 */
	public void setTimeSlotsPerDay(int timeSlotsPerDay) throws
			WctttModelException {
		validateTimeSlotsPerDay(timeSlotsPerDay);
		this.timeSlotsPerDay = timeSlotsPerDay;
	}

	/**
	 * Getter for the maximum number of daily lectures per curriculum of a
	 * semester.
	 *
	 * @return the maximum number of daily lectures per curriculum.
	 */
	@XmlAttribute(required = true)
	public int getMaxDailyLecturesPerCur() {
		return maxDailyLecturesPerCur;
	}

	/**
	 * Setter for the maximum number of daily lectures per curriculum of a
	 * semester.
	 *
	 * @param maxDailyLecturesPerCur the new maximum number of daily lectures
	 *                               per curriculum.
	 * @throws WctttModelException if {@code maxDailyLecturesPerCur} is < 0.
	 */
	public void setMaxDailyLecturesPerCur(int maxDailyLecturesPerCur) throws
			WctttModelException {
		validateMaxDailyLecturesPerCur(maxDailyLecturesPerCur);
		this.maxDailyLecturesPerCur = maxDailyLecturesPerCur;
	}

	/**
	 * Getter for the soft constraint weightings of a semester.
	 *
	 * @return the soft constraint weightings of the semester.
	 */
	@XmlElement(required = true)
	public ConstraintWeightings getConstrWeightings() {
		return constrWeightings;
	}

	/**
	 * Setter for the soft constraint weightings of a semester.
	 *
	 * @param constrWeightings the new soft constraint weightings for the
	 *                         semester, must not be null.
	 */
	public void setConstrWeightings(ConstraintWeightings constrWeightings) {
		if (constrWeightings == null) {
			throw new IllegalArgumentException("Parameter 'constrWeightings' " +
					"must not be null");
		}
		this.constrWeightings = constrWeightings;
		for (Timetable timetable : timetables) {
			timetable.calcConstraintViolations(this);
		}
	}

	/**
	 * Getter for the chairs of a semester. Do not manipulate the returned
	 * references.
	 *
	 * @return the list of the chairs of the semester.
	 */
	@XmlElementWrapper(required = true)
	@XmlElement(name = "chair")
	public List<Chair> getChairs() {
		return chairs;
	}

	/**
	 * Getter for the rooms of a semester. Do not manipulate the returned
	 * references.
	 *
	 * @return the list of the rooms of the semester.
	 */
	@XmlElementWrapper(required = true)
	@XmlElement(name = "room")
	public List<Room> getRooms() {
		return rooms;
	}

	/**
	 * Getter for the courses of a semester. Do not manipulate the returned
	 * references.
	 *
	 * @return the list of the courses of the semester.
	 */
	@XmlElementWrapper(required = true)
	@XmlElement(name = "course")
	public List<Course> getCourses() {
		return courses;
	}

	/**
	 * Getter for the curricula of a semester. Do not manipulate the returned
	 * references.
	 *
	 * @return the list of the curricula of the semester.
	 */
	@XmlElementWrapper(required = true)
	@XmlElement(name = "curriculum")
	public List<Curriculum> getCurricula() {
		return curricula;
	}

	/**
	 * Getter for the timetables of a semester. Do not manipulate the returned
	 * references.
	 *
	 * @return the list of the timetables of the semester.
	 */
	@XmlElementWrapper(required = true)
	@XmlElement(name = "timetable")
	public List<Timetable> getTimetables() {
		return timetables;
	}

	private boolean chairIdExists(String id) {
		for (Chair chair : chairs) {
			if (id.equals(chair.getId())) {
				return true;
			}
		}
		return false;
	}

	private boolean teacherIdExists(String id) {
		for (Chair chair : chairs) {
			for (Teacher teacher : chair.getTeachers()) {
				if (id.equals(teacher.getId())) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean roomIdExists(String id) {
		for (Room room : rooms) {
			if (id.equals(room.getId())) {
				return true;
			}
		}
		return false;
	}

	private boolean courseIdExists(String id) {
		for (Course course : courses) {
			if (id.equals(course.getId())) {
				return true;
			}
		}
		return false;
	}

	private boolean sessionIdExists(String id) {
		for (Course course : courses) {
			for (Session session : course.getLectures()) {
				if (id.equals(session.getId())) {
					return true;
				}
			}
			for (Session session : course.getPracticals()) {
				if (id.equals(session.getId())) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean curriculumIdExists(String id) {
		for (Curriculum curriculum : curricula) {
			if (id.equals(curriculum.getId())) {
				return true;
			}
		}
		return false;
	}

	private void checkIfIdAvailable(String id) throws WctttModelException {
		if ("".equals(id)) {
			throw new WctttModelException("Id '" + id + "' is not allowed");
		} else if (chairIdExists(id)) {
			throw new WctttModelException("Id '" + id + "' is already " +
					"assigned to a chair of the semester");
		} else if (teacherIdExists(id)) {
			throw new WctttModelException("Id '" + id + "' is already " +
					"assigned to a teacher of the semester");
		} else if (roomIdExists(id)) {
			throw new WctttModelException("Id '" + id + "' is already " +
					"assigned to a room of the semester");
		} else if (courseIdExists(id)) {
			throw new WctttModelException("Id '" + id + "' is already " +
					"assigned to a course of the semester");
		} else if (sessionIdExists(id)) {
			throw new WctttModelException("Id '" + id + "' is already " +
					"assigned to a session of the semester");
		} else if (curriculumIdExists(id)) {
			throw new WctttModelException("Id '" + id + "' is already " +
					"assigned to a curriculum of the semester");
		}
	}

	public void addChair(Chair chair) throws WctttModelException {
		if (chair == null) {
			throw new IllegalArgumentException("Parameter 'chair' must not be" +
					" null");
		}
		checkIfIdAvailable(chair.getId());
		for (Teacher teacher : chair.getTeachers()) {
			checkIfIdAvailable(teacher.getId());
		}
		this.chairs.add(chair);
	}

	// timetables should be empty
	public boolean removeChair(Chair chair) throws WctttModelException {
		if (chair == null) {
			throw new IllegalArgumentException("Parameter 'chair' must not be" +
					" null");
		} else if (!chair.getTeachers().isEmpty()) {
			throw new WctttModelException("Chair cannot be removed because it" +
					" has teachers assigned to it");
		}
		for (Course course : courses) {
			if (chair.equals(course.getChair())) {
				throw new WctttModelException("Chair cannot be removed " +
						"because it is responsible for course " + course);
			}
		}
		for (Room room : rooms) {
			if (room.getHolder().isPresent() &&
					chair.equals(room.getHolder().get())) {
				throw new WctttModelException("Chair cannot be removed " +
						"because it is the holder of room " + room);
			}
		}
		return chairs.remove(chair);
	}

	public void updateChairId(Chair chair, String id) throws
			WctttModelException {
		if (chair == null || id == null) {
			throw new IllegalArgumentException("Parameters 'chair' and 'id' " +
					"must not be null");
		} else if (!chairIdExists(chair.getId())) {
			throw new WctttModelException("Chair " + chair.getId() + " is not" +
					" assigned to the semester");
		}
		checkIfIdAvailable(id);
		chair.setId(id);
	}

	public void addTeacherToChair(Teacher teacher, Chair chair) throws
			WctttModelException {
		if (teacher == null || chair == null) {
			throw new IllegalArgumentException("Parameter 'teacher' and " +
					"'chair' must not be null");
		} else if (!chairIdExists(chair.getId())) {
			throw new WctttModelException("Chair " + chair.getId() + " is not" +
					" assigned to the semester");
		}
		checkIfIdAvailable(teacher.getId());
		chair.addTeacher(teacher);
	}

	// timetables should be empty
	public boolean removeTeacherFromChair(Teacher teacher, Chair chair) throws
			WctttModelException {
		if (teacher == null || chair == null) {
			throw new IllegalArgumentException("Parameters 'teacher' and " +
					"'chair' must not be null");
		}
		for (Course course : courses) {
			for (Session lecture : course.getLectures()) {
				if (teacher.equals(lecture.getTeacher())) {
					throw new WctttModelException("Teacher cannot be removed " +
							"because he/she is assigned to lecture " + lecture);
				}
			}
			for (Session practical : course.getPracticals()) {
				if (teacher.equals(practical.getTeacher())) {
					throw new WctttModelException("Teacher cannot be removed " +
							"because he/she is assigned to practical " +
							practical);
				}
			}
		}
		return chair.removeTeacher(teacher);
	}

	public void updateTeacherId(Teacher teacher, Chair chair, String id) throws
			WctttModelException {
		if (teacher == null || chair == null || id == null) {
			throw new IllegalArgumentException("Parameter 'teacher', 'chair' " +
					"and 'id' must not be null");
		} else if (!teacherIdExists(teacher.getId())) {
			throw new WctttModelException("Teacher " + teacher.getId() + " is" +
					" not assigned to the semester");
		} else if (!chairIdExists(chair.getId())) {
			throw new WctttModelException("Chair " + chair.getId() + " is not" +
					" assigned to the semester");
		}
		checkIfIdAvailable(id);
		chair.updateTeacherId(teacher, id);
	}

	public void addRoom(Room room) throws WctttModelException {
		if (room == null) {
			throw new IllegalArgumentException("Parameter 'room' must not be " +
					"null");
		}
		checkIfIdAvailable(room.getId());
		this.rooms.add(room);
	}

	// timetables should be empty
	public boolean removeRoom(Room room) {
		if (room == null) {
			throw new IllegalArgumentException("Parameter 'room' must not " +
					"be null");
		}
		return this.rooms.remove(room);
	}

	public void updateRoomId(Room room, String id) throws WctttModelException {
		if (room == null || id == null) {
			throw new IllegalArgumentException("Parameter 'room' and 'id' " +
					"must not be null");
		} else if (!roomIdExists(room.getId())) {
			throw new WctttModelException("Room " + room.getId() + " is" +
					" not assigned to the semester");
		}
		checkIfIdAvailable(id);
		room.setId(id);
	}

	public void addCourse(Course course) throws WctttModelException {
		if (course == null) {
			throw new IllegalArgumentException("Parameter 'course' must not " +
					"be null");
		}
		checkIfIdAvailable(course.getId());
		for (Session lecture : course.getLectures()) {
			checkIfIdAvailable(lecture.getId());
		}
		for (Session practical : course.getPracticals()) {
			checkIfIdAvailable(practical.getId());
		}
		this.courses.add(course);
	}

	// timetables should be empty
	public boolean removeCourse(Course course) throws WctttModelException {
		if (course == null) {
			throw new IllegalArgumentException("Parameter 'course' must not " +
					"be null");
		}
		for (Curriculum curriculum : curricula) {
			for (Course otherCourse : curriculum.getCourses()) {
				if (course.equals(otherCourse)) {
					throw new WctttModelException("Course cannot be removed " +
							"because it is part of curriculum " + curriculum);
				}
			}
		}
		return this.courses.remove(course);
	}

	public void updateCourseId(Course course, String id) throws
			WctttModelException {
		if (course == null || id == null) {
			throw new IllegalArgumentException("Parameters 'course' and 'id' " +
					"must not be null");
		} else if (!courseIdExists(course.getId())) {
			throw new WctttModelException("Course " + course.getId() + " is " +
					"not assigned to the semester");
		}
		checkIfIdAvailable(id);
		course.setId(id);
	}

	private void addCourseSession(Session session, Course course,
	                              boolean lecture) throws WctttModelException {
		if (!courseIdExists(course.getId())) {
			throw new WctttModelException("Course " + course.getId() + " is " +
					"not assigned to the semester");
		}
		checkIfIdAvailable(session.getId());
		if (lecture) {
			course.addLecture(session);
		} else {
			course.addPractical(session);
		}
	}

	public void addCourseLecture(Session lecture, Course course) throws
			WctttModelException {
		if (course == null || lecture == null) {
			throw new IllegalArgumentException("Parameter 'course' and " +
					"'lecture' must not be null");
		}
		addCourseSession(lecture, course, true);
	}

	// timetables should be empty
	public boolean removeCourseLecture(Session lecture, Course course) throws
			WctttModelException {
		if (lecture == null || course == null) {
			throw new IllegalArgumentException("Parameters 'lecture' and " +
					"'course' must not be null");
		} else if (!courseIdExists(course.getId())) {
			throw new WctttModelException("Course " + course.getId() + " is" +
					" not assigned to the semester");
		}
		return course.removeLecture(lecture);
	}

	public void addCoursePractical(Session practical, Course course) throws
			WctttModelException {
		if (course == null || practical == null) {
			throw new IllegalArgumentException("Parameter 'course' and " +
					"'practical' must not be null");
		}
		addCourseSession(practical, course, false);
	}

	// timetables should be empty
	public boolean removeCoursePractical(Session practical, Course course)
			throws WctttModelException {
		if (practical == null || course == null) {
			throw new IllegalArgumentException("Parameters 'practical' and " +
					"'course' must not be null");
		} else if (!courseIdExists(course.getId())) {
			throw new WctttModelException("Course " + course.getId() + " is" +
					" not assigned to the semester");
		}
		return course.removePractical(practical);
	}

	public void updateCourseSessionId(Session session, Course course, String id)
			throws WctttModelException {
		if (course == null || session == null || id == null) {
			throw new IllegalArgumentException("Parameter 'session', 'course'" +
					" and 'id' must not be null");
		} else if (!courseIdExists(course.getId())) {
			throw new WctttModelException("Course " + course.getId() + " is" +
					" not assigned to the semester");
		} else if (!sessionIdExists(session.getId())) {
			throw new WctttModelException("Session " + session.getId() + " is" +
					" not assigned to the semester");
		}
		checkIfIdAvailable(id);
		course.updateSessionId(session, id);
	}

	public void addCurriculum(Curriculum curriculum) throws
			WctttModelException {
		if (curriculum == null) {
			throw new IllegalArgumentException("Parameter 'curriculum' must " +
					"not be null");
		}
		checkIfIdAvailable(curriculum.getId());
		this.curricula.add(curriculum);
	}

	// timetables should be empty
	public boolean removeCurriculum(Curriculum curriculum) {
		if (curriculum == null) {
			throw new IllegalArgumentException("Parameter 'curriculum' must " +
					"not be null");
		}
		return this.curricula.remove(curriculum);
	}

	public void updateCurriculumId(Curriculum curriculum, String id) throws
			WctttModelException {
		if (curriculum == null || id == null) {
			throw new IllegalArgumentException("Parameters 'curriculum' and " +
					"'id' must not be null");
		} else if (!curriculumIdExists(curriculum.getId())) {
			throw new WctttModelException("Curriculum " + curriculum.getId() +
					" is not assigned to the semester");
		}
		checkIfIdAvailable(id);
		curriculum.setId(id);
	}

	public void addTimetable(Timetable timetable) {
		timetable.calcConstraintViolations(this);
		timetables.add(timetable);
	}

	public boolean removeTimetable(Timetable timetable) {
		return timetables.remove(timetable);
	}

	public void removeAllTimetables() {
		this.timetables.clear();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Semester)) {
			return false;
		} else if (obj == this) {
			return true;
		}

		Semester other = (Semester) obj;
		if (!this.name.equals(other.name) ||
				this.daysPerWeek != other.daysPerWeek ||
				this.timeSlotsPerDay != other.timeSlotsPerDay ||
				this.maxDailyLecturesPerCur != other.maxDailyLecturesPerCur ||
				!this.constrWeightings.equals(other.constrWeightings)) {
			return false;
		}

		if (this.chairs.size() != other.chairs.size()) {
			return false;
		} else if (this.chairs != other.chairs) {
			if (!(this.chairs.containsAll(other.chairs))) {
				return false;
			}
		}

		if (this.rooms.size() != other.rooms.size()) {
			return false;
		} else if (this.rooms != other.rooms) {
			if (!(this.rooms.containsAll(other.rooms))) {
				return false;
			}
		}

		if (this.courses.size() != other.courses.size()) {
			return false;
		} else if (this.courses != other.courses) {
			if (!(this.courses.containsAll(other.courses))) {
				return false;
			}
		}

		if (this.curricula.size() != other.curricula.size()) {
			return false;
		} else if (this.curricula != other.curricula) {
			if (!(this.curricula.containsAll(other.curricula))) {
				return false;
			}
		}

		if (this.timetables.size() != other.timetables.size()) {
			return false;
		} else if (this.timetables != other.timetables) {
			if (!(this.timetables.containsAll(other.timetables))) {
				return false;
			}
		}

		return true;
	}

	// TODO toString

}
