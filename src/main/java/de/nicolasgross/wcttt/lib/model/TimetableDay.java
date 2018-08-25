package de.nicolasgross.wcttt.lib.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import java.util.Objects;

import static de.nicolasgross.wcttt.lib.model.ValidationHelper.validateDay;

@XmlType(propOrder = {"day", "periods"})
public class TimetableDay {

	private int day;
	private final ObservableList<TimetablePeriod> periods =
			FXCollections.observableArrayList();

	public TimetableDay() {
		this.day = ValidationHelper.PERIOD_DAY_MIN;
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
	public ObservableList<TimetablePeriod> getPeriods() {
		return periods;
	}

	private boolean periodExists(TimetablePeriod period) {
		for (TimetablePeriod existingPeriod : periods) {
			if (period.getDay() == existingPeriod.getDay() &&
					period.getTimeSlot() == existingPeriod.getTimeSlot()) {
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
		periods.add(period.getTimeSlot() - 1, period);
	}

	public boolean removePeriod(TimetablePeriod period) {
		if (period == null) {
			throw new IllegalArgumentException("Parameter 'period' must not " +
					"be null");
		}
		return periods.remove(period);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TimetableDay that = (TimetableDay) o;
		return day == that.day &&
				Objects.equals(periods, that.periods);
	}

	@Override
	public int hashCode() {
		return Objects.hash(day, periods);
	}
}
