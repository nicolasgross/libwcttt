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

		Chair chair = new Chair("swt", "Lehrstuhl Softwaretechnik und " +
				"Programmiersprachen");
		Teacher teacher = new Teacher("lüttgen", "Prof. Dr. Gerald Lüttgen");
		teacher.addUnfavorablePeriod(new Period(1, 3));
		teacher.addUnavailablePeriod(new Period(2, 4));
		chair.addTeacher(teacher);
		semesterWrite.getChairs().add(chair);

		Room room1 = new Room("r1", "WE5/00.019", 156, chair,
				new RoomFeatures(2, false, false, false));
		Room room2 = new Room("r2", "WE5/00.021", 200, null,
				new RoomFeatures(2, false, false, false));
		semesterWrite.getRooms().add(room1);
		semesterWrite.getRooms().add(room2);

		Course course = new Course("fse",
				"Foundations of Software Engineering", "SWT-FSE-B", chair, 2);
		Session lecture = new Session("FSE-L1", "FSE Lecture 1", teacher, 80,
				false, false, new RoomFeatures(1, false, false, false), null);
		Session practical = new Session("FSE-P1", "FSE Practical 1", teacher,
				30, false, false, new RoomFeatures(1, false, false, true),
				null);
		course.addLecture(lecture);
		course.addPractical(practical);
		semesterWrite.getCourses().add(course);

		Curriculum curriculum = new Curriculum("sosysc-mand",
				"Software Systems Science - Mandatory");
		curriculum.addCourse(course);
		semesterWrite.getCurricula().add(curriculum);

		Timetable timetable = new Timetable("test-timetable");
		TimetableDay timetableDay = new TimetableDay(1);
		TimetablePeriod timetablePeriod1 = new TimetablePeriod(1, 1);
		TimetableAssignment timetableAssignment1 = new
				TimetableAssignment(lecture, room1);
		timetablePeriod1.getAssignments().add(timetableAssignment1);
		timetableDay.getPeriods().add(timetablePeriod1);
		TimetablePeriod timetablePeriod2 = new TimetablePeriod(1, 4);
		TimetableAssignment timetableAssignment2 = new
				TimetableAssignment(practical, room2);
		timetablePeriod2.getAssignments().add(timetableAssignment2);
		timetableDay.getPeriods().add(timetablePeriod2);
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
