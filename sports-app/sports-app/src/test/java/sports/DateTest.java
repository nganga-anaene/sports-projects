package sports;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

public class DateTest {

	@Test
	public void test() {
		String date = "2000-12-29";
		LocalDate local = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}

}
