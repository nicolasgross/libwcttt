package de.nicolasgross.wcttt.model;

import javax.xml.bind.annotation.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents a semester.
 */
@XmlRootElement(name = "semester")
@XmlType(propOrder = {"name", "daysPerWeek", "timeSlotsPerDay",
		"minDailyLecturesPerCur", "maxDailyLecturesPerCur", "constrWeightings",
		"chairs", "teachers", "rooms", "courses", "curricula", "timetables"})
public class Semester {

	private String name;
	private int daysPerWeek;
	private int timeSlotsPerDay;
	private int minDailyLecturesPerCur;
	private int maxDailyLecturesPerCur;
	private ConstraintWeightings constrWeightings; // TODO recalc timetable scores on change
	private List<Chair> chairs = new LinkedList<>();
	private List<Teacher> teachers = new LinkedList<>();
	private List<Room> rooms = new LinkedList<>();
	private List<Course> courses = new LinkedList<>();
	private List<Curriculum> curricula = new LinkedList<>();
	private List<Timetable> timetables = new LinkedList<>(); // TODO must be empty before changes of semester data

	/**
	 * Creates a semester with an empty name, 1 days per week, 1 time slots
	 * per day, 0 min daily lectures per curriculum , 1 max daily lecture
	 * per curriculum and default constraint weightings (see
	 * {@link ConstraintWeightings#ConstraintWeightings()}).
	 */
	public Semester() {
		this.name = "";
		this.daysPerWeek = 1;
		this.timeSlotsPerDay = 1;
		this.minDailyLecturesPerCur = 0;
		this.maxDailyLecturesPerCur = 1;
		this.constrWeightings = new ConstraintWeightings();
	}

	public Semester(String name, int daysPerWeek, int timeSlotsPerDay,
	                int minDailyLecturesPerCur, int maxDailyLecturesPerCur,
	                ConstraintWeightings constrWeightings) {
		this();
		this.name = name;
		this.daysPerWeek = daysPerWeek;
		this.timeSlotsPerDay = timeSlotsPerDay;
		this.minDailyLecturesPerCur = minDailyLecturesPerCur;
		this.maxDailyLecturesPerCur = maxDailyLecturesPerCur;
		this.constrWeightings = constrWeightings;
	}

	@XmlAttribute(required = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlAttribute(required = true)
	public int getDaysPerWeek() {
		return daysPerWeek;
	}

	public void setDaysPerWeek(int daysPerWeek) throws WctttModelException {
		this.daysPerWeek = daysPerWeek;
	}

	@XmlAttribute(required = true)
	public int getTimeSlotsPerDay() {
		return timeSlotsPerDay;
	}

	public void setTimeSlotsPerDay(int timeSlotsPerDay) throws WctttModelException {
		this.timeSlotsPerDay = timeSlotsPerDay;
	}

	@XmlAttribute(required = true)
	public int getMinDailyLecturesPerCur() {
		return minDailyLecturesPerCur;
	}

	public void setMinDailyLecturesPerCur(int minDailyLecturesPerCur) {
		this.minDailyLecturesPerCur = minDailyLecturesPerCur;
	}

	@XmlAttribute(required = true)
	public int getMaxDailyLecturesPerCur() {
		return maxDailyLecturesPerCur;
	}

	public void setMaxDailyLecturesPerCur(int maxDailyLecturesPerCur) {
		this.maxDailyLecturesPerCur = maxDailyLecturesPerCur;
	}

	@XmlElement(required = true)
	public ConstraintWeightings getConstrWeightings() {
		return constrWeightings;
	}

	public void setConstrWeightings(ConstraintWeightings constrWeightings) {
		this.constrWeightings = constrWeightings;
	}

	@XmlElement(required = true)
	public List<Chair> getChairs() {
		return chairs;
	}

	private void setChairs(List<Chair> chairs) {
		this.chairs = chairs;
	}

	@XmlElement(required = true)
	public List<Teacher> getTeachers() {
		return teachers;
	}

	private void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	@XmlElement(required = true)
	public List<Room> getRooms() {
		return rooms;
	}

	private void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	@XmlElement(required = true)
	public List<Course> getCourses() {
		return courses;
	}

	private void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	@XmlElement(required = true)
	public List<Curriculum> getCurricula() {
		return curricula;
	}

	private void setCurricula(List<Curriculum> curricula) {
		this.curricula = curricula;
	}

	@XmlElement(required = true)
	public List<Timetable> getTimetables() {
		return timetables;
	}

	private void setTimetables(List<Timetable> timetables) {
		this.timetables = timetables;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Semester)) {
			return false;
		} else if (obj == this) {
			return true;
		}
		Semester other = (Semester) obj;
		if (!(this.name.equals(other.name) &&
				this.daysPerWeek == other.daysPerWeek &&
				this.timeSlotsPerDay == other.timeSlotsPerDay &&
				this.minDailyLecturesPerCur == other.minDailyLecturesPerCur &&
				this.maxDailyLecturesPerCur == other.maxDailyLecturesPerCur &&
				this.constrWeightings.equals(other.constrWeightings))) {
			return false;
		}

		//TODO also check content of lists
		if (this.chairs.size() != other.chairs.size()) {
			return false;
		}

		if (this.teachers.size() != other.teachers.size()) {
			return false;
		}

		if (this.rooms.size() != other.rooms.size()) {
			return false;
		}

		if (this.courses.size() != other.courses.size()) {
			return false;
		}

		if (this.curricula.size() != other.curricula.size()) {
			return false;
		}

		if (this.timetables.size() != other.timetables.size()) {
			return false;
		}

		return true;
	}

}
