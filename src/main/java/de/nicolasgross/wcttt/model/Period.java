package de.nicolasgross.wcttt.model;

public class Period {

	private int id;
	private int day;
	private int timeSlot;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(int timeslot) {
		this.timeSlot = timeslot;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO
		return false;
	}
}
