import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Владелец on 11.07.2016.
 */
public class GeneratorTest {
    private DateGenerator dateGenerator;

    @Before
    public void before(){
        dateGenerator = new DateGenerator();
    }

    @Test
    public void generatorTest() throws Exception {
        List<LocalDate> generatedDates = dateGenerator.generate(2016);
        assertThat(generatedDates.size(), is(46));
    }
}
