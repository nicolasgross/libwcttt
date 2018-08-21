package de.nicolasgross.wcttt.lib.model;

import javax.xml.bind.annotation.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@XmlType(propOrder = {"id", "name", "unfavorablePeriods", "unavailablePeriods"})
public class Teacher {

	private String id;
	private String name;
	private final List<Period> unfavorablePeriods = new LinkedList<>();
	private final List<Period> unavailablePeriods = new LinkedList<>();

	public Teacher() {
		this.id = "teacher";
		this.name = "teacher";
	}

	public Teacher(String id, String name) {
		if (id == null || name == null) {
			throw new IllegalArgumentException("Parameters 'id' and 'name'" +
					"must not be null");
		}
		this.id = id;
		this.name = name;
	}

	@XmlAttribute(required = true)
	@XmlID
	public String getId() {
		return id;
	}

	public void setId(String id) {
		if (id == null) {
			throw new IllegalArgumentException("Parameter 'id' must not be " +
					"null");
		}
		this.id = id;
	}

	@XmlAttribute(required = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Parameter 'name' must not be " +
					"null");
		}
		this.name = name;
	}

	@XmlElementWrapper(required = false)
	@XmlElement(name = "unfavorable")
	public List<Period> getUnfavorablePeriods() {
		return unfavorablePeriods;
	}

	@XmlElementWrapper(required = false)
	@XmlElement(name = "unavailable")
	public List<Period> getUnavailablePeriods() {
		return unavailablePeriods;
	}

	private boolean unavailPeriodExists(Period period) {
		for (Period unavailable : unavailablePeriods) {
			if (period.equals(unavailable)) {
				return true;
			}
		}
		return false;
	}

	private boolean unfavorPeriodExists(Period period) {
		for (Period unfavorable : unfavorablePeriods) {
			if (period.equals(unfavorable)) {
				return true;
			}
		}
		return false;
	}

	private void addPeriod(Period period, boolean isUnfavorable) throws
			WctttModelException {
		if (period == null) {
			throw new IllegalArgumentException("Parameter 'period' must not " +
					"be null");
		} else if (unfavorPeriodExists(period)) {
			throw new WctttModelException("Period '" + period + "' is " +
					"already included in unfavorable periods");
		} else if (unavailPeriodExists(period)) {
			throw new WctttModelException("Period '" + period + "' is " +
					"already included in unavailable periods");
		}
		if (isUnfavorable) {
			unfavorablePeriods.add(period);
		} else {
			unavailablePeriods.add(period);
		}
	}

	public void addUnfavorablePeriod(Period period) throws WctttModelException {
		addPeriod(period, true);
	}

	public void addUnavailablePeriod(Period period) throws WctttModelException {
		addPeriod(period, false);
	}

	public boolean removeUnfavorablePeriod(Period period) {
		if (period == null) {
			throw new IllegalArgumentException("Parameter 'period' must not " +
					"be null");
		}
		return unfavorablePeriods.remove(period);
	}

	public boolean removeUnavailablePeriod(Period period) {
		if (period == null) {
			throw new IllegalArgumentException("Parameter 'period' must not " +
					"be null");
		}
		return unavailablePeriods.remove(period);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Teacher teacher = (Teacher) o;
		return Objects.equals(id, teacher.id) &&
				Objects.equals(name, teacher.name) &&
				Objects.equals(unfavorablePeriods, teacher.unfavorablePeriods) &&
				Objects.equals(unavailablePeriods, teacher.unavailablePeriods);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, unfavorablePeriods, unavailablePeriods);
	}

	@Override
	public String toString() {
		return name;
	}

}
