package de.nicolasgross.wcttt.model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

import static de.nicolasgross.wcttt.model.ValidationHelper.validateDay;

@XmlType(propOrder = {"day", "periods"})
public class TimetableDay {

	private int day;
	private List<TimetablePeriod> periods = new ArrayList<>();

	public TimetableDay() {
		this.day = 1;
	}

	public TimetableDay(int day) throws WctttModelException {
		validateDay(day);
		this.day = day;
	}

	@XmlAttribute(required = true)
	public int getDay() {
		return day;
	}

	public void setDay(int day) throws WctttModelException {
		validateDay(day);
		this.day = day;
	}

	@XmlElementWrapper(required = true)
	@XmlElement(name = "timetablePeriod")
	public List<TimetablePeriod> getPeriods() {
		return periods;
	}

	// TODO add, delete, update courses

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

}
