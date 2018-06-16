package de.nicolasgross.wcttt.binder;

import de.nicolasgross.wcttt.model.Chair;
import de.nicolasgross.wcttt.model.ConstraintWeightings;
import de.nicolasgross.wcttt.model.Semester;
import de.nicolasgross.wcttt.model.WctttModelException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WctttBinderTest {

	@Test
	void emptySemesterWritesAndParses() throws WctttParserException,
			IOException {
		String path = "test-empty-semester.xml";
		WctttBinder binder = new WctttBinder(path);

		Semester semesterWrite = new Semester();
		binder.write(semesterWrite);

		Semester semesterRead = binder.parse();
		assertEquals(semesterWrite, semesterRead);
		//Files.delete(Paths.get(path));
	}

	@Test
	void fullComplexitySemesterWritesAndParses() throws WctttParserException,
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
		Chair chair = new Chair("swt", " Lehrstuhl Softwaretechnik & " +
				"Programmiersprachen");
		semesterWrite.getChairs().add(chair);
		binder.write(semesterWrite);

		Semester semesterRead = binder.parse();
		assertEquals(semesterWrite, semesterRead);
		//Files.delete(Paths.get(path));
	}

}
