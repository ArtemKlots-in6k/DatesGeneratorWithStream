import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Владелец on 11.07.2016.
 */
public class DateGenerator {
    private static int FIRST_POSSIBLE_WEEKEND_IN_END_OF_MONTH = 25;
    private static int LAST_POSSIBLE_WEEKEND_ON_BEGINNING_OF_MONTH = 7;
    ArrayList<LocalDate> generatedDates;

    Function<Integer, Integer> limitCounter = (Integer year) -> {
        int limit = 0;
        for (int i = 1; i <= 12; i++) {
            limit += LocalDate.of(year, i, 1).with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();
        }
        return limit;
    };


    public List<LocalDate> generate(int year) {
        List<LocalDate> weekends = Stream.iterate(LocalDate.of(year, 1, 1), n -> n.plusDays(1))
                .limit(limitCounter.apply(year))
                .filter(d -> d.getDayOfWeek().getValue() >= 6)
                .filter(d -> (d.getDayOfMonth() <= LAST_POSSIBLE_WEEKEND_ON_BEGINNING_OF_MONTH
                        || d.getDayOfMonth() >= FIRST_POSSIBLE_WEEKEND_IN_END_OF_MONTH))
                .collect(Collectors.toList());
        System.out.println(weekends);
        return weekends;
    }
}
