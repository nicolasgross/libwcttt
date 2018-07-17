package de.nicolasgross.wcttt.lib.model;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlSeeAlso(TimetablePeriod.class)
@XmlType(propOrder = {"day", "timeSlot"})
public class Period {

	private int day;
	private int timeSlot;

	public Period() {
		this.day = 1;
		this.timeSlot = 1;
	}

	public Period(int day, int timeSlot) throws WctttModelException {
		ValidationHelper.validateDay(day);
		ValidationHelper.validateTimeSlot(day);
		this.day = day;
		this.timeSlot = timeSlot;
	}

	@XmlAttribute(required = true)
	public int getDay() {
		return day;
	}

	public void setDay(int day) throws WctttModelException {
		ValidationHelper.validateDay(day);
		this.day = day;
	}

	@XmlAttribute(required = true)
	public int getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(int timeslot) throws WctttModelException {
		ValidationHelper.validateTimeSlot(day);
		this.timeSlot = timeslot;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Period)) {
			return false;
		} else if (obj == this) {
			return true;
		}

		Period other = (Period) obj;
		if (!(this.day == other.day && this.timeSlot == other.timeSlot)) {
			return false;
		}

		return true;
	}

	// TODO toString

}