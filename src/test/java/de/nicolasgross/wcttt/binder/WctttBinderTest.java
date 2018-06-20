package de.nicolasgross.wcttt.binder;

import de.nicolasgross.wcttt.model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WctttBinderTest {

	@Test
	void emptySemesterWritesAndParses() throws WctttBinderException,
			IOException {
		String path = "libwcttt-test-empty-semester.xml";
		WctttBinder binder = new WctttBinder(path);

		Semester semesterWrite = new Semester();
		binder.write(semesterWrite);

		Semester semesterRead = binder.parse();
		assertEquals(semesterWrite, semesterRead);
		Files.delete(Paths.get(path));
	}

	@Test
	void fullComplexitySemesterWritesAndParses() throws WctttBinderException,
			WctttModelException, IOException {
		String path = "libwcttt-test-complex-semester.xml";
		WctttBinder binder = new WctttBinder(path);

		Semester semesterWrite = new Semester();
		semesterWrite.setName("test-semester");
		semesterWrite.setDaysPerWeek(5);
		semesterWrite.setTimeSlotsPerDay(6);
		semesterWrite.setMinDailyLecturesPerCur(0);
		semesterWrite.setMaxDailyLecturesPerCur(4);
		semesterWrite.setConstrWeightings(new ConstraintWeightings(1.0, 2,
				3.4, 4.1, 5.8, 6.2, 7.9, 8.5));

		Chair chair = new Chair("", "Lehrstuhl Softwaretechnik und " +
				"Programmiersprachen");
		semesterWrite.addChair(chair);
		semesterWrite.updateChairId(chair, "swt");
		Teacher teacher = new Teacher("", "Prof. Dr. Gerald Lüttgen");
		teacher.addUnfavorablePeriod(new Period(1, 3));
		teacher.addUnavailablePeriod(new Period(2, 4));
		semesterWrite.addTeacherToChair(teacher, chair);
		semesterWrite.updateTeacherId(teacher, chair, "lüttgen");

		Room room1 = new Room("r0", "WE5/00.019", 156, chair,
				new RoomFeatures(2, false, false, false));
		Room room2 = new Room("r2", "WE5/00.021", 200, null,
				new RoomFeatures(2, false, false, false));
		semesterWrite.addRoom(room1);
		semesterWrite.updateRoomId(room1, "r1");
		semesterWrite.addRoom(room2);

		Course course = new Course("",
				"Foundations of Software Engineering", "SWT-FSE-B", chair, 2);
		semesterWrite.addCourse(course);
		semesterWrite.updateCourseId(course, "fse");
		Session lecture = new Session("", "FSE Lecture 1", teacher, 80,
				false, false, new RoomFeatures(1, false, false, false), null);
		semesterWrite.addCourseLecture(lecture, course);
		semesterWrite.updateCourseSessionId(lecture, course, "FSE-L1");
		Session practical = new Session("FSE-P1", "FSE Practical 1", teacher,
				30, false, false, new RoomFeatures(1, false, false, true),
				null);
		semesterWrite.addCoursePractical(practical, course);

		Curriculum curriculum = new Curriculum("sosysc-mand",
				"Software Systems Science - Mandatory");
		curriculum.addCourse(course);
		semesterWrite.getCurricula().add(curriculum);

		Timetable timetable = new Timetable("test-timetable");
		TimetableDay timetableDay = new TimetableDay(1, "Monday");
		TimetablePeriod timetablePeriod1 = new TimetablePeriod(1, 1);
		TimetableAssignment timetableAssignment1 = new
				TimetableAssignment(lecture, room1);
		timetablePeriod1.addAssignment(timetableAssignment1);
		timetableDay.addPeriod(timetablePeriod1);
		TimetablePeriod timetablePeriod2 = new TimetablePeriod(1, 4);
		TimetableAssignment timetableAssignment2 = new
				TimetableAssignment(practical, room2);
		timetablePeriod2.addAssignment(timetableAssignment2);
		timetableDay.addPeriod(timetablePeriod2);
		timetable.addDay(timetableDay);
		semesterWrite.getTimetables().add(timetable);

		binder.write(semesterWrite);
		Semester semesterRead = binder.parse();
		assertEquals(semesterWrite, semesterRead);
		Files.delete(Paths.get(path));
	}

	@Test
	void tinyWiaiOutputEqualsInput() throws WctttBinderException, IOException {
		String inPath = "src/test/resources/tiny-wiai.xml";
		WctttBinder binderRead = new WctttBinder(inPath);
		Semester tinyWiaiSem = binderRead.parse();

		String outPath = "libwcttt-test-tiny-wiai-out.xml";
		WctttBinder binderWrite = new WctttBinder(outPath);
		binderWrite.write(tinyWiaiSem);

		byte[] input = Files.readAllBytes(Paths.get(inPath));
		byte[] output = Files.readAllBytes(Paths.get(outPath));
		assertTrue(Arrays.equals(input, output));
		Files.delete(Paths.get(outPath));
	}

}
