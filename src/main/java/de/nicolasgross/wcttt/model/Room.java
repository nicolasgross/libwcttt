package de.nicolasgross.wcttt.model;

import java.util.Optional;

public class Room {

	private int id;
	private String name;
	private int capacity;
	private Chair holder;
	private RoomFeatures features;

	public Optional<Chair> getHolder() {
		return Optional.ofNullable(holder);
	}

}
