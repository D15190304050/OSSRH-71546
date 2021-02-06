package dataworks;

import org.jetbrains.annotations.Contract;

/**
 * The {@link OutDouble} class is a simulation of the "out double" parameters in C#.
 */
public class OutDouble
{
    private double value;

    @Contract(pure = true)
    public OutDouble(double value)
    {
        this.value = value;
    }

    public double getValue()
    {
        return value;
    }

    public void setValue(double value)
    {
        this.value = value;
    }
}
