package de.nicolasgross.wcttt.model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

import static de.nicolasgross.wcttt.model.ValidationHelper.validateDay;

@XmlType(propOrder = {"day", "name","periods"})
public class TimetableDay {

	private int day;
	private String name;
	private final List<TimetablePeriod> periods = new ArrayList<>();

	public TimetableDay() {
		this.day = 1;
		this.name = "day";
	}

	public TimetableDay(int day, String name) throws WctttModelException {
		if (name == null) {
			throw new IllegalArgumentException("Parameter 'name' must not be" +
					"null");
		}
		validateDay(day);
		this.day = day;
		this.name = name;
	}

	@XmlAttribute(required = true)
	public int getDay() {
		return day;
	}

	public void setDay(int day) throws WctttModelException {
		validateDay(day);
		this.day = day;
	}

	@XmlAttribute(required = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Parameter 'name' must not be" +
					"null");
		}
		this.name = name;
	}

	@XmlElementWrapper(required = true)
	@XmlElement(name = "timetablePeriod")
	public List<TimetablePeriod> getPeriods() {
		return periods;
	}

	private boolean periodExists(TimetablePeriod period) {
		for (TimetablePeriod existingPeriod : periods) {
			if (((Period) period).equals(existingPeriod)) {
				return true;
			}
		}
		return false;
	}

	public void addPeriod(TimetablePeriod period) throws WctttModelException {
		if (period == null) {
			throw new IllegalArgumentException("Parameter 'period' must not " +
					"be null");
		} else if (periodExists(period)) {
			throw new WctttModelException("Period '" + period + "' is already" +
					" included in the day");
		}
		periods.add(period);
	}

	public boolean removePeriod(TimetablePeriod period) {
		if (period == null) {
			throw new IllegalArgumentException("Parameter 'period' must not " +
					"be null");
		}
		return periods.remove(period);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof TimetableDay)) {
			return false;
		} else if (obj == this) {
			return true;
		}

		TimetableDay other = (TimetableDay) obj;
		if (this.day != other.day) {
			return false;
		}

		if (this.periods.size() != other.periods.size()) {
			return false;
		} else if (this.periods != other.periods) {
			if (!(this.periods.containsAll(other.periods))) {
				return false;
			}
		}

		return true;
	}

	// TODO toString

}
