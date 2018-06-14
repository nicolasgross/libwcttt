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

	@XmlAttribute(required = true)
	public int getDaysPerWeek() {
		return daysPerWeek;
	}

	@XmlAttribute(name = "timeSlotsPerDay", required = true) // TODO remove name if works
	public int getTimeSlotsPerDay() {
		return timeSlotsPerDay;
	}

	@XmlAttribute(name = "minDailyLecturesPerCur", required = true)
	public int getMinDailyLecturesPerCur() {
		return minDailyLecturesPerCur;
	}

	@XmlAttribute(name = "maxDailyLecturesPerCur", required = true)
	public int getMaxDailyLecturesPerCur() {
		return maxDailyLecturesPerCur;
	}

	@XmlIDREF
	public List<Teacher> getTeachers() {
		return teachers;
	}

	@XmlIDREF
	public List<Room> getRooms() {
		return rooms;
	}

	@XmlIDREF
	public List<Course> getCourses() {
		return courses;
	}

	@XmlIDREF
	public List<Curriculum> getCurricula() {
		return curricula;
	}

	@XmlIDREF
	public List<Timetable> getTimetables() {
		return timetables;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDaysPerWeek(int daysPerWeek) throws WctttModelException {
		this.daysPerWeek = daysPerWeek;
	}

	public void setTimeSlotsPerDay(int timeSlotsPerDay) throws WctttModelException {
		this.timeSlotsPerDay = timeSlotsPerDay;
	}

	public void setMinDailyLecturesPerCur(int minDailyLecturesPerCur) {
		this.minDailyLecturesPerCur = minDailyLecturesPerCur;
	}

	public void setMaxDailyLecturesPerCur(int maxDailyLecturesPerCur) {
		this.maxDailyLecturesPerCur = maxDailyLecturesPerCur;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public void setCurricula(List<Curriculum> curricula) {
		this.curricula = curricula;
	}

}
