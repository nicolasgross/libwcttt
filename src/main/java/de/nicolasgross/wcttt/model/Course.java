package de.nicolasgross.wcttt.model;

import javax.xml.bind.annotation.*;
import java.util.LinkedList;
import java.util.List;

import static de.nicolasgross.wcttt.model.ValidationHelper.validateMinNumOfDays;

@XmlType(propOrder = {"id", "name", "abbreviation", "chair", "minNumberOfDays",
		"lectures", "practicals"})
public class Course {

	private String id;
	private String name;
	private String abbreviation;
	private Chair chair;
	private int minNumberOfDays;
	private final List<Session> lectures = new LinkedList<>();
	private final List<Session> practicals = new LinkedList<>();

	public Course() {
		// TODO same id fix
		this.id = "";
		this.name = "";
		this.abbreviation = "";
		this.chair = new Chair();
		this.minNumberOfDays = 1;
	}

	public Course(String id, String name, String abbreviation, Chair chair,
	              int minNumberOfDays) throws WctttModelException {
		if (id == null || name == null || abbreviation == null ||
				chair == null) {
			throw new IllegalArgumentException("Parameters 'id', 'name', " +
					"'abbreviation' and 'chair' must not be null");
		}
		validateMinNumOfDays(minNumberOfDays);
		this.id = id;
		this.name = name;
		this.abbreviation = abbreviation;
		this.chair = chair;
		this.minNumberOfDays = minNumberOfDays;
	}

	@XmlAttribute(required = true)
	@XmlID
	public String getId() {
		return id;
	}

	public void setId(String id) throws WctttModelException {
		if (id == null) {
			throw new IllegalArgumentException("Parameter 'id' must not be " +
					"null");
		} else if (chair.getId().equals(id)) {
			throw new WctttModelException("Id '" + id + "' is already " +
					"assigned to the chair of this course");
		} else if ((lectureIdExists(id))) {
			throw new WctttModelException("Id '" + id + "' is already " +
					"assigned to a lecture of this course");
		} else if ((practicalIdExists(id))) {
			throw new WctttModelException("Id '" + id + "' is already " +
					"assigned to a practical of this course");
		}
		this.id = id;
	}

	@XmlAttribute(required = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Parameter 'name' must not be " +
					"null");
		}
		this.name = name;
	}

	@XmlAttribute(required = true)
	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		if (abbreviation == null) {
			throw new IllegalArgumentException("Parameter 'abbreviation' must" +
					" not be null");
		}
		this.abbreviation = abbreviation;
	}

	@XmlElement(required = true)
	@XmlIDREF
	public Chair getChair() {
		return chair;
	}

	public void setChair(Chair chair) throws WctttModelException {
		if (chair == null) {
			throw new IllegalArgumentException("Parameter 'chair' must not " +
					"be null");
		} else if (this.id.equals(chair.getId())) {
			throw new WctttModelException("Id '" + id + "' is already " +
					"assigned to the this course");
		} else if (lectureIdExists(chair.getId())) {
			throw new WctttModelException("Id '" + id + "' is already " +
					"assigned to a lecture of this course");
		} else if (practicalIdExists(chair.getId())) {
			throw new WctttModelException("Id '" + id + "' is already " +
					"assigned to a practical of this course");
		}
		this.chair = chair;
	}

	@XmlAttribute(required = true)
	public int getMinNumberOfDays() {
		return minNumberOfDays;
	}

	public void setMinNumberOfDays(int minNumberOfDays) throws
			WctttModelException {
		validateMinNumOfDays(minNumberOfDays);
		this.minNumberOfDays = minNumberOfDays;
	}

	@XmlElementWrapper(required = true)
	@XmlElement(name = "lecture")
	public List<Session> getLectures() {
		return lectures;
	}

	@XmlElementWrapper(required = true)
	@XmlElement(name = "practical")
	public List<Session> getPracticals() {
		return practicals;
	}

	private boolean lectureIdExists(String id) {
		for (Session lecture : lectures) {
			if (id.equals(lecture.getId())) {
				return true;
			}
		}
		return false;
	}

	private boolean practicalIdExists(String id) {
		for (Session practical : practicals) {
			if (id.equals(practical.getId())) {
				return true;
			}
		}
		return false;
	}

	private boolean sessionIdExists(String id) {
		return lectureIdExists(id) || practicalIdExists(id);
	}

	private void addSession(Session session, boolean isLecture) throws
			WctttModelException {
		if (session == null) {
			throw new IllegalArgumentException("Parameter 'session' must not " +
					"be null");
		} else if (this.id.equals(session.getId())) {
			throw new WctttModelException("Id '" + session.getId() + "' is " +
					"already assigned to this course");
		} else if (chair.getId().equals(session.getId())) {
			throw new WctttModelException("Id '" + session.getId() + "' is " +
					"already assigned to the chair of this course");
		} else if (lectureIdExists(session.getId())) {
			throw new WctttModelException("Id '" + session.getId() + "' is " +
					"already assigned to another lecture of the course");
		} else if (practicalIdExists(session.getId())) {
			throw new WctttModelException("Id '" + session.getId() + "' is " +
					"already assigned to another practical of the course");
		}
		if (isLecture) {
			lectures.add(session);
		} else {
			practicals.add(session);
		}
	}

	public void addLecture(Session lecture) throws WctttModelException {
		addSession(lecture, true);
	}

	public void addPractical(Session practical) throws WctttModelException {
		addSession(practical, false);
	}

	public boolean removeLecture(Session lecture) {
		if (lecture == null) {
			throw new IllegalArgumentException("Parameter 'lecture' must not " +
					"be null");
		}
		return lectures.remove(lecture);
	}

	public boolean removePractical(Session practical) {
		if (practical == null) {
			throw new IllegalArgumentException("Parameter 'practical' must " +
					"not be null");
		}
		return practicals.remove(practical);
	}

	public void updateSessionId(Session session, String id) throws
			WctttModelException {
		if (session == null || id == null) {
			throw new IllegalArgumentException("Parameters 'session' and 'id'" +
					" must not be null");
		} else if (!sessionIdExists(session.getId())) {
			throw new WctttModelException("Session '" + id + "' is not " +
					"assigned to the course");
		} else if (this.id.equals(id)) {
			throw new WctttModelException("Id '" + id + "' is already " +
					"assigned to this course");
		} else if (chair.getId().equals(id)) {
			throw new WctttModelException("Id '" + session.getId() + "' is " +
					"already assigned to the chair of this course");
		} else if (lectureIdExists(id)) {
			throw new WctttModelException("Id '" + id + "' is already " +
					"assigned to another lecture of the course");
		} else if (practicalIdExists(id)) {
			throw new WctttModelException("Id '" + id + "' is already " +
					"assigned to another practical of the course");
		}
		session.setId(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Course)) {
			return false;
		} else if (obj == this) {
			return true;
		}

		Course other = (Course) obj;
		if (!this.id.equals(other.id) || !this.name.equals(other.name) ||
				!this.chair.equals(other.chair) ||
				this.minNumberOfDays != other.minNumberOfDays) {
			return false;
		}

		if (this.lectures.size() != other.lectures.size()) {
			return false;
		} else if (this.lectures != other.lectures) {
			if (!(this.lectures.containsAll(other.lectures))) {
				return false;
			}
		}

		if (this.practicals.size() != other.practicals.size()) {
			return false;
		} else if (this.practicals != other.practicals) {
			if (!(this.practicals.containsAll(other.practicals))) {
				return false;
			}
		}

		return true;
	}

	// TODO toString

}
