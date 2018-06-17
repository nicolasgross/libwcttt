package de.nicolasgross.wcttt.model;

import javax.xml.bind.annotation.*;
import java.util.LinkedList;
import java.util.List;

@XmlType(propOrder = {"id", "name", "unfavorablePeriods", "unavailablePeriods"})
public class Teacher {

	private String id;
	private String name;
	private List<Period> unfavorablePeriods = new LinkedList<>();
	private List<Period> unavailablePeriods = new LinkedList<>();

	public Teacher() {
		this.id = "";
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
		this.id = id;
	}

	@XmlAttribute(required = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
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

}
