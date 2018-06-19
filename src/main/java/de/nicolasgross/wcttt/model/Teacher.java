package de.nicolasgross.wcttt.model;

import javax.xml.bind.annotation.*;
import java.util.LinkedList;
import java.util.List;

@XmlType(propOrder = {"id", "name", "unfavorablePeriods", "unavailablePeriods"})
public class Teacher {

	private String id;
	private String name;
	private final List<Period> unfavorablePeriods = new LinkedList<>();
	private final List<Period> unavailablePeriods = new LinkedList<>();

	public Teacher() {
		this.id = "teacher";
		this.name = "";
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

	@XmlElementWrapper(required = true)
	@XmlElement(name = "unfavorable")
	public List<Period> getUnfavorablePeriods() {
		return unfavorablePeriods;
	}

	@XmlElementWrapper(required = true)
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
	public boolean equals(Object obj) {
		if (!(obj instanceof Teacher)) {
			return false;
		} else if (obj == this) {
			return true;
		}

		Teacher other = (Teacher) obj;
		if (!this.id.equals(other.id) || !this.name.equals(other.name)) {
			return false;
		}

		if (this.unfavorablePeriods.size() != other.unfavorablePeriods.size()) {
			return false;
		} else if (this.unfavorablePeriods != other.unfavorablePeriods) {
			if (!(this.unfavorablePeriods.containsAll(
					other.unfavorablePeriods))) {
				return false;
			}
		}

		if (this.unavailablePeriods.size() != other.unavailablePeriods.size()) {
			return false;
		} else if (this.unavailablePeriods != other.unavailablePeriods) {
			if (!(this.unavailablePeriods.containsAll(
					other.unavailablePeriods))) {
				return false;
			}
		}

		return true;
	}

	// TODO toString

}
