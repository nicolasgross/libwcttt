package de.nicolasgross.wcttt.model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlType(propOrder = {"day", "periods"})
public class TimetableDay {

	private int day;
	private List<TimetablePeriod> periods = new ArrayList<>();

	@XmlAttribute(required = true)
	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	@XmlElement(required = true)
	public List<TimetablePeriod> getPeriods() {
		return periods;
	}

	public void setPeriods(List<TimetablePeriod> periods) {
		this.periods = periods;
	}

}
