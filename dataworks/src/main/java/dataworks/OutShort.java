package dataworks;

import org.jetbrains.annotations.Contract;

/**
 * The {@link OutShort} class is a simulation of the "out short" parameters in C#.
 */
public class OutShort
{
    private short value;

    public OutShort(short value)
    {
        this.value = value;
    }

    public short getValue()
    {
        return value;
    }

    public void setValue(short value)
    {
        this.value = value;
    }
}
