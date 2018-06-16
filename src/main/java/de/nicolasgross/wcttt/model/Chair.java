package de.nicolasgross.wcttt.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"id", "name"})
public class Chair {

	private String id = "";
	private String name = "";

	public Chair() { }

	public Chair(String id, String name) {
		if (id == null || name == null) {
			throw new IllegalArgumentException("Parameters must not be null");
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

}
