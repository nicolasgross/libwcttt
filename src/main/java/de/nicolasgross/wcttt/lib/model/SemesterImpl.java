package de.nicolasgross.wcttt.lib.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.bind.annotation.*;
import java.util.LinkedList;

/**
 * Represents a semester.
 */
@XmlRootElement(name = "semester")
@XmlType(propOrder = {"name", "daysPerWeek", "timeSlotsPerDay",
		"maxDailyLecturesPerCur", "constrWeightings", "chairs", "internalRooms",
		"externalRooms", "courses", "curricula", "timetables"})
public class SemesterImpl implements Semester {

	private String name;
	private int daysPerWeek;
	private int timeSlotsPerDay;
	private int maxDailyLecturesPerCur;
	private ConstraintWeightings constrWeightings;
	private final ObservableList<Chair> chairs =
			FXCollections.observableList(new LinkedList<>());
	private final ObservableList<InternalRoom> internalRooms =
			FXCollections.observableList(new LinkedList<>());
	private final ObservableList<ExternalRoom> externalRooms =
			FXCollections.observableList(new LinkedList<>());
	private final ObservableList<Course> courses =
			FXCollections.observableList(new LinkedList<>());
	private final ObservableList<Curriculum> curricula =
			FXCollections.observableList(new LinkedList<>());
	private final ObservableList<Timetable> timetables =
			FXCollections.observableList(new LinkedList<>());

	/**
	 * Creates a new semester with an empty name, 1 day per week, 1 time
	 * slot per day, 1 max daily lecture per curriculum and default constraint
	 * weightings (see {@link ConstraintWeightings#ConstraintWeightings()}).
	 */
	public SemesterImpl() {
		this.name = "semester";
		this.daysPerWeek = 1;
		this.timeSlotsPerDay = 1;
		this.maxDailyLecturesPerCur = 1;
		this.constrWeightings = new ConstraintWeightings();
	}

	/**
	 * Creates a new semester.
	 *
	 * @param name                   the name of the semester, must not be null.
	 * @param daysPerWeek            the number of days in a week, must be >=
	 *                               {@value de.nicolasgross.wcttt.lib.model.ValidationHelper#DAYS_PER_WEEK_MIN}
	 *                               and <= {@value de.nicolasgross.wcttt.lib.model.ValidationHelper#DAYS_PER_WEEK_MAX}.
	 * @param timeSlotsPerDay        the number of time slots in a day, must be
	 *                               >= {@value de.nicolasgross.wcttt.lib.model.ValidationHelper#TIME_SLOTS_PER_DAY_MIN}
	 *                               and <= {@value de.nicolasgross.wcttt.lib.model.ValidationHelper#TIME_SLOTS_PER_DAY_MAX}.
	 * @param maxDailyLecturesPerCur the maximum number of daily lectures per
	 *                               curriculum, must be >=
	 *                               {@value de.nicolasgross.wcttt.lib.model.ValidationHelper#MIN_DAILY_LECTURES_PER_CUR_MIN}.
	 * @param constrWeightings       the soft constraint weightings for the
	 *                               semester, must not be null.
	 * @throws WctttModelException if parameters do not adhere to their
	 *                             respective value ranges.
	 */
	public SemesterImpl(String name, int daysPerWeek, int timeSlotsPerDay,
	                    int maxDailyLecturesPerCur,
	                    ConstraintWeightings constrWeightings) throws
			WctttModelException {
		if (name == null || constrWeightings == null) {
			throw new IllegalArgumentException("Parameters 'name' and " +
					"'constrWeightings' must not be null");
		}
		ValidationHelper.validateDaysPerWeek(daysPerWeek);
		ValidationHelper.validateTimeSlotsPerDay(timeSlotsPerDay);
		ValidationHelper.validateMaxDailyLecturesPerCur(maxDailyLecturesPerCur);
		this.name = name;
		this.daysPerWeek = daysPerWeek;
		this.timeSlotsPerDay = timeSlotsPerDay;
		this.maxDailyLecturesPerCur = maxDailyLecturesPerCur;
		this.constrWeightings = constrWeightings;
	}

	@Override
	@XmlAttribute(required = true)
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Parameter 'name' must not be " +
					"null");
		}
		this.name = name;
	}

	@Override
	@XmlAttribute(required = true)
	public int getDaysPerWeek() {
		return daysPerWeek;
	}

	@Override
	public void setDaysPerWeek(int daysPerWeek) throws WctttModelException {
		ValidationHelper.validateDaysPerWeek(daysPerWeek);
		this.daysPerWeek = daysPerWeek;
	}

	@Override
	@XmlAttribute(required = true)
	public int getTimeSlotsPerDay() {
		return timeSlotsPerDay;
	}

	@Override
	public void setTimeSlotsPerDay(int timeSlotsPerDay) throws
			WctttModelException {
		ValidationHelper.validateTimeSlotsPerDay(timeSlotsPerDay);
		this.timeSlotsPerDay = timeSlotsPerDay;
	}

	@Override
	@XmlAttribute(required = true)
	public int getMaxDailyLecturesPerCur() {
		return maxDailyLecturesPerCur;
	}

	@Override
	public void setMaxDailyLecturesPerCur(int maxDailyLecturesPerCur) throws
			WctttModelException {
		ValidationHelper.validateMaxDailyLecturesPerCur(maxDailyLecturesPerCur);
		this.maxDailyLecturesPerCur = maxDailyLecturesPerCur;
	}

	@Override
	@XmlElement(required = true)
	public ConstraintWeightings getConstrWeightings() {
		return constrWeightings;
	}

	@Override
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

	@Override
	@XmlElementWrapper(required = true)
	@XmlElement(name = "chair")
	public ObservableList<Chair> getChairs() {
		return chairs;
	}

	@Override
	@XmlElementWrapper(required = true)
	@XmlElement(name = "internalRoom")
	public ObservableList<InternalRoom> getInternalRooms() {
		return internalRooms;
	}

	@Override
	@XmlElementWrapper(required = true)
	@XmlElement(name = "externalRoom")
	public ObservableList<ExternalRoom> getExternalRooms() {
		return externalRooms;
	}

	@Override
	@XmlElementWrapper(required = true)
	@XmlElement(name = "course")
	public ObservableList<Course> getCourses() {
		return courses;
	}

	@Override
	@XmlElementWrapper(required = true)
	@XmlElement(name = "curriculum")
	public ObservableList<Curriculum> getCurricula() {
		return curricula;
	}

	@Override
	@XmlElementWrapper(required = true)
	@XmlElement(name = "timetable")
	public ObservableList<Timetable> getTimetables() {
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
		for (Room room : internalRooms) {
			if (id.equals(room.getId())) {
				return true;
			}
		}
		for (Room room : externalRooms) {
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

	@Override
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

	@Override
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
		for (InternalRoom room : internalRooms) {
			if (room.getHolder().isPresent() &&
					chair.equals(room.getHolder().get())) {
				throw new WctttModelException("Chair cannot be removed " +
						"because it is the holder of room " + room);
			}
		}
		return chairs.remove(chair);
	}

	@Override
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

	@Override
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
	@Override
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

	@Override
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

	@Override
	public void addInternalRoom(InternalRoom room) throws WctttModelException {
		if (room == null) {
			throw new IllegalArgumentException("Parameter 'room' must not be " +
					"null");
		}
		checkIfIdAvailable(room.getId());
		this.internalRooms.add(room);
	}

	@Override
	public void addExternalRoom(ExternalRoom room) throws WctttModelException {
		if (room == null) {
			throw new IllegalArgumentException("Parameter 'room' must not be " +
					"null");
		}
		checkIfIdAvailable(room.getId());
		this.externalRooms.add(room);
	}

	// timetables should be empty
	@Override
	public boolean removeInternalRoom(InternalRoom room) {
		if (room == null) {
			throw new IllegalArgumentException("Parameter 'room' must not " +
					"be null");
		}
		return this.internalRooms.remove(room);
	}

	@Override
	public boolean removeExternalRoom(ExternalRoom room) {
		if (room == null) {
			throw new IllegalArgumentException("Parameter 'room' must not " +
					"be null");
		}
		return this.externalRooms.remove(room);
	}

	@Override
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

	@Override
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
	@Override
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

	@Override
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

	@Override
	public void addCourseLecture(Session lecture, Course course) throws
			WctttModelException {
		if (course == null || lecture == null) {
			throw new IllegalArgumentException("Parameter 'course' and " +
					"'lecture' must not be null");
		}
		addCourseSession(lecture, course, true);
	}

	// timetables should be empty
	@Override
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

	@Override
	public void addCoursePractical(Session practical, Course course) throws
			WctttModelException {
		if (course == null || practical == null) {
			throw new IllegalArgumentException("Parameter 'course' and " +
					"'practical' must not be null");
		}
		addCourseSession(practical, course, false);
	}

	// timetables should be empty
	@Override
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

	@Override
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

	@Override
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
	@Override
	public boolean removeCurriculum(Curriculum curriculum) {
		if (curriculum == null) {
			throw new IllegalArgumentException("Parameter 'curriculum' must " +
					"not be null");
		}
		return this.curricula.remove(curriculum);
	}

	@Override
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

	@Override
	public void addTimetable(Timetable timetable) {
		timetable.calcConstraintViolations(this);
		timetables.add(timetable);
	}

	@Override
	public boolean removeTimetable(Timetable timetable) {
		return timetables.remove(timetable);
	}

	@Override
	public void removeAllTimetables() {
		this.timetables.clear();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof SemesterImpl)) {
			return false;
		} else if (obj == this) {
			return true;
		}

		SemesterImpl other = (SemesterImpl) obj;
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

		if (this.internalRooms.size() != other.internalRooms.size()) {
			return false;
		} else if (this.internalRooms != other.internalRooms) {
			if (!(this.internalRooms.containsAll(other.internalRooms))) {
				return false;
			}
		}

		if (this.externalRooms.size() != other.externalRooms.size()) {
			return false;
		} else if (this.externalRooms != other.externalRooms) {
			if (!(this.externalRooms.containsAll(other.externalRooms))) {
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

	@Override
	public String toString() {
		return name;
	}

}
