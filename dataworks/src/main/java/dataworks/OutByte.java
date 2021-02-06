package dataworks;

import org.jetbrains.annotations.Contract;

/**
 * The {@link OutByte} class is a simulation of the "out byte" parameters in C#.
 */
public class OutByte
{
    private byte value;

    @Contract(pure = true)
    public OutByte(byte value)
    {
        this.value = value;
    }

    public byte getValue()
    {
        return value;
    }

    public void setValue(byte value)
    {
        this.value = value;
    }
}
