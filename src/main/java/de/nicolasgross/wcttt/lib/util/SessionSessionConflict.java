package de.nicolasgross.wcttt.lib.util;

import de.nicolasgross.wcttt.lib.model.Curriculum;

import java.util.LinkedList;
import java.util.List;

public class SessionSessionConflict {

	private List<Curriculum> curricula = new LinkedList<>();
	boolean sessionConflict = false;
	boolean teacherConflict = false;

	public List<Curriculum> getCurricula() {
		return curricula;
	}

	public boolean isSessionConflict() {
		return sessionConflict;
	}

	public void setSessionConflict(boolean sessionConflict) {
		this.sessionConflict = sessionConflict;
	}

	public boolean isTeacherConflict() {
		return teacherConflict;
	}

	public void setTeacherConflict(boolean teacherConflict) {
		this.teacherConflict = teacherConflict;
	}
}
