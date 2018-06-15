package de.nicolasgross.wcttt.intern.model;

import javax.xml.bind.annotation.*;

@XmlType(propOrder = {"id", "name", "capacity", "holder", "features"})
public class Room {

	private String id;
	private String name = "";
	private int capacity;
	private Chair holder = new Chair();
	private RoomFeatures features = new RoomFeatures();

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

	@XmlAttribute(required = true)
	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	@XmlElement(required = false)
	public Chair getHolder() { // TODO optional
		return holder;
	}

	public void setHolder(Chair holder) {
		this.holder = holder;
	}

	@XmlElement(required = true)
	public RoomFeatures getFeatures() {
		return features;
	}

	public void setFeatures(RoomFeatures features) {
		this.features = features;
	}

}
