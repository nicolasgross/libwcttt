/*
 * WCT³ (WIAI Course Timetabling Tool) is a software that strives to automate
 * the timetabling process at the WIAI faculty of the University of Bamberg.
 *
 * libwcttt comprises the data model, a binder (parser + emitter) to store the
 * data as XML files, the implementations of the algorithms as well as
 * functionality to calculate conflicts and their violations.
 *
 * Copyright (C) 2018 Nicolas Gross
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package wcttt.lib.algorithms.tabu_based_memetic_approach;

import wcttt.lib.algorithms.WctttAlgorithmException;
import wcttt.lib.algorithms.WctttAlgorithmFatalException;
import wcttt.lib.model.*;
import wcttt.lib.util.ConflictMatrixCalculator;
import wcttt.lib.util.SessionRoomConflict;
import wcttt.lib.util.SessionSessionConflict;
import wcttt.lib.util.TeacherPeriodConflict;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

/**
 * Implementation of the saturation degree heuristic, which was proposed by
 * Daniel Brélaz in 'New methods to color the vertices of a graph', 1979.
 *
 * Session == Vertex
 * Period == Color
 */
class SaturationDegreeHeuristic {

	private Semester semester;
	private Map<Session, Map<Session, SessionSessionConflict>> sessionSessionConflicts;
	private Map<InternalSession, Map<InternalRoom, SessionRoomConflict>> sessionRoomConflicts;
	private Map<Teacher, Map<Period, TeacherPeriodConflict>> teacherPeriodConflicts;

	SaturationDegreeHeuristic(Semester semester) {
		this.semester = semester;
		ConflictMatrixCalculator matrixCalculator =
				new ConflictMatrixCalculator(semester);
		sessionSessionConflicts = matrixCalculator.calcSessionSessionConflicts();
		sessionRoomConflicts = matrixCalculator.calcSessionRoomConflicts();
		teacherPeriodConflicts = matrixCalculator.calcTeacherPeriodConflicts();
	}

	/**
	 * Generate a number of feasible solutions. In case of a cancellation, the
	 * list of solutions that were found so far is returned.
	 *
	 * @param count the number of solutions that should be generated.
	 * @param isCancelled the status of the algorithm.
	 * @return a list of feasible solutions containing 'count' elements.
	 * @throws WctttAlgorithmException if an error occurred, e.g. unrealizable
	 * room requirements.
	 */
	List<Timetable> generateFeasibleSolutions(int count, AtomicBoolean isCancelled)
			throws WctttAlgorithmException {
		List<InternalSession> internalSessions = new LinkedList<>();
		List<ExternalSession> externalSessions = new LinkedList<>();
		fillSessionLists(internalSessions, externalSessions);
		List<Period> periods = Util.createPeriodList(semester);

		List<Timetable> generatedTimetables = new LinkedList<>();

		for (int i = 0; i < count && !isCancelled.get(); i++) {
			List<InternalSession> unassignedSessions =
					new LinkedList<>(internalSessions);

			Map<Period, Integer> periodUsages = new HashMap<>();
			periods.forEach(period -> periodUsages.put(period, 0));

			Map<InternalRoom, List<Period>> unassignedPeriods = new HashMap<>();
			semester.getInternalRooms().forEach(
					room -> unassignedPeriods.put(room, new LinkedList<>(periods)));

			Map<Session, TimetablePeriod> assignmentMap = new HashMap<>();
			Stream.concat(internalSessions.stream(), externalSessions.stream()).
					forEach(session -> assignmentMap.put(session, null));

			Timetable timetable = new Timetable();
			addTimetablePeriods(timetable);

			addPreAssignments(externalSessions, unassignedSessions, timetable,
					periodUsages, unassignedPeriods, assignmentMap);

			boolean couldFindAssignment = true;
			while (couldFindAssignment && !unassignedSessions.isEmpty() &&
					!isCancelled.get()) {
				InternalSession nextSession;
				List<InternalSession> maxSatDegrees = maxSaturationDegrees(
						unassignedSessions, assignmentMap, timetable);

				// Choose the session with the highest saturation degree, if
				// there are multiple, choose the one with the highest degree
				if (maxSatDegrees.size() > 1) {
					nextSession = maxConflictedSession(maxSatDegrees);
				} else {
					nextSession = maxSatDegrees.get(0);
				}

				List<Period> orderedPeriods =
						getPeriodsOrderedByLowestUsage(periodUsages);
				couldFindAssignment = Util.assignSessionRandomly(nextSession,
						timetable, semester, orderedPeriods, unassignedSessions,
						periodUsages, unassignedPeriods, assignmentMap);
			}

			if (couldFindAssignment && !isCancelled.get()) {
				generatedTimetables.add(timetable);
			} else {
				// If the heuristic failed at finding a feasible solution, the
				// infeasible timetable is discarded and a new timetable is
				// generated
				i--;
			}
		}

		return generatedTimetables;
	}

	private void fillSessionLists(List<InternalSession> internalSessions,
	                              List<ExternalSession> externalSessions) {
		for (Course course : semester.getCourses()) {
			for (Session lecture : course.getLectures()) {
				if (lecture instanceof InternalSession) {
					internalSessions.add((InternalSession) lecture);
				} else {
					externalSessions.add((ExternalSession) lecture);
				}
			}
			for (Session practical : course.getPracticals()) {
				if (practical instanceof InternalSession) {
					internalSessions.add((InternalSession) practical);
				} else {
					externalSessions.add((ExternalSession) practical);
				}
			}
		}
	}

	private void addTimetablePeriods(Timetable timetable) {
		try {
			for (int i = 1; i <= semester.getDaysPerWeek(); i++) {
				TimetableDay day = new TimetableDay(i);
				for (int j = 1; j <= semester.getTimeSlotsPerDay(); j++) {
					TimetablePeriod period = new TimetablePeriod(i, j);
					day.addPeriod(period);
				}
				timetable.addDay(day);
			}
		} catch (WctttModelException e) {
			throw new WctttAlgorithmFatalException("Implementation error, a " +
					"timetable day or period was created with illegal " +
					"parameters", e);
		}
	}

	private void addPreAssignments(
			List<ExternalSession> externalSessions,
			List<InternalSession> unassignedSessions, Timetable timetable,
			Map<Period, Integer> periodUsages,
			Map<InternalRoom, List<Period>> unassignedPeriods,
			Map<Session, TimetablePeriod> assignmentMap)
			throws WctttAlgorithmException {
		for (ExternalSession session : externalSessions) {
			// Pre-assignment must be present because it is an external
			// session
			Period period = session.getPreAssignment().get();
			Util.assignSession(session, period, session.getRoom(), timetable,
					semester, periodUsages, unassignedPeriods, assignmentMap);
		}
		List<InternalSession> removeList = new LinkedList<>();
		for (InternalSession session : unassignedSessions) {
			if (session.getPreAssignment().isPresent()) {
				Period period = session.getPreAssignment().get();
				InternalRoom randomRoom = selectRandomSuitableRoom(session,
						period, unassignedPeriods);
				Util.assignSession(session, period, randomRoom, timetable,
						semester, periodUsages, unassignedPeriods, assignmentMap);
				removeList.add(session);
			}
		}
		unassignedSessions.removeAll(removeList);
	}

	private InternalRoom selectRandomSuitableRoom(InternalSession session, Period period,
	                                              Map<InternalRoom, List<Period>> unassignedPeriods)
			throws WctttAlgorithmException {
		List<InternalRoom> suitableRooms = new LinkedList<>();
		Util.findSuitableRooms(session, semester).forEach(room -> {
			if (unassignedPeriods.get(room).contains(period)) {
				suitableRooms.add(room);
			}
		});

		if (suitableRooms.isEmpty()) {
			throw new WctttAlgorithmException("No suitable room was found for" +
					" session '" + session + "' in period '" + period + "'");
		} else {
			return suitableRooms.get(new Random().nextInt(suitableRooms.size()));
		}
	}

	/**
	 * Calculates a list of the unassigned sessions with the highest saturation
	 * degree. The saturation degree of a vertex (== session) is the number of
	 * distinct colors (== periods) that are used to schedule adjacent
	 * (== conflicted) sessions.
	 *
	 * @param unassignedSessions the list of unassigned sessions.
	 * @param assignmentMap the mapping of sessions to their respectively
	 *                         assigned period, or null if unassigned.
	 * @param timetable the current timetable.
	 * @return the list of unassigned sessions with the highest saturation degree.
	 */
	private List<InternalSession> maxSaturationDegrees(
			List<InternalSession> unassignedSessions,
			Map<Session, TimetablePeriod> assignmentMap, Timetable timetable) {
		List<InternalSession> maxSatDegrees = new LinkedList<>();
		Map<InternalSession, Integer> saturationDegrees = new HashMap<>();
		int max = 0;
		for (InternalSession session : unassignedSessions) {
			Set<TimetablePeriod> distinctColors = new HashSet<>();
			for (Map.Entry<Session, SessionSessionConflict> entry :
					sessionSessionConflicts.get(session).entrySet()) {
				Session otherSession = entry.getKey();
				SessionSessionConflict conflict = entry.getValue();
				if (!otherSession.equals(session) &&
						(!conflict.getCurricula().isEmpty() ||
								conflict.isSessionConflict() ||
								conflict.isTeacherConflict())) {
					TimetablePeriod assignedPeriod =
							assignmentMap.get(otherSession);
					if (assignedPeriod != null) {
						distinctColors.add(assignedPeriod);
						if (otherSession.isDoubleSession()) {
							// also add second period of double session
							distinctColors.add(timetable.
									getDays().get(assignedPeriod.getDay() - 1).
									getPeriods().get(assignedPeriod.getTimeSlot()));
						}
					}
				}
			}
			if (distinctColors.size() > max) {
				max = distinctColors.size();
			}
			saturationDegrees.put(session, distinctColors.size());
		}

		for (Map.Entry<InternalSession, Integer> entry : saturationDegrees.entrySet()) {
			if (entry.getValue() == max) {
				maxSatDegrees.add(entry.getKey());
			}
		}

		return maxSatDegrees;
	}

	/**
	 * Finds the session with the highest number of conflicts.
	 *
	 * @param sessions the list of sessions.
	 * @return the most conflicted session.
	 */
	private InternalSession maxConflictedSession(List<InternalSession> sessions) {
		InternalSession maxSession = sessions.get(0);
		int maxConflicts = calcNumberOfConflicts(maxSession);
		int tmp;
		for (InternalSession session : sessions) {
			tmp = calcNumberOfConflicts(session);
			if (tmp > maxConflicts) {
				maxConflicts = tmp;
				maxSession = session;
			}
		}
		return maxSession;
	}

	private int calcNumberOfConflicts(Session session) {
		int counter = 0;
		Map<Session, SessionSessionConflict> sessionConflicts =
				sessionSessionConflicts.get(session);
		for (SessionSessionConflict conflict : sessionConflicts.values()) {
			if (conflict != null) {
				counter += conflict.getCurricula().size();
				if (conflict.isSessionConflict()) {
					counter++;
				}
				if (conflict.isTeacherConflict()) {
					counter++;
				}
			}
		}
		if (session.isDoubleSession()) {
			counter *= 2;
		}
		if (session instanceof InternalSession) {
			Map<InternalRoom, SessionRoomConflict> roomConflicts =
					sessionRoomConflicts.get(session);
			for (SessionRoomConflict conflict : roomConflicts.values()) {
				if (!conflict.fullfillsFeatures()) {
					counter++;
				}
			}
		}
		Map<Period, TeacherPeriodConflict> periodConflicts =
				teacherPeriodConflicts.get(session.getTeacher());
		for (TeacherPeriodConflict conflict : periodConflicts.values()) {
			if (conflict.isUnavailable()) {
				counter++;
			}
		}
		return counter;
	}


	/**
	 * Generates a list of all colors (== periods), ordered by their number of
	 * usages for assignments from low to high, except for usages of 0, which
	 * resembles the highest number in this order.
	 *
	 * In addition, some randomization is realized within this method in two ways:
	 * 1.  The list of colors with usage >= 0 is shuffled before it is beeing
	 * sorted, such that colors with the same value are potentially distinctly
	 * ordered for every method call.
	 * 2.  The list of colors with usage == 0 is shuffled and appended to the
	 * previously sorted list.
	 *
	 * @param periodUsages the mapping of periods to their number of usages.
	 * @return the ordered list of periods.
	 */
	private List<Period> getPeriodsOrderedByLowestUsage(Map<Period, Integer> periodUsages) {
		LinkedList<Period> alreadyUsedPeriods = new LinkedList<>();
		LinkedList<Period> unusedPeriods = new LinkedList<>();
		periodUsages.forEach((key, value) -> {
			if (value > 0) {
				alreadyUsedPeriods.add(key);
			} else {
				unusedPeriods.add(key);
			}
		});
		Collections.shuffle(alreadyUsedPeriods);
		Collections.shuffle(unusedPeriods);

		List<Period> orderedList = new LinkedList<>(alreadyUsedPeriods);
		orderedList.sort((o1, o2) -> {
			int o1Usages = periodUsages.get(o1);
			int o2Usages = periodUsages.get(o2);
			if (o1Usages == o2Usages) {
				return 0;
			} else if (o1Usages == 0 || o1Usages > o2Usages) {
				return 1;
			} else {
				return -1;
			}
		});

		Collections.shuffle(unusedPeriods);
		orderedList.addAll(unusedPeriods);

		return orderedList;
	}
}
