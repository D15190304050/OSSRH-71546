package dataworks;

import org.jetbrains.annotations.Contract;

/**
 * The {@link OutInt} class is a simulation of the "out int" parameters in C#.
 */
public class OutInt
{
    private int value;

    @Contract(pure = true)
    public OutInt(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return value;
    }

    public void setValue(int value)
    {
        this.value = value;
    }

    public void addOne()
    {
        this.value++;
    }

    public void subtractOne()
    {
        this.value--;
    }
}
