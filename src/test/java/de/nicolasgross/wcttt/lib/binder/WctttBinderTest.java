package de.nicolasgross.wcttt.lib.binder;

import de.nicolasgross.wcttt.lib.model.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WctttBinderTest {

	@Test
	void emptySemesterWritesAndParses() throws WctttBinderException,
			IOException {
		File file = new File("libwcttt-test-empty-semester.xml");
		WctttBinder binder = new WctttBinder(file);

		Semester semesterWrite = new SemesterImpl();
		binder.write(semesterWrite);

		Semester semesterRead = binder.parse();
		assertEquals(semesterWrite, semesterRead);
		Files.delete(file.toPath());
	}

	@Test
	void fullComplexitySemesterWritesAndParses() throws WctttBinderException,
			WctttModelException, IOException {
		File file = new File("libwcttt-test-complex-semester.xml");
		WctttBinder binder = new WctttBinder(file);

		Semester semesterWrite = new SemesterImpl();
		semesterWrite.setName("test-semester");
		semesterWrite.setDaysPerWeek(5);
		semesterWrite.setTimeSlotsPerDay(6);
		semesterWrite.setMaxDailyLecturesPerCur(4);
		semesterWrite.setConstrWeightings(new ConstraintWeightings(1.0, 2,
				3.4, 4.1, 5.8, 6.2, 7.9, 8.5));

		Chair chair = new Chair("abc", "Lehrstuhl Softwaretechnik und " +
				"Programmiersprachen", "SWT");
		semesterWrite.addChair(chair);
		semesterWrite.updateChairId(chair, "swt");
		Teacher teacher = new Teacher("abc", "Prof. Dr. Gerald Lüttgen");
		teacher.addUnfavorablePeriod(new Period(1, 3));
		teacher.addUnavailablePeriod(new Period(2, 4));
		semesterWrite.addTeacherToChair(teacher, chair);
		semesterWrite.updateTeacherId(teacher, chair, "lüttgen");

		InternalRoom room1 = new InternalRoom("r0", "WE5/00.019", 156, chair,
				new RoomFeatures(2, false, false, false));
		InternalRoom room2 = new InternalRoom("r2", "WE5/00.021", 200, null,
				new RoomFeatures(2, false, false, false));
		ExternalRoom room3 = new ExternalRoom("r3", "Audimax");
		semesterWrite.addInternalRoom(room1);
		semesterWrite.updateRoomId(room1, "r1");
		semesterWrite.addInternalRoom(room2);
		semesterWrite.addExternalRoom(room3);

		Course course = new Course("abc",
				"Foundations of Software Engineering", "SWT-FSE-B", chair,
				CourseLevel.Bachelor, 2);
		semesterWrite.addCourse(course);
		semesterWrite.updateCourseId(course, "fse");
		InternalSession lecture1 = new InternalSession("abc", "FSE Lecture 1",
				teacher, course, false, null, 80,
				new RoomFeatures(1, false, false, false));
		Period preAssignment = new Period(2, 3);
		ExternalSession lecture2 = new ExternalSession("FSE-L2",
				"FSE Lecture 2", teacher, course, false, preAssignment, room3);
		semesterWrite.addCourseLecture(lecture1, course);
		semesterWrite.addCourseLecture(lecture2, course);
		semesterWrite.updateCourseSessionId(lecture1, course, "FSE-L1");
		InternalSession practical = new InternalSession("FSE-P1",
				"FSE Practical 1", teacher, course, false, null, 30,
				new RoomFeatures(1, false, false, true));
		semesterWrite.addCoursePractical(practical, course);

		Curriculum curriculum = new Curriculum("sosysc-mand",
				"Software Systems Science - Mandatory");
		semesterWrite.addCurriculum(curriculum);
		curriculum.addCourse(course);

		Timetable timetable = new Timetable("test-timetable");
		TimetableDay timetableDay = new TimetableDay(1);
		TimetablePeriod timetablePeriod1 = new TimetablePeriod(1, 1);
		TimetableAssignment timetableAssignment1 = new
				TimetableAssignment(lecture1, room1);
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
		Files.delete(file.toPath());
	}

	@Test
	void tinyWiaiOutputEqualsInput() throws WctttBinderException, IOException {
		File inputFile = new File("src/test/resources/tiny-wiai.xml");
		WctttBinder binderRead = new WctttBinder(inputFile);
		Semester tinyWiaiSem = binderRead.parse();

		File outputFile = new File("libwcttt-test-tiny-wiai-out.xml");
		WctttBinder binderWrite = new WctttBinder(outputFile);
		binderWrite.write(tinyWiaiSem);

		byte[] input = Files.readAllBytes(inputFile.toPath());
		byte[] output = Files.readAllBytes(outputFile.toPath());
		assertTrue(Arrays.equals(input, output));
		Files.delete(outputFile.toPath());
	}
}
