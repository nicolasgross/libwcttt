package de.nicolasgross.wcttt.model;

import javax.xml.bind.annotation.*;
import java.util.Optional;

@XmlType(propOrder = {"id", "name", "capacity", "holder", "features"})
public class Room {

	private String id = "";
	private String name = "";
	private int capacity;
	private RoomFeatures features = new RoomFeatures();
	private Chair holder = new Chair();

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

	@XmlElement(required = false) // Getter returns Optional, annotation must be here
	@XmlIDREF
	private Chair getHolder() { // TODO optional
		return holder;
	}

	public Optional<Chair> getHolderSafe() {
		return Optional.ofNullable(holder);
	}

	private void setHolder(Chair holder) {
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
