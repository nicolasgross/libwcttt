package de.nicolasgross.wcttt.model;

import java.util.List;

public class Semester {

	private int daysPerWeek;
	private int periodsPerDay;
	private int minDailyLecturesPerCur;
	private int maxDailyLecturesPerCur;

	List<Teacher> teachers;
	List<Room> rooms;
	List<Course> courses;
	List<Curriculum> curricula;
	List<Timetable> timetables;

}
