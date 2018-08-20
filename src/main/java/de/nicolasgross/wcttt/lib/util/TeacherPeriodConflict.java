package de.nicolasgross.wcttt.lib.util;

public class TeacherPeriodConflict {

	private boolean isUnfavorable = false;
	private boolean isUnavailable = false;

	public boolean isUnfavorable() {
		return isUnfavorable;
	}

	public void setUnfavorable(boolean unfavorable) {
		isUnfavorable = unfavorable;
	}

	public boolean isUnavailable() {
		return isUnavailable;
	}

	public void setUnavailable(boolean unavailable) {
		isUnavailable = unavailable;
	}
}
