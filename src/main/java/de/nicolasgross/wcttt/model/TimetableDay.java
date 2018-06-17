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

	@XmlElementWrapper(required = true)
	@XmlElement(name = "timetablePeriod")
	public List<TimetablePeriod> getPeriods() {
		return periods;
	}

	public void setPeriods(List<TimetablePeriod> periods) {
		this.periods = periods;
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

}
