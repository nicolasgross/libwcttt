package de.nicolasgross.wcttt.intern.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlType;
import java.util.LinkedList;
import java.util.List;

@XmlType(propOrder = {"id", "name", "unfavorablePeriods", "unavailablePeriods"})
public class Teacher {

	private String id;
	private String name = "";
	private List<Period> unfavorablePeriods = new LinkedList<>();
	private List<Period> unavailablePeriods = new LinkedList<>();

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

	@XmlElement(required = true)
	public List<Period> getUnfavorablePeriods() {
		return unfavorablePeriods;
	}

	public void setUnfavorablePeriods(List<Period> unafavorablePeriods) {
		this.unfavorablePeriods = unafavorablePeriods;
	}

	@XmlElement(required = true)
	public List<Period> getUnavailablePeriods() {
		return unavailablePeriods;
	}

	public void setUnavailablePeriods(List<Period> unavailablePeriods) {
		this.unavailablePeriods = unavailablePeriods;
	}

}
