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

	@XmlElementWrapper
	@XmlElement(name = "unfavorable", required = true)
	public List<Period> getUnfavorablePeriods() {
		return unfavorablePeriods;
	}

	public void setUnfavorablePeriods(List<Period> unfavorablePeriods) {
		this.unfavorablePeriods = unfavorablePeriods;
	}

	@XmlElementWrapper
	@XmlElement(name = "unavailable", required = true)
	public List<Period> getUnavailablePeriods() {
		return unavailablePeriods;
	}

	public void setUnavailablePeriods(List<Period> unavailablePeriods) {
		this.unavailablePeriods = unavailablePeriods;
	}

}
