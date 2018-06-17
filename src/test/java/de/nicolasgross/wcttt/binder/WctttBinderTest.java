package de.nicolasgross.wcttt.binder;

import de.nicolasgross.wcttt.model.*;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WctttBinderTest {

	@Test
	void emptySemesterWritesAndParses() throws WctttBinderException,
			IOException, SAXException {
		String path = "test-empty-semester.xml";
		WctttBinder binder = new WctttBinder(path);

		Semester semesterWrite = new Semester();
		binder.write(semesterWrite);

		Semester semesterRead = binder.parse();
		assertEquals(semesterWrite, semesterRead);
		Files.delete(Paths.get(path));
	}

	@Test
	void fullComplexitySemesterWritesAndParses() throws WctttBinderException,
			WctttModelException {
		String path = "test-complex-semester.xml";
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
		teacher.getUnavailablePeriods().add(new Period(2, 4));
		teacher.getUnfavorablePeriods().add(new Period(1, 3));
		chair.getTeachers().add(teacher);
		semesterWrite.getChairs().add(chair);

		Room room = new Room("r1", "WE5/00.019", 156, chair,
				new RoomFeatures(2, false, false, false));
		Room room1 = new Room("r2", "WE5/00.021", 200, null,
				new RoomFeatures(2, false, false, false));
		semesterWrite.getRooms().add(room);
		semesterWrite.getRooms().add(room1);

		Course course = new Course("fse",
				"Foundations of Software Engineering", "SWT-FSE-B", chair, 2);
		Session lecture = new Session("FSE-L1", "FSE Lecture 1", teacher, 80,
				false, false, new RoomFeatures(1, false, false, false), null);
		Session practical = new Session("FSE-P1", "FSE Practical 1", teacher,
				30, false, false, new RoomFeatures(1, false, false, true),
				null);
		course.getLectures().add(lecture);
		course.getPracticals().add(practical);
		semesterWrite.getCourses().add(course);

		binder.write(semesterWrite);

		Semester semesterRead = binder.parse();
		assertEquals(semesterWrite, semesterRead);
		//Files.delete(Paths.get(path)); TODO
	}

	@Test
	void tinyWiaiOutputEqualsInput() throws WctttBinderException, IOException {
		String inPath = "src/test/resources/tiny-wiai.xml";
		WctttBinder binderRead = new WctttBinder(inPath);
		Semester tinyWiaiSem = binderRead.parse();

		String outPath = "tiny-wiai-out.xml";
		WctttBinder binderWrite = new WctttBinder(outPath);
		binderWrite.write(tinyWiaiSem);

		byte[] input = Files.readAllBytes(Paths.get(inPath));
		byte[] output = Files.readAllBytes(Paths.get(outPath));
		assertTrue(Arrays.equals(input, output));
		Files.delete(Paths.get(outPath));
	}

}
