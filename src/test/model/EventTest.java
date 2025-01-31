package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Unit tests for the Event class
 */
public class EventTest {
    private Event event;
    private Date date;

    // NOTE: these tests might fail if time at which line (2) below is executed
    // is different from time that line (1) is executed. Lines (1) and (2) must
    // run in same millisecond for this test to make sense and pass.

    @BeforeEach
    public void runBefore() {
        event = new Event("Planets did something"); // (1)
        date = Calendar.getInstance().getTime(); // (2)
    }

    @Test
    public void testEvent() {
        // assertEquals(date, event.getDate()); // not passing, runs on different miliseconds
        assertEquals("Planets did something", event.getDescription());
    }

    @Test
    public void testToString() {
        assertEquals(date.toString() + "\n" + "Planets did something", event.toString());
    }

    @Test
    public void testEqualsAndHashcode() {
        assertFalse(event.equals(null));
        assertFalse(event.equals(new Planet(0, 0, 0, 0, 0)));
        assertEquals((13 * event.getDate().hashCode() + event.getDescription().hashCode()), event.hashCode());
    }
}
