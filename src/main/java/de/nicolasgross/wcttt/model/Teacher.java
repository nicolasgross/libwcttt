package de.nicolasgross.wcttt.model;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Teacher {

	private static final AtomicInteger idCounter = new AtomicInteger();

	private final int id;
	private String name;
	private List<Assignment> unafavorablePeriods;
	private List<Assignment> unavailablePeriods;

	public Teacher() {
		this.id = nextId();
	}

	public static int nextId() {
		return idCounter.getAndIncrement();
	}

}
