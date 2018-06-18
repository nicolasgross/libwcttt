package de.nicolasgross.wcttt.model;

import javax.xml.bind.annotation.*;
import java.util.LinkedList;
import java.util.List;

import static de.nicolasgross.wcttt.model.ValidationHelper.*;

/**
 * Represents a semester.
 */
@XmlRootElement(name = "semester")
@XmlType(propOrder = {"name", "daysPerWeek", "timeSlotsPerDay",
		"minDailyLecturesPerCur", "maxDailyLecturesPerCur", "constrWeightings",
		"chairs", "rooms", "courses", "curricula", "timetables"})
public class Semester {

	private String name;
	private int daysPerWeek;
	private int timeSlotsPerDay;
	private int minDailyLecturesPerCur;
	private int maxDailyLecturesPerCur;
	private ConstraintWeightings constrWeightings;
	private List<Chair> chairs = new LinkedList<>();
	private List<Room> rooms = new LinkedList<>();
	private List<Course> courses = new LinkedList<>();
	private List<Curriculum> curricula = new LinkedList<>();
	private List<Timetable> timetables = new LinkedList<>();

	/**
	 * Creates a new semester with an empty name, 1 days per week, 1 time
	 * slots per day, 0 min daily lectures per curriculum , 1 max daily
	 * lecture per curriculum and default constraint weightings (see
	 * {@link ConstraintWeightings#ConstraintWeightings()}).
	 */
	public Semester() {
		this.name = "";
		this.daysPerWeek = 1;
		this.timeSlotsPerDay = 1;
		this.minDailyLecturesPerCur = 0;
		this.maxDailyLecturesPerCur = 1;
		this.constrWeightings = new ConstraintWeightings();
	}

	/**
	 * Creates a new semester.
	 *
	 * @param name                   the name of the semester, must not be null.
	 * @param daysPerWeek            the number of days in a week, must be > 0.
	 * @param timeSlotsPerDay        the number of time slots in a day, must
	 *                                  be > 0.
	 * @param minDailyLecturesPerCur the minimum number of daily lectures per
	 *                               curriculum, must >= 0.
	 * @param maxDailyLecturesPerCur the maximum number of daily lectures per
	 *                               curriculum, must be >=
	 *                               {@code minDailyLecturesPerCur}.
	 * @param constrWeightings       the soft constraint weightings for the
	 *                               semester, must not be null.
	 * @throws WctttModelException if parameters do not adhere to their
	 *                             respective value ranges.
	 */
	public Semester(String name, int daysPerWeek, int timeSlotsPerDay,
	                int minDailyLecturesPerCur, int maxDailyLecturesPerCur,
	                ConstraintWeightings constrWeightings) throws
			WctttModelException {
		if (name == null || constrWeightings == null) {
			throw new IllegalArgumentException("Parameters 'name' and " +
					"'constrWeightings' must not be null");
		}
		validateDaysPerWeek(daysPerWeek);
		validateTimeSlotsPerDay(timeSlotsPerDay);
		validateMinDailyLecturesPerCur(minDailyLecturesPerCur);
		validateMaxDailyLecturesPerCur(maxDailyLecturesPerCur,
				minDailyLecturesPerCur);
		this.name = name;
		this.daysPerWeek = daysPerWeek;
		this.timeSlotsPerDay = timeSlotsPerDay;
		this.minDailyLecturesPerCur = minDailyLecturesPerCur;
		this.maxDailyLecturesPerCur = maxDailyLecturesPerCur;
		this.constrWeightings = constrWeightings;
	}

	/**
	 * Getter for the name of a semester. Do not manipulate the returned
	 * reference.
	 *
	 * @return the name of the semester.
	 */
	@XmlAttribute(required = true)
	public String getName() {
		return name;
	}

	/**
	 * Setter for the name of a semester.
	 *
	 * @param name the new name of the semester, must not be null.
	 */
	public void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Parameter 'name' must not be " +
					"null");
		}
		this.name = name;
	}

	/**
	 * Getter for the number of days per week of a semester.
	 *
	 * @return the number of days per week of the semester.
	 */
	@XmlAttribute(required = true)
	public int getDaysPerWeek() {
		return daysPerWeek;
	}

	/**
	 * Setter for the number of days per week of a semester. The list of
	 * timetables must be empty to avoid inconsistencies.
	 *
	 * @param daysPerWeek the new number of days per week, must be >= 1.
	 * @throws WctttModelException if timetable list is not empty or {@code
	 * daysPerWeek} is < 1.
	 */
	public void setDaysPerWeek(int daysPerWeek) throws WctttModelException {
		if (!timetables.isEmpty()) {
			throw new WctttModelException("The timetable list must be empty " +
					"before changes can be made on the semester data");
		}
		validateDaysPerWeek(daysPerWeek);
		this.daysPerWeek = daysPerWeek;
	}

	/**
	 * Getter for the number of time slots per day of a semester.
	 *
	 * @return the number of time slots per day of the semester
	 */
	@XmlAttribute(required = true)
	public int getTimeSlotsPerDay() {
		return timeSlotsPerDay;
	}

	/**
	 * Setter for the number of time slots per day of a semester. The list of
	 * timetables must be empty to avoid inconsistencies.
	 *
	 * @param timeSlotsPerDay the new number of time slots per day, must be
	 *                        >= 1.
	 * @throws WctttModelException if timetable list is not empty or {@code
	 * timeSlotsPerDay} is < 1.
	 */
	public void setTimeSlotsPerDay(int timeSlotsPerDay) throws
			WctttModelException {
		if (!timetables.isEmpty()) {
			throw new WctttModelException("The timetable list must be empty " +
					"before changes can be made on the semester data");
		}
		validateTimeSlotsPerDay(timeSlotsPerDay);
		this.timeSlotsPerDay = timeSlotsPerDay;
	}

	/**
	 * Getter for the minimum number of daily lectures per curriculum of a
	 * semester.
	 *
	 * @return the minimum number of daily lectures per curriculum.
	 */
	@XmlAttribute(required = true)
	public int getMinDailyLecturesPerCur() {
		return minDailyLecturesPerCur;
	}

	/**
	 * Setter for the minimum number of daily lectures per curriculum of a
	 * semester. The list of timetables must be empty to avoid inconsistencies.
	 *
	 * @param minDailyLecturesPerCur the new minimum number of daily lectures
	 *                               per curriculum, must be >= 0.
	 * @throws WctttModelException if timetable list is not empty or {@code
	 * minDailyLecturesPerCur} is < 0.
	 */
	public void setMinDailyLecturesPerCur(int minDailyLecturesPerCur) throws
			WctttModelException {
		if (!timetables.isEmpty()) {
			throw new WctttModelException("The timetable list must be empty " +
					"before changes can be made on the semester data");
		}
		validateMinDailyLecturesPerCur(minDailyLecturesPerCur);
		this.minDailyLecturesPerCur = minDailyLecturesPerCur;
	}

	/**
	 * Getter for the maximum number of daily lectures per curriculum of a
	 * semester.
	 *
	 * @return the maximum number of daily lectures per curriculum.
	 */
	@XmlAttribute(required = true)
	public int getMaxDailyLecturesPerCur() {
		return maxDailyLecturesPerCur;
	}

	/**
	 * Setter for the maximum number of daily lectures per curriculum of a
	 * semester. The list of timetables must be empty to avoid inconsistencies.
	 *
	 * @param maxDailyLecturesPerCur the new maximum number of daily lectures
	 *                               per curriculum, must be >= {@code
	 *                               minDailyLecturesPerCur}.
	 * @throws WctttModelException if timetable list is not empty or {@code
	 * maxDailyLecturesPerCur} is < {@code minDailyLecturesPerCur}.
	 */
	public void setMaxDailyLecturesPerCur(int maxDailyLecturesPerCur) throws
			WctttModelException {
		if (!timetables.isEmpty()) {
			throw new WctttModelException("The timetable list must be empty " +
					"before changes can be made on the semester data");
		}
		validateMaxDailyLecturesPerCur(maxDailyLecturesPerCur,
				minDailyLecturesPerCur);
		this.maxDailyLecturesPerCur = maxDailyLecturesPerCur;
	}

	/**
	 * Getter for the soft constraint weightings of a semester.
	 *
	 * @return the soft constraint weightings of the semester.
	 */
	@XmlElement(required = true)
	public ConstraintWeightings getConstrWeightings() {
		return constrWeightings;
	}

	/**
	 * Setter for the soft constraint weightings of a semester.
	 *
	 * @param constrWeightings the new soft constraint weightings for the
	 *                            semester, must not be null.
	 */
	public void setConstrWeightings(ConstraintWeightings constrWeightings) {
		if (constrWeightings == null) {
			throw new IllegalArgumentException("Parameter 'constrWeightings' " +
					"must not be null");
		}
		this.constrWeightings = constrWeightings;
		for (Timetable timetable : timetables) {
			timetable.calcConstraintViolations();
		}
	}

	/**
	 * Getter for the chairs of a semester. Do not manipulate the returned
	 * references.
	 *
	 * @return the list of the chairs of the semester.
	 */
	@XmlElementWrapper(required = true)
	@XmlElement(name = "chair")
	public List<Chair> getChairs() {
		return chairs;
	}

	/**
	 * Getter for the rooms of a semester. Do not manipulate the returned
	 * references.
	 *
	 * @return the list of the rooms of the semester.
	 */
	@XmlElementWrapper(required = true)
	@XmlElement(name = "room")
	public List<Room> getRooms() {
		return rooms;
	}

	/**
	 * Getter for the courses of a semester. Do not manipulate the returned
	 * references.
	 *
	 * @return the list of the courses of the semester.
	 */
	@XmlElementWrapper(required = true)
	@XmlElement(name = "course")
	public List<Course> getCourses() {
		return courses;
	}

	/**
	 * Getter for the curricula of a semester. Do not manipulate the returned
	 * references.
	 *
	 * @return the list of the curricula of the semester.
	 */
	@XmlElementWrapper(required = true)
	@XmlElement(name = "curriculum")
	public List<Curriculum> getCurricula() {
		return curricula;
	}

	/**
	 * Getter for the timetables of a semester. Do not manipulate the returned
	 * references.
	 *
	 * @return the list of the timetables of the semester.
	 */
	@XmlElementWrapper(required = true)
	@XmlElement(name = "timetable")
	public List<Timetable> getTimetables() {
		return timetables;
	}

	// TODO add, delete, update, removeAllTimetables
	// TODO timetable list must be empty before changes of semester data?

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Semester)) {
			return false;
		} else if (obj == this) {
			return true;
		}

		Semester other = (Semester) obj;
		if (!this.name.equals(other.name) ||
				this.daysPerWeek != other.daysPerWeek ||
				this.timeSlotsPerDay != other.timeSlotsPerDay ||
				this.minDailyLecturesPerCur != other.minDailyLecturesPerCur ||
				this.maxDailyLecturesPerCur != other.maxDailyLecturesPerCur ||
				!this.constrWeightings.equals(other.constrWeightings)) {
			return false;
		}

		if (this.chairs.size() != other.chairs.size()) {
			return false;
		} else if (this.chairs != other.chairs) {
			if (!(this.chairs.containsAll(other.chairs))) {
				return false;
			}
		}

		if (this.rooms.size() != other.rooms.size()) {
			return false;
		} else if (this.rooms != other.rooms) {
			if (!(this.rooms.containsAll(other.rooms))) {
				return false;
			}
		}

		if (this.courses.size() != other.courses.size()) {
			return false;
		} else if (this.courses != other.courses) {
			if (!(this.courses.containsAll(other.courses))) {
				return false;
			}
		}

		if (this.curricula.size() != other.curricula.size()) {
			return false;
		} else if (this.curricula != other.curricula) {
			if (!(this.curricula.containsAll(other.curricula))) {
				return false;
			}
		}

		if (this.timetables.size() != other.timetables.size()) {
			return false;
		} else if (this.timetables != other.timetables) {
			if (!(this.timetables.containsAll(other.timetables))) {
				return false;
			}
		}

		return true;
	}

}
