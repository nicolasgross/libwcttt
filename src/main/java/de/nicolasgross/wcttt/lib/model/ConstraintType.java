package de.nicolasgross.wcttt.lib.model;

public enum ConstraintType {
	/**
	 * All lectures of a course shall be scheduled, and they shall be assigned
	 * to distinct time periods than other lectures or practicals of the course.
	 */
	h1,

	/**
	 * All practicals of a course shall be scheduled, and they shall be assigned
	 * to distinct time periods than the lectures of the course.
	 */
	h2,

	/**
	 * No more than one lecture or practical shall take place in the same room
	 * in the same period.
	 */
	h3,

	/**
	 * Lectures shall not take place in the same periods as other lectures or
	 * practicals in the same curriculum.
	 */
	h4,

	/**
	 * Practicals shall not take place in the same periods as other practicals
	 * of the curriculum if the course has only one practical or all of its
	 * practicals are overlapping.
	 */
	h5,

	/**
	 * Lectures and practicals given by the same teacher shall not take place in
	 * the same period.
	 */
	h6,

	/**
	 * For all periods where a teacher is unavailable, none of its lectures or
	 * practicals shall take place.
	 */
	h7,

	/**
	 * A lecture/practical with a preassigned period shall be scheduled to the
	 * respective period.
	 */
	h8,

	/**
	 * The features of a room shall fulfill the requirements of the respective
	 * lecture/practical.
	 */
	h9,

	/**
	 * There should be an appropriate number of seats in the room. A penalty
	 * is given for the number of students that exceed or fall below the number
	 * of seats.
	 */
	s1,

	/**
	 * The lectures of a course should be spread at least across the given
	 * minimum number of days. A penalty is given for every day below the
	 * minimum.
	 */
	s2,

	/**
	 * Lectures in a curriculum should be adjacent. A penalty is given for every
	 * lecture that is isolated from other lectures of the same curriculum.
	 */
	s3,

	/**
	 * Every lecture of a course should take place in the same room. A penalty
	 * is given for every additionally used room of a course.
	 */
	s4,

	/**
	 * Lectures/practicals should not take place in the periods that were marked
	 * as unfavorable by the teacher. A penalty is given for every
	 * lecture/practical assigned to such a period.
	 */
	s5,

	/**
	 * A teacher should not exceed two adjacent lectures/practicals. A penalty
	 * is given for all lectures/practicals involved in such a constellation.
	 */
	s6,

	/**
	 * A curriculum should not exceed the maximum number of daily lectures. A
	 * penalty is given for every lecture that deviates from this number.
	 */
	s7
}
