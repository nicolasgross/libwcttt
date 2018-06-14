package de.nicolasgross.wcttt.model;

import java.util.Optional;

public class Session {

	private int id;
	private String name;
	private Course course;
	private Teacher teacher;
	private int students;
	private boolean doubleSession;
	private Period preAssignment;
	private RoomFeatures roomRequirements;
	private boolean external;

	public Optional<Period> getPreAssignment() {
		return Optional.ofNullable(preAssignment);
	}

}
