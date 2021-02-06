package dataworks;

import org.jetbrains.annotations.Contract;

/**
 * The {@link OutBool} class is a simulation of the "out bool" parameters in C#.
 */
public class OutBool
{
    private boolean value;

    @Contract(pure = true)
    public OutBool(boolean value)
    {
        this.value = value;
    }

    public boolean isValue()
    {
        return value;
    }

    public void setValue(boolean value)
    {
        this.value = value;
    }
}
