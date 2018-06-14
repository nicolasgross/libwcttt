package de.nicolasgross.wcttt.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "semester")
public class Semester {

	@XmlAttribute(name = "name", required = true)
	private String name;
	private int daysPerWeek; // TODO final?
	private int timeSlotsPerDay;
	private int minDailyLecturesPerCur;
	private int maxDailyLecturesPerCur;
	private List<Teacher> teachers;
	private List<Room> rooms;
	private List<Course> courses;
	private List<Curriculum> curricula;
	private List<Period> periods;
	private List<Timetable> timetables; // TODO must be empty before changes of semester data
	private List<Constraint> softConstraints;

	public int getDaysPerWeek() {
		return daysPerWeek;
	}

	public int getTimeSlotsPerDay() {
		return timeSlotsPerDay;
	}
}
