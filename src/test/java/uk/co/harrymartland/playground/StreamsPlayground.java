package uk.co.harrymartland.playground;

import java.util.IntSummaryStatistics;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Assert;
import org.junit.Test;

public class StreamsPlayground {

    @Test
    public void findFirstShouldLazyLoad() {
        Optional<Object> first = Stream.<Supplier>of(Object::new, this::shouldNotBeCalled).map(Supplier::get).findFirst();
        Assert.assertTrue(first.isPresent());
    }

    @Test
    public void findingAveragesMinAndMaxIsEasy() {
        IntSummaryStatistics stats = Stream.of(1, 2, 3, 4, 5, 6, 7, 8)
                .collect(Collectors.summarizingInt(Integer::intValue));

        Assert.assertEquals(1, stats.getMin());
        Assert.assertEquals(8, stats.getMax());
        Assert.assertEquals(4.5, stats.getAverage(), 0.0001);
    }

    private Object shouldNotBeCalled() {
        Assert.fail("This method should not be called");
        return null;
    }
}
