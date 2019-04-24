package uk.co.harrymartland.playground;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Test;

public class OptionalPlayground {

    @Test
    public void shouldMapObject() {
        assertEquals((Integer) 5, Optional.of("5").map(Integer::parseInt).orElse(null));
    }

    @Test
    public void shouldReturnDefaultValue() {
        assertEquals((Integer) 5, Optional.<Integer>empty().orElse(5));
    }

    @Test
    public void shouldNotMapWhenEmpty() {
        assertEquals((Integer) 5, Optional.<String>empty().map(Integer::parseInt).orElse(5));
    }

    @Test
    public void shouldAllowMapToReturnNull() {
        assertFalse(Optional.of(5).map(value -> null).isPresent());
    }

    @Test
    public void shouldHaveMultipleElses() {
        Object result = Optional.ofNullable(couldBeNullable())
                .orElse(Optional.ofNullable(couldBeNullable()).orElse("Hard Default"));
        Assert.assertEquals("Hard Default", result);
    }

    private Object couldBeNullable() {
        return null;
    }
}
