import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.stream.Stream;

import static java.lang.Math.toIntExact;


import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by Владелец on 11.07.2016.
 */
public class GeneratorTest {
    private DateGenerator dateGenerator;
    private final String NEW_LINE_SYMBOL = "\n";

    @Before
    public void before() {
        dateGenerator = new DateGenerator();
    }

    @Test
    public void generator() throws Exception {
        assertThat(toIntExact(dateGenerator.generate(2016).count()), is(366));
    }

    @Test
    public void findWeekend() throws Exception {
        Stream<LocalDate> stream = Stream.of(LocalDate.of(2016, 7, 30));
        String expectedString = "JULY      [2016-07-30]";
        assertThat(dateGenerator.print(stream), containsString(expectedString));
    }


    @Test
    public void findFirstWeekendInMonth() throws Exception {
        Stream<LocalDate> stream = Stream.of(LocalDate.of(2016, 1, 2));
        String expectedString = "JANUARY   [2016-01-02]";
        assertThat(dateGenerator.print(stream), containsString(expectedString));
    }

    @Test
    public void findLastWeekendInMonth() throws Exception {
        Stream<LocalDate> stream = Stream.of(LocalDate.of(2016, 1, 31));
        String expectedString = "[2016-01-31]";
        assertThat(dateGenerator.print(stream), containsString(expectedString));
    }

    @Test
    public void isWeekdayInResult() throws Exception {
        Stream<LocalDate> stream = Stream.of(LocalDate.of(2016, 1, 1));
        String outputString = dateGenerator.print(stream);
        String expectedString = "[2016-01-1]";
        assertThat(outputString, not(outputString.contains(expectedString)));
    }

    @Test
    public void rightMonthOutput() throws Exception {
        Stream<LocalDate> stream = Stream.of(LocalDate.of(2016, 1, 2),
                LocalDate.of(2016, 1, 3),
                LocalDate.of(2016, 1, 15),
                LocalDate.of(2016, 1, 30),
                LocalDate.of(2016, 1, 31));
        assertThat(dateGenerator.print(stream),
                containsString("JANUARY   [2016-01-02, 2016-01-03, 2016-01-30, 2016-01-31]" + NEW_LINE_SYMBOL));
    }

    @Test
    public void isAllMonthInTableFormat() throws Exception {
        Stream<LocalDate> stream = Stream.of(LocalDate.of(2016, 1, 1));
        String[] outputString = dateGenerator.print(stream).split(NEW_LINE_SYMBOL);

        assertThat(outputString.length, is(12));
    }

    @Test
    public void allMonthPrinting() throws Exception {
        Stream<LocalDate> stream = Stream.of(LocalDate.of(2016, 1, 1));
        String outputString = dateGenerator.print(stream);

        assertThat(outputString, both(containsString("JANUARY"))
                .and(containsString("FEBRUARY"))
                .and(containsString("MARCH"))
                .and(containsString("APRIL"))
                .and(containsString("MAY"))
                .and(containsString("JUNE"))
                .and(containsString("JULY"))
                .and(containsString("AUGUST"))
                .and(containsString("SEPTEMBER"))
                .and(containsString("OCTOBER"))
                .and(containsString("NOVEMBER"))
                .and(containsString("DECEMBER")));
    }
    @Test
    public void findWeekend1() throws Exception {
        Stream<LocalDate> stream = Stream.of(LocalDate.of(2016, 7, 30));
        String expectedString = "JULY      [2016-07-30]";
        assertThat(dateGenerator.print(stream), containsString(expectedString));
    }
}
