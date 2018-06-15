package de.nicolasgross.wcttt.model;

import javax.xml.bind.annotation.*;
import java.util.LinkedList;
import java.util.List;

@XmlRootElement(name = "semester")
@XmlType(propOrder = {"name", "daysPerWeek", "timeSlotsPerDay", "minDailyLecturesPerCur",
		"maxDailyLecturesPerCur", "teachers", "rooms", "courses", "curricula", "timetables"})
public class Semester {

	private String name = "";
	private int daysPerWeek;
	private int timeSlotsPerDay;
	private int minDailyLecturesPerCur;
	private int maxDailyLecturesPerCur;
	private List<Teacher> teachers = new LinkedList<>();
	private List<Room> rooms = new LinkedList<>();
	private List<Course> courses = new LinkedList<>();
	private List<Curriculum> curricula = new LinkedList<>();
	private List<Timetable> timetables = new LinkedList<>(); // TODO must be empty before changes of semester data


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

	@XmlIDREF
	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	@XmlElement(required = true)
	@XmlIDREF
	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	@XmlElement(required = true)
	@XmlIDREF
	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	@XmlElement(required = true)
	@XmlIDREF
	public List<Curriculum> getCurricula() {
		return curricula;
	}

	public void setCurricula(List<Curriculum> curricula) {
		this.curricula = curricula;
	}

	@XmlElement(required = true)
	@XmlIDREF
	public List<Timetable> getTimetables() {
		return timetables;
	}

	public void setTimetables(List<Timetable> timetables) {
		this.timetables = timetables;
	}

}
