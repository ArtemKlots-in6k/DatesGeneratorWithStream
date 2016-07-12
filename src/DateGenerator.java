import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Владелец on 11.07.2016.
 */
public class DateGenerator {
    private static int LAST_POSSIBLE_WEEKEND_ON_BEGINNING_OF_MONTH = 7;


    public Stream<LocalDate> generate(int year) {
        return Stream.iterate(LocalDate.of(year, 1, 1), n -> n.plusDays(1))
                .limit(Year.of(year).length());
    }


    public String print(Stream<LocalDate> inputStream) {
        String resultsTable = inputStream
                .filter(d -> d.getDayOfWeek().getValue() >= 6)
                .filter(d -> (d.getDayOfMonth() <= LAST_POSSIBLE_WEEKEND_ON_BEGINNING_OF_MONTH
                        || d.getDayOfMonth() >= Month.valueOf(d.getMonth().toString()).maxLength() - 6))
                .collect(Collectors.groupingBy(LocalDate::getMonth))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .map(s -> String.format("%1$-10s", s.getKey()) + s.getValue().toString())
                .collect(Collectors.joining("\n"));


        System.out.println(resultsTable);
        return resultsTable;
    }
}
