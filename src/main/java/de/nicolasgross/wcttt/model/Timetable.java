package de.nicolasgross.wcttt.model;

public class Timetable {

	private int id;
	private int name;
	private final Semester semester;
	private final PeriodAssignments[][] periodAssignments;
	private boolean upToDate;
	private double softConstraintViolations;

	public Timetable(Semester semester) {
		this.semester = semester;
		this.periodAssignments = new PeriodAssignments
				[semester.getDaysPerWeek()]
				[semester.getTimeSlotsPerDay()];
	}

}
