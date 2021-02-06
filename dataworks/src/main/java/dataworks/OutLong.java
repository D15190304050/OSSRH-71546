package dataworks;

import org.jetbrains.annotations.Contract;

/**
 * The {@link OutLong} class is a simulation of the "out long" parameters in C#.
 */
public class OutLong
{
    private long value;

    @Contract(pure = true)
    public OutLong(long value)
    {
        this.value = value;
    }

    public long getValue()
    {
        return value;
    }

    public void setValue(long value)
    {
        this.value = value;
    }
}
