package dataworks.datetime;

import dataworks.ArgumentOutOfRangeException;
import dataworks.OverflowException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

/**
 * The {@link TimeSpan} class represents a duration of time. A {@link TimeSpan} can be negative or positive.
 * <p>
 * {@link TimeSpan} is internally represented as a number of milliseconds. While this maps well into units of time such
 * as hours and days, any periods longer than that are not representable in a nice fashion. For instance, a month can be
 * between 28 and 31 days, while a year can contain 365 or 366 days. A decade can have between 1 and 3 leap years,
 * depending on when you map the TimeSpan into the calendar. This is why we do not provide years() or months().
 * <p>
 * This is an imitation of the TimeSpan class of C#.
 */
public class TimeSpan implements Serializable, Comparable<TimeSpan>
{
    public static final long TICKS_PER_MILLISECOND = 10000;
    private static final double MILLISECONDS_PER_TICK = 1.0 / TICKS_PER_MILLISECOND;

    public static final long TICKS_PER_SECOND = TICKS_PER_MILLISECOND * 1000;
    public static final double SECONDS_PER_TICK = 1.0 / TICKS_PER_SECOND;

    public static final long TICKS_PER_MINUTE = TICKS_PER_SECOND * 60;
    private static final double MINUTES_PER_TICK = 1.0 / TICKS_PER_MINUTE;

    public static final long TICKS_PER_HOUR = TICKS_PER_MINUTE * 60;
    private static final double HOURS_PER_TICK = 1.0 / TICKS_PER_HOUR;

    public static final long TICKS_PER_DAY = TICKS_PER_HOUR * 24;
    private static final double DAYS_PER_TICK = 1.0 / TICKS_PER_DAY;

    private static final int MILLIS_PER_SECOND = 1000;
    private static final int MILLIS_PER_MINUTE = MILLIS_PER_SECOND * 60;
    private static final int MILLIS_PER_HOUR = MILLIS_PER_MINUTE * 60;
    private static final int MILLIS_PER_DAY = MILLIS_PER_HOUR * 24;

    static final long MAX_SECONDS = Long.MAX_VALUE / TICKS_PER_SECOND;
    static final long MIN_SECONDS = Long.MIN_VALUE / TICKS_PER_SECOND;

    static final long MAX_MILLI_SECONDS = Long.MAX_VALUE / TICKS_PER_MILLISECOND;
    static final long MIN_MILLI_SECONDS = Long.MIN_VALUE / TICKS_PER_MILLISECOND;

    static final long TICKS_PER_TENTH_SECOND = TICKS_PER_MILLISECOND * 100;

    public static final TimeSpan ZERO = new TimeSpan(0);
    public static final TimeSpan MAX_VALUE = new TimeSpan(Long.MAX_VALUE);
    public static final TimeSpan MIN_VALUE = new TimeSpan(Long.MIN_VALUE);

    // Internal so that dataworks.time.DateTime doesn't have to call an extra get method for some arithmetic operations.
    long ticks;

    @Contract(pure = true)
    public TimeSpan(long ticks)
    {
        this.ticks = ticks;
    }

    @Contract(pure = true)
    public TimeSpan(int hours, int minutes, int seconds)
    {

    }

    @Contract(pure = true)
    public TimeSpan(int days, int hours, int minutes, int seconds)
    {
        this(days, hours, minutes, seconds, 0);
    }

    @Contract(pure = true)
    public TimeSpan(int days, int hours, int minutes, int seconds, int milliseconds)
    {
        long totalMilliSeconds = ((long) days * 3600 * 24 + (long) hours * 3600 + (long) minutes * 60 + seconds) * 1000 + milliseconds;
        if ((totalMilliSeconds > MAX_MILLI_SECONDS) || (totalMilliSeconds < MIN_MILLI_SECONDS))
            throw new IllegalArgumentException("TimeSpan value overflow (TimeSpan too long).");
        ticks = (long) totalMilliSeconds * TICKS_PER_MILLISECOND;
    }

    public long getTicks()
    {
        return ticks;
    }

    public int getDays()
    {
        return (int) (ticks / TICKS_PER_DAY);
    }

    public int getHours()
    {
        return (int) ((ticks / TICKS_PER_HOUR) % 24);
    }

    public int getMilliseconds()
    {
        return (int) ((ticks / TICKS_PER_MILLISECOND) % 1000);
    }

    public int getMinutes()
    {
        return (int) ((ticks / TICKS_PER_MINUTE) % 60);
    }

    public int getSeconds()
    {
        return (int) ((ticks / TICKS_PER_SECOND) % 60);
    }

    public double getTotalDays()
    {
        return (double) ticks * DAYS_PER_TICK;
    }

    public double getTotalHours()
    {
        return (double) ticks * HOURS_PER_TICK;
    }

    public double getTotalMilliseconds()
    {
        double temp = (double) ticks * MILLISECONDS_PER_TICK;
        if (temp > MAX_MILLI_SECONDS)
            return (double) MAX_MILLI_SECONDS;
        if (temp < MIN_MILLI_SECONDS)
            return (double) MIN_MILLI_SECONDS;
        return temp;
    }

    public double getTotalMinutes()
    {
        return (double) ticks * MINUTES_PER_TICK;
    }

    public double getTotalSeconds()
    {
        return (double) ticks * SECONDS_PER_TICK;
    }

    public TimeSpan add(@NotNull TimeSpan timeSpan)
    {
        if (timeSpan == null)
            throw new NullPointerException("Argument \"timeSpan\" cannot be null.");

        long result = this.ticks + timeSpan.ticks;

        // Overflow if sings of operands was identical and result's sign was opposite.
        // >> 63 gives the sing bit (either 64 1's or 64 0's).
        if ((this.ticks >> 63 == timeSpan.ticks >> 63) && (this.ticks >> 63 != result >> 63))
            throw new OverflowException("TimeSpan overflow.");
        return new TimeSpan(result);
    }

    @Contract("null, _ -> fail; !null, null -> fail")
    public static int compare(TimeSpan t1, TimeSpan t2)
    {
        if (t1 == null)
            throw new NullPointerException("Argument \"t1\" cannot be null.");
        if (t2 == null)
            throw new NullPointerException("Argument \"t2\" cannot be null.");

        //        if (t1.ticks > t2.ticks)
        //            return 1;
        //        if (t1.ticks < t2.ticks)
        //            return -1;
        //        return 0;
        return Long.compare(t1.ticks, t2.ticks);
    }

    @NotNull
    public static TimeSpan fromDays(double value)
    {
        return interval(value, MILLIS_PER_DAY);
    }

    public TimeSpan duration()
    {
        if (ticks == TimeSpan.MIN_VALUE.ticks)
            throw new OverflowException("Overflow duration");
        return new TimeSpan(ticks >= 0 ? ticks : -ticks);
    }

    @Contract(value = "null -> false", pure = true)
    @Override
    public boolean equals(Object value)
    {
        if (value == null)
            return false;

        if (value instanceof TimeSpan)
            return ticks == ((TimeSpan) value).ticks;
        return false;
    }

    public boolean equals(TimeSpan value)
    {
        if (value == null)
            return false;
        return ticks == value.ticks;
    }

    @Contract(value = "null, _ -> false; !null, null -> false", pure = true)
    public static boolean equals(TimeSpan t1, TimeSpan t2)
    {
        if ((t1 == null) || (t2 == null))
            return false;

        return t1.ticks == t2.ticks;
    }

    @Override
    public int hashCode()
    {
        return (int) ticks ^ (int) (ticks >> 32);
    }

    @NotNull
    public static TimeSpan fromHours(double value)
    {
        return interval(value, MILLIS_PER_HOUR);
    }

    @NotNull
    public static TimeSpan fromMilliseconds(double value)
    {
        return interval(value, 1);
    }

    @NotNull
    public static TimeSpan fromMinutes(double value)
    {
        return interval(value, MILLIS_PER_MINUTE);
    }

    public TimeSpan negate()
    {
        if (ticks == TimeSpan.MIN_VALUE.ticks)
            throw new OverflowException("Overflow: negate 2's complete number.");
        return new TimeSpan(-ticks);
    }

    @NotNull
    public static TimeSpan fromSeconds(double value)
    {
        return interval(value, MILLIS_PER_SECOND);
    }

    public TimeSpan subtract(TimeSpan ts)
    {
        if (ts == null)
            throw new NullPointerException("Argument \"ts\" cannot be null.");

        long result = ticks - ts.ticks;

        // Overflow if sings of operands was different and result's sign was opposite from the first argument's sign.
        // i.e. negative - positive => negative, positive - negative = positive.
        // >> 63 gives the sign bit (either 64 1's or 64 0's).
        if ((ticks >> 63 != ts.ticks >> 63) && (ticks >> 63 != result >> 63))
            throw new OverflowException("Overflow: TimeSpan too long.");
        return new TimeSpan(result);
    }

    @NotNull
    @Contract(value = "_ -> new", pure = true)
    public static TimeSpan fromTicks(long value)
    {
        return new TimeSpan(value);
    }

    static long timeToTicks(int hour, int minute, int second)
    {
        // totalSeconds is bounded by 2^31 * 2^12 + 2^31 * 2^8 + 2^31, which is less than 2^44, meaning we won't overflow totalSeconds.
        long totalSeconds = (long) hour * 3600 + (long) minute * 60 + (long) second;
        if ((totalSeconds > MAX_SECONDS) || (totalSeconds < MIN_SECONDS))
            throw new ArgumentOutOfRangeException("Overflow: TimeSpan too long.");
        return totalSeconds * TICKS_PER_SECOND;
    }

    @NotNull
    @Contract("_, _ -> new")
    private static TimeSpan interval(double value, double scale)
    {
        if (Double.isNaN(value))
            throw new IllegalArgumentException("Argument \"value\" cannot be NaN.");

        double temp = value * scale;
        double millis = temp + (value >= 0.5 ? 0.5 : -0.5); // TODO: Figure out this +- 0.5.
        if ((millis > Long.MAX_VALUE / TICKS_PER_MILLISECOND) || (millis < Long.MIN_VALUE / TICKS_PER_MILLISECOND))
            throw new OverflowException("Overflow (TimeSpan too long).");
        return new TimeSpan((long) millis * TICKS_PER_MILLISECOND);
    }


    @Override
    public int compareTo(@NotNull TimeSpan o)
    {
        return 0;
    }
}
