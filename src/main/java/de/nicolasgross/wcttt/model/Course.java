package de.nicolasgross.wcttt.model;

import java.util.List;

public class Course {

	private int id;
	private String name;
	private Chair chair;
	private int minNumberOfDays;
	private List<Lecture> lectures;
	private List<Practical> practicals;

}
